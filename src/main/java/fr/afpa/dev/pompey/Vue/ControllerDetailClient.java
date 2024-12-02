package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.PlaceholderTextField;
import fr.afpa.dev.pompey.Utilitaires.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.afpa.dev.pompey.Modele.DAO.DAOUtils.*;
import static fr.afpa.dev.pompey.Utilitaires.Conversion.convertirDate;
import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.*;
import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.ShowLabelWithTimer;

/**
 * La classe ControllerDetailClient est le contrôleur de la fenêtre de détail du client
 */
public class ControllerDetailClient extends JFrame{

    private JTextField nomTextField;
    private JTextField secusocialTextField;
    private JTextField cpTextField;
    private JTextField prenomTextField;
    private JTextField dateNaissanceTextField;
    private JTextField rueTextField;
    private JTextField villeTextField;
    private JTextField telephoneTextField;
    private JTextField emailTextField;
    private JButton annulerButton;
    private JButton modifierButton;
    private JPanel contentPane;
    private JComboBox medTraitantComboBox;
    private JComboBox mutuelleComboBox;
    private JButton mutuelleDuClientButton;
    private JLabel informationLabel;
    private JLabel coordonneeLabel;
    private JLabel adresseLabel;
    private JLabel contactLabel;
    private JComboBox regionComboBox;

    private ClientDAO clientDAO;
    private MedecinDAO medecinDAO;
    private MutuelleDAO mutuelleDAO;
    private CoordonneesDAO coordonneesDAO;
    private RegionDAO regionDAO;
    private VilleDAO villeDAO;
    private AdressesDAO adressesDAO;

    private int idclient;

    /**
     * Constructeur de la classe ControllerDetailClient
     */
    public ControllerDetailClient(int idclient) {
        this.idclient = idclient;
        //Initialisation des DAO
        clientDAO = new ClientDAO();
        medecinDAO = new MedecinDAO();
        mutuelleDAO = new MutuelleDAO();
        coordonneesDAO = new CoordonneesDAO();
        regionDAO = new RegionDAO();
        villeDAO = new VilleDAO();
        adressesDAO = new AdressesDAO();

        configurerFenetre(this, contentPane, false, "Detail Client");

        // Placeholder
        InsertPlaceholders();
        // Remplir les combobox
        remplirComboxbox();

        // Récupérer les données du client
        chargerLesDonneesClients();

        //Boutons de modifier, annuler et mutuelle du client
        Boutons();
    }

    /**
     * Modifier les informations du client
     *
     * @param idclient L'identifiant du client
     * @throws SaisieException
     */
    private void modifier(int idclient) throws SaisieException {
        Client client = clientDAO.find(idclient);
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String dateNaissance = dateNaissanceTextField.getText();
        String secusocial = secusocialTextField.getText();
        String cp = cpTextField.getText();
        String rueName = rueTextField.getText();
        String villeName = villeTextField.getText();
        String telephone = telephoneTextField.getText();
        String email = emailTextField.getText();
        Medecin medecin = (Medecin) medTraitantComboBox.getSelectedItem();
        Mutuelle mutuelle = (Mutuelle) mutuelleComboBox.getSelectedItem();
        Region regionSelected = (Region) regionComboBox.getSelectedItem();

        int newIdMedecin = checkMedecin(medecin);

        int newIdRegion = checkRegion(regionSelected);

        int newIdVille = checkVille(villeName, cp, newIdRegion);

        int newIdAdresse = checkAdresse(rueName, newIdVille);

        checkCoordonnees(email, telephone, client);

        int newIDMutuelle = checkMutuelle(mutuelle);
        int newIdCoordonnees = client.getCoordonnees().getId();

        //Création d'un client(
        Client clientModif = new Client(
                Verification.NomPrenom(nom, "Nom"),
                Verification.NomPrenom(prenom, "Prénom"),
                Verification.SecuSocial(secusocial),
                Verification.BirthDate(dateNaissance),
                newIdMedecin,
                newIdCoordonnees,
                newIdAdresse,
                newIDMutuelle
        );
        clientDAO.update(clientModif);
    }

    /**
     * Vérifier si la mutuelle existe
     *
     * @param mutuelle Le nom de la mutuelle
     * @return L'identifiant de la mutuelle
     * @throws SaisieException
     */
    private int checkMutuelle(Mutuelle mutuelle) throws SaisieException {
        int newIDMutuelle = 0;
        boolean mutuelleExist = false;
        for (Mutuelle mutuelleCheck : mutuelleDAO.findAll()) {
            if (mutuelleCheck.getNom().equals(mutuelle.getNom())) {
                newIDMutuelle = mutuelleCheck.getId();
                mutuelleExist = true;
            }
        }
        if(!mutuelleExist){
            Mutuelle mutuelle1 = new Mutuelle(
                    mutuelle.getNom()
            );
            newIDMutuelle = mutuelleDAO.create(mutuelle1);
        }
        return newIDMutuelle;
    }

    /**
     * Vérifier si l'adresse existe
     *
     * @param rueName  Le nom de la rue
     * @param newIdVille L'identifiant de la ville
     * @return L'identifiant de l'adresse
     */
    private int checkAdresse(String rueName, int newIdVille) {
        int newIdAdresse = 0;
        boolean adresseExist = false;
        for (Adresses adresseCheck : adressesDAO.findAll()) {
            if (adresseCheck.getRue().equals(rueName)) {
                newIdAdresse = adresseCheck.getId();
                adresseExist = true;
            }
        }

        if(adresseExist){
            Adresses adresse = adressesDAO.find(newIdAdresse);
            if(adresse.getVille().getId() != newIdVille){
                Adresses adresseUpdate = new Adresses(
                        newIdAdresse,
                        rueName,
                        newIdVille
                );
                adressesDAO.update(adresseUpdate);
            }
        }else{
            Adresses adresse = new Adresses(
                    rueName,
                    newIdVille
            );
            newIdAdresse = adressesDAO.create(adresse);
        }
        return newIdAdresse;
    }

    /**
     * Vérifier si la ville existe
     *
     * @param villeName Le nom de la ville
     * @param cp        Le code postal
     * @param newIdRegion L'identifiant de la région
     * @return L'identifiant de la ville
     */
    private int checkVille(String villeName, String cp, int newIdRegion) {
        int newIdVille = 0;
        boolean villeExist = false;
        for (Ville villeCheck : villeDAO.findAll()) {
            if (villeCheck.getNom().equals(villeName)) {
                newIdVille = villeCheck.getId();
                villeExist = true;
            }
        }
//TODO: A FIX!
        if(villeExist){
            Ville ville = villeDAO.find(newIdVille);
            if(ville.getRegion().getId() != newIdRegion){
                Ville villeUpdate = new Ville(
                        newIdVille,
                        villeName,
                        ville.getCp(),
                        newIdRegion
                );
                villeDAO.update(villeUpdate);
            }
        }else{
            Ville ville = new Ville(
                    villeName,
                    cp,
                    newIdRegion
            );
            newIdVille = villeDAO.create(ville);
        }
        return newIdVille;
    }

    /**
     * Vérifier si les coordonnées du client ont changé
     *
     * @param email     L'email du client
     * @param telephone Le téléphone du client
     * @param client    Le client
     * @throws SaisieException
     */
    private void checkCoordonnees(String email, String telephone, Client client) throws SaisieException {
        if(email.isEmpty() && telephone.isEmpty()) {
            ShowLabelWithBlinker(informationLabel, "Veuillez remplir les champs", Color.RED);
            throw new SaisieException("Veuillez remplir les champs");
        }else{
            //Mettre à jour les coordonnées du client
            if(!client.getCoordonnees().getEmail().equals(email) && !client.getCoordonnees().getTelephone().equals(telephone)) {
                Coordonnees coordonnees = new Coordonnees(
                        client.getCoordonnees().getId(),
                        email,
                        telephone
                );
                coordonneesDAO.update(coordonnees);
            }else{
                throw new SaisieException("Le client n'a pas changé ses coordonnées");
            }
        }
    }

    /**
     * Vérifier si la région existe
     *
     * @param region La région
     * @return L'identifiant de la région
     * @throws SaisieException
     */
    private int checkRegion(Region region) throws SaisieException {
        int newIdRegion;
        if (region instanceof Region) {
            newIdRegion = region.getId();
        } else if (region.getNom() instanceof String) {
            Region newRegion = new Region(region.getNom());
            newIdRegion = regionDAO.create(newRegion);
        } else {
            ShowLabelWithTimer(informationLabel, "Sélection invalide pour la région.", Color.RED);
            throw new SaisieException("Sélection invalide pour la région.");
        }
        return newIdRegion;
    }

    /**
     * Vérifier si le médecin existe
     *
     * @param medecin Le médecin
     * @return L'identifiant du médecin
     * @throws SaisieException
     */
    private int checkMedecin(Medecin medecin) throws SaisieException {
        int newIdMedecin;
        if (medecin instanceof Medecin) {
            newIdMedecin = medecin.getId();
        } else if (medecin.getNom() instanceof String) {
            String[] parts = medecin.getNom().split(" ");
            if (parts.length >= 2) {
                Medecin newMedecin = new Medecin(parts[0], parts[1]);
                newIdMedecin = medecinDAO.create(newMedecin);
            } else {
                ShowLabelWithTimer(informationLabel, "Nom ou prénom du médecin incomplet.", Color.RED);
                throw new SaisieException("Nom ou prénom du médecin incomplet.");
            }
        } else {
            ShowLabelWithTimer(informationLabel, "Sélection invalide pour le médecin traitant.", Color.RED);
            throw new SaisieException("Sélection invalide pour le médecin traitant.");
        }
        return newIdMedecin;
    }

    /**
     * Ouvre la fenêtre de détail de la mutuelle du client
     *
     * @param id L'identifiant de la mutuelle
     * @throws SaisieException
     */
    private void mutuelleDuClient(int id) throws SaisieException {
        ControllerDetailMutuelle controllerDetailMutuelle = new ControllerDetailMutuelle(id);
        controllerDetailMutuelle.setVisible(true);
    }
    /**
     * Définit les données du client dans les champs de texte
     *
     * @param textField Le champ de texte
     * @param data      Les données du client
     */
    private void setTextFieldData(JTextField textField, String data) {
        if (data != null && !data.isEmpty()) {
            textField.setText(data);
            textField.setForeground(Color.BLACK); // Texte en noir si les données existent
        }
    }

    /**
     * Remplir les combobox
     */
    private void remplirComboxbox(){
        medTraitantComboBox.setModel(new DefaultComboBoxModel<>(getMedecins().toArray(new Medecin[0])));
        mutuelleComboBox.setModel(new DefaultComboBoxModel<>(getMutuelles().toArray(new Mutuelle[0])));
        regionComboBox.setModel(new DefaultComboBoxModel<>(getRegions().toArray(new Region[0])));
        regionComboBox.setEditable(false);
    }

    /**
     * Insérer les placeholders dans les champs de texte
     */
    private void InsertPlaceholders(){
        PlaceholderTextField.setPlaceholder(nomTextField, "Nom");
        PlaceholderTextField.setPlaceholder(prenomTextField, "Prénom");
        PlaceholderTextField.setPlaceholder(dateNaissanceTextField, "JJ/MM/AAAA");
        PlaceholderTextField.setPlaceholder(secusocialTextField, "Numéro de sécurité sociale");
        PlaceholderTextField.setPlaceholder(cpTextField, "Code postal");
        PlaceholderTextField.setPlaceholder(telephoneTextField, "Téléphone");
        PlaceholderTextField.setPlaceholder(emailTextField, "Email");
        PlaceholderTextField.setPlaceholder(rueTextField, "Rue");
        PlaceholderTextField.setPlaceholder(villeTextField, "Ville");
    }

    /**
     * Charger les données du client
     *  @idclient L'identifiant du client
     *  @throws SaisieException
     */
    private void chargerLesDonneesClients(){
        Client client = clientDAO.find(idclient);
        Adresses adresses = adressesDAO.find(client.getAdresses().getId());
        Coordonnees coordonnees = coordonneesDAO.find(client.getCoordonnees().getId());
        Ville ville = villeDAO.find(adresses.getVille().getId());
        Region region = regionDAO.find(ville.getRegion().getId());
        setTextFieldData(nomTextField, client.getNom());
        setTextFieldData(prenomTextField, client.getPrenom());

        String dateDeNaissance = convertirDate(client.getDateNaissance());
        setTextFieldData(dateNaissanceTextField, dateDeNaissance);

        setTextFieldData(secusocialTextField, client.getNumeroSecuClient());
        setTextFieldData(cpTextField, ville.getCp());
        setTextFieldData(rueTextField, adresses.getRue());
        setTextFieldData(villeTextField, ville.getNom());
        setTextFieldData(telephoneTextField, coordonnees.getTelephone());
        setTextFieldData(emailTextField, coordonnees.getEmail());
        mutuelleComboBox.setSelectedItem(client.getMutuelle());
        medTraitantComboBox.setSelectedItem(client.getMedecin());
        regionComboBox.setSelectedItem(region.getNom());
    }

    /**
     * Les actions des boutons modifier, annuler et mutuelle du client
     * @throws SaisieException
     */
    private void Boutons(){
        annulerButton.addActionListener(e -> this.dispose());

        modifierButton.addActionListener(e -> {
            try {
                modifier(idclient);
            } catch (SaisieException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        // Ouvre la fenêtre de détail de la mutuelle du client
        mutuelleDuClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mutuelleDuClient(clientDAO.find(idclient).getMutuelle().getId());
                } catch (SaisieException ex) {
                    Fenetre.Fenetre("Erreur lors de l'ouverture de la fenêtre de mutuelle");
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}