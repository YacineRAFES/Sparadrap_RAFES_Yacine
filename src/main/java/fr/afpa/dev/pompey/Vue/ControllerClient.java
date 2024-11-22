package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.PlaceholderTextField;
import fr.afpa.dev.pompey.Utilitaires.Verification;
import fr.afpa.dev.pompey.Modele.DAO.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    //Initialisation des DAO
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
    public ControllerClient(){
        //Initialisation de la DAO
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
        for (Medecin medecin : getMedecin()) {
            MedTraitantModel.addElement(medecin);
        }
        medTraitantComboBox.setModel(MedTraitantModel);

        DefaultComboBoxModel<Mutuelle> mutuelleModel = new DefaultComboBoxModel<>();
        for (Mutuelle mutuelle : getMutuelle()) {
            mutuelleModel.addElement(mutuelle);
        }
        mutuelleComboBox.setModel(mutuelleModel);

        DefaultComboBoxModel<Region> regionModel = new DefaultComboBoxModel<>();
        for (Region region : getRegion()) {
            regionModel.addElement(region);
        }
        regionComboBox.setModel(regionModel);

        //Les Listeners
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
        //Récupération des valeurs des champs
        String nom = nomTextField.getText().trim();
        String prenom = prenomTextField.getText().trim();
        String dateNaissance = dateNaissanceTextField.getText();
        String secusocial = secusocialTextField.getText();
        String cp = cpTextField.getText().trim();
        String telephone = telephoneTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String rueName = rueTextField.getText();
        String villeName = villeTextField.getText();
        Object medTraitant = medTraitantComboBox.getSelectedItem();
        Object mutuelle = mutuelleComboBox.getSelectedItem();
        Object regionSelected = regionComboBox.getSelectedItem();

        //Vérification des champs
        //trouver des champs de saisie vide et le transformer en rouge

        if (nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || secusocial.isEmpty() ||
                telephone.isEmpty() || email.isEmpty() || rueName.isEmpty() || villeName.isEmpty() ||
                medTraitant == null || mutuelle == null || regionSelected == null) {
            Fenetre.Fenetre("Veuillez remplir tous les champs");
            throw new SaisieException();
        }

        //Création de la région


        // 1. Créer des coordonnées
        int newIdCoordonnees = 0;
        boolean coordonnesExist = false;

        for (Coordonnees coordonneesCheck : coordonneesDAO.findAll()) {
            if (coordonneesCheck.getEmail().equals(email) || coordonneesCheck.getTelephone().equals(telephone)) {
                coordonnesExist = true;
                break;
            }
        }
        if (coordonnesExist) {
            Fenetre.Fenetre("Les coordonnées existent déjà");
            throw new SaisieException();
        } else {
            Coordonnees coordonnees = new Coordonnees(
                    email,
                    telephone
            );
            // On récupère l'id des coordonnées
            newIdCoordonnees = coordonneesDAO.create(coordonnees);
        }

        // 2. Créer des adresses
        // on crée la région dans la base de données
        Region nameRegion = null;
        if (regionSelected instanceof RegionDAO) {
            nameRegion = (Region) regionSelected;
        } else if (regionSelected instanceof String) {
            nameRegion = new Region((String) regionSelected);
        }
        int newIdRegion = 0;
        for (Region regionCheck : regionDAO.findAll()) {
            if (regionCheck.getNom().equals(nameRegion.getNom())) {
                Fenetre.Fenetre("La région existe déjà");
                throw new SaisieException();
            } else {
                Region region = new Region(
                        String.valueOf(nameRegion)
                );
                newIdRegion = regionDAO.create(region);
            }
        }

        //on crée la ville dans la base de données
        int newIdVille = 0;
        boolean villeExist = false;
        for (Ville villeCheck : villeDAO.findAll()) {
            if (villeCheck.getNom().equals(villeName)) {
                villeExist = true;
                break;
            }
        }
        if(villeExist){
            Fenetre.Fenetre("La ville existe déjà");
            throw new SaisieException();
        } else {
            Ville ville = new Ville(
                    villeName,
                    cp,
                    newIdRegion
            );
            newIdVille = villeDAO.create(ville);
        }

        //On crée l'adresse
        int newIdAdresse = 0;
        Adresses adresses = new Adresses(
                rueName,
                newIdVille);
        // On récupère l'id des adresses
        newIdAdresse = adressesDAO.create(adresses);


        int newIdMedecin = 0;
        if (medecinDAO.find(((Medecin) medTraitant).getId()) == null) {
            Medecin medecin1 = new Medecin(
                    Verification.NomPrenom(medTraitant.toString(), "Nom du médecin"),
                    Verification.NomPrenom(medTraitant.toString(), "Prénom du médecin")
            );
            newIdMedecin = medecinDAO.create(medecin1);
        } else {
            newIdMedecin = medecinDAO.find(((Medecin) medTraitant).getId()).getId();
        }

        // tester si mutuelle existe ? si non creer mutuelle
        int newIDMutuelle = 0;
        if (mutuelle == null) {
            //Création d'une mutuelle
            Mutuelle mutuelle1 = new Mutuelle(
                    Verification.NomPrenom(mutuelle.toString(), "Nom de la mutuelle")
            );
            //Ajout de la mutuelle à la liste des mutuelles
            newIDMutuelle = mutuelleDAO.create(mutuelle1);
        }

        //Création d'un client(
        Client client = new Client(
                Verification.NomPrenom(nom, "Nom"),
                Verification.NomPrenom(prenom, "Prénom"),
                Verification.SecuSocial(secusocial),
                Verification.BirthDate(dateNaissance),
                newIdMedecin,
                newIdCoordonnees,
                newIdAdresse,
                newIDMutuelle
        );
        clientDAO.create(client);

        //Affichage d'un message de confirmation
        Fenetre.Fenetre("Client enregistrée avec succès");

        //Fermeture de la fenêtre
        this.dispose();

        //Effacer les champs
        effaceToutLesChamps();

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
    private void effaceToutLesChamps(){
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




    //Affichage des listes
    public List<Medecin> getMedecin() {
        return medecinDAO.findAll();
    }

    public List<Mutuelle> getMutuelle() {
        return mutuelleDAO.findAll();
    }

    public List<Region> getRegion() {
        return regionDAO.findAll();
    }








}
