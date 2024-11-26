package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.InterfaceModel;
import fr.afpa.dev.pompey.Utilitaires.PlaceholderTextField;
import fr.afpa.dev.pompey.Utilitaires.Verification;
import fr.afpa.dev.pompey.Modele.DAO.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static fr.afpa.dev.pompey.Modele.DAO.DAOUtils.*;

public class ControllerClient extends JFrame {
    private JPanel contentPane;
    private JTextField nomTextField;
    private JTextField dateNaissanceTextField;
    private JTextField secusocialTextField;
    private JTextField cpTextField;
    private JTextField prenomTextField;
    private JTextField rueTextField;
    private JTextField villeTextField;
    private JTextField telephoneTextField;
    private JTextField emailTextField;
    private JButton annulerButton;
    private JButton creerButton;
    private JLabel coordonneeLabel;
    private JLabel adresseLabel;
    private JLabel contactLabel;
    private JComboBox medTraitantComboBox;
    private JComboBox mutuelleComboBox;
    private JTextField nomRegionField;
    private JComboBox regionComboBox;
    private JLabel informationLabel;

    // Initialisation des DAO
    private final CoordonneesDAO coordonneesDAO;
    private final AdressesDAO adressesDAO;
    private final ClientDAO clientDAO;
    private final MedecinDAO medecinDAO;
    private final MutuelleDAO mutuelleDAO;
    private final RegionDAO regionDAO;
    private final VilleDAO villeDAO;

    /**
     * Constructeur de la classe ControllerClient
     */
    public ControllerClient() {
        // Initialisation de la DAO
        coordonneesDAO = new CoordonneesDAO();
        adressesDAO = new AdressesDAO();
        clientDAO = new ClientDAO();
        medecinDAO = new MedecinDAO();
        mutuelleDAO = new MutuelleDAO();
        regionDAO = new RegionDAO();
        villeDAO = new VilleDAO();

        setTitle("Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Placeholder
        PlaceholderTextField.setPlaceholder(nomTextField, "Nom");
        PlaceholderTextField.setPlaceholder(prenomTextField, "Prénom");
        PlaceholderTextField.setPlaceholder(dateNaissanceTextField, "JJ/MM/AAAA");
        PlaceholderTextField.setPlaceholder(secusocialTextField, "Numéro de sécurité sociale");
        PlaceholderTextField.setPlaceholder(cpTextField, "Code postal");
        PlaceholderTextField.setPlaceholder(telephoneTextField, "Téléphone");
        PlaceholderTextField.setPlaceholder(emailTextField, "Email");
        PlaceholderTextField.setPlaceholder(rueTextField, "Rue");
        PlaceholderTextField.setPlaceholder(villeTextField, "Ville");

        DefaultComboBoxModel<Medecin> MedTraitantModel = new DefaultComboBoxModel<>();
        for (Medecin medecin : getMedecins()) {
            MedTraitantModel.addElement(medecin);
        }
        medTraitantComboBox.setModel(MedTraitantModel);
        mutuelleComboBox.setEditable(true);

        DefaultComboBoxModel<Mutuelle> mutuelleModel = new DefaultComboBoxModel<>();
        for (Mutuelle mutuelle : getMutuelles()) {
            mutuelleModel.addElement(mutuelle);
        }
        mutuelleComboBox.setModel(mutuelleModel);
        mutuelleComboBox.setEditable(true);

        DefaultComboBoxModel<Region> regionModel = new DefaultComboBoxModel<>();
        for (Region region : getRegions()) {
            regionModel.addElement(region); // Ajoute les objets Region directement
        }
        regionComboBox.setModel(regionModel);

        // Les Listeners
        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    enregistrerClient();
                } catch (SaisieException ex) {
                    new RuntimeException(ex);
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerClient();
            }
        });
    }

    /**
     * Méthode pour enregistrer un client
     *
     * @throws SaisieException
     */
    private void enregistrerClient() throws SaisieException {
        // Validation des champs
        validerChamps();
        try {
            Connection connect = clientDAO.getConnection();
            connect.setAutoCommit(false);

            int idCoordonnees = creerCoordonnees();
            int idVille = creerVille();
            int idAdresse = creerAdresse(idVille);
            int idMedecin = creerMedecin();
            int idMutuelle = creerMutuelle();

            creerClient(idCoordonnees, idAdresse, idMedecin, idMutuelle);

            connect.commit();
        } catch (Exception e) {
            try {
                clientDAO.getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("Erreur lors du rollback.", ex);
            }
            throw new RuntimeException("Erreur lors de l'enregistrement du client.", e);
        } finally {
            try {
                clientDAO.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException("Erreur lors de la réinitialisation de l'auto-commit.", ex);
            }
        }
    }

    /**
     * Méthode pour annuler l'ajout d'un client
     */
    private void annulerClient() {
        effaceToutLesChamps();
    }

    /**
     * Méthode pour effacer tous les champs
     */
    private void effaceToutLesChamps() {
        nomTextField.setText("");
        prenomTextField.setText("");
        dateNaissanceTextField.setText("");
        secusocialTextField.setText("");
        cpTextField.setText("");
        telephoneTextField.setText("");
        emailTextField.setText("");
        rueTextField.setText("");
        villeTextField.setText("");
    }

    private int creerCoordonnees() throws SaisieException {
        String email = emailTextField.getText().trim();
        String telephone = telephoneTextField.getText().trim();
        if (coordonneesDAO.findByEmail(telephone) != null || coordonneesDAO.findByTelephone(email) != null) {
            throw new SaisieException("Les coordonnées existent déjà");
        }
        return coordonneesDAO.create(new Coordonnees(email, telephone));
    }

    /**
     * Créer une nouvelle ville dans la base de données.
     *
     * @return l'id de la ville créée
     * @throws SaisieException si la ville existe déjà
     */
    private int creerVille() throws SaisieException {
        Region region = (Region) regionComboBox.getSelectedItem();
        String villeName = villeTextField.getText().trim();
        if (isVilleExist(villeName)) {
            return villeDAO.findByName(villeName).getId();
        }
        return villeDAO.create(new Ville(villeName, cpTextField.getText().trim(), region.getId()));
    }

    private void creerClient(int idCoordonnees, int idAdresse, int idMedecin, int idMutuelle) throws SaisieException {
        Client client = new Client(
                nomTextField.getText().trim(),
                prenomTextField.getText().trim(),
                secusocialTextField.getText().trim(),
                dateNaissanceTextField.getText().trim(),
                idMedecin,
                idCoordonnees,
                idAdresse,
                idMutuelle
        );
        clientDAO.create(client);
        InterfaceModel.ShowLabelWithTimer(informationLabel, "Client créé", Color.GREEN);
    }

    private int creerAdresse(int idVille) {
        return adressesDAO.create(new Adresses(rueTextField.getText().trim(), idVille));
    }

    private int creerMedecin() throws SaisieException {
        Object selectedMedecin = medTraitantComboBox.getSelectedItem();

        int newIdMedecin;
        if (selectedMedecin instanceof Medecin) {
            newIdMedecin = ((Medecin) selectedMedecin).getId();
        } else if (selectedMedecin instanceof String) {
            String[] parts = ((String) selectedMedecin).split(" ");
            if (parts.length >= 2) {
                Medecin newMedecin = new Medecin(parts[0], parts[1]);
                newIdMedecin = medecinDAO.create(newMedecin);
            } else {
                throw new SaisieException("Nom ou prénom du médecin incomplet.");
            }
        } else {
            throw new SaisieException("Sélection invalide pour le médecin traitant.");
        }
        return newIdMedecin;
    }

    private int creerMutuelle() throws SaisieException {
        Object mutuelle = mutuelleComboBox.getSelectedItem();

        if(mutuelle instanceof Mutuelle){
            return ((Mutuelle) mutuelle).getId();
        } else if(mutuelle instanceof String){
            return mutuelleDAO.create(new Mutuelle(mutuelle.toString(), 0, 0, 0));
        } else {
            throw new SaisieException("Sélection invalide pour la mutuelle.");
        }
    }

    private void validerChamps() throws SaisieException {
        if (nomTextField.getText().trim().isEmpty() || prenomTextField.getText().trim().isEmpty() || dateNaissanceTextField.getText().trim().isEmpty() || secusocialTextField.getText().trim().isEmpty() || cpTextField.getText().trim().isEmpty() || telephoneTextField.getText().trim().isEmpty() || emailTextField.getText().trim().isEmpty() || rueTextField.getText().trim().isEmpty() || villeTextField.getText().trim().isEmpty() || mutuelleComboBox.getSelectedItem() == null) {
            throw new SaisieException("Veuillez remplir tous les champs");
        }
    }

    private boolean isVilleExist(String villeName) {
        return villeDAO.findAll().stream()
                .anyMatch(ville -> ville.getNom().equals(villeName));
    }

    private boolean isMedecinExist(String medecinName) {
        return medecinDAO.findAll().stream()
                .anyMatch(medecin -> medecin.getNom().equals(medecinName));
    }
}