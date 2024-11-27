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
import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.ShowLabelWithBlinker;
import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.configurerFenetre;

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

        //Vérifier si les données coordonnées(email, telephone) sont vides
        if(email != null && email.isEmpty() && telephone.isEmpty()){
            if(client.getCoordonnees().getEmail().equals(email) && client.getCoordonnees().getTelephone().equals(telephone)){
                //Mettre à jour les coordonnées du client
                Coordonnees coordonnees = new Coordonnees(
                        client.getCoordonnees().getId(),
                        telephone,
                        email
                );
                coordonneesDAO.update(coordonnees);
            }
        }else{
            ShowLabelWithBlinker(informationLabel, "Veuillez remplir les champs", Color.RED);
            throw new SaisieException("Veuillez remplir les champs");
        }

        Region nameRegion = null;
        if (regionSelected instanceof Region) {
            nameRegion = regionDAO.find((regionSelected).getId());
        } else if (regionSelected.getNom() instanceof String) {
            String getRegionNameString = regionSelected.getNom();
            nameRegion = new Region(getRegionNameString);
        }

        int newIdRegion = 0;
        for (Region regionCheck : getRegions()) {
            if (regionCheck.getNom().equals(nameRegion)) {
                break;
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

        int newIdMedecin = medecin.getId();
        int newIDMutuelle = mutuelle.getId();
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
        regionComboBox.setEditable(true);
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
        setTextFieldData(dateNaissanceTextField, client.getDateNaissance());
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