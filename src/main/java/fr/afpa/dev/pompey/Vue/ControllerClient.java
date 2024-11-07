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

    private ClientDAO clientDAO;
    private CoordonneesDAO coordonneesDAO;
    private AdressesDAO adressesDAO;
    private MedecinDAO medecinDAO;
    private MutuelleDAO mutuelleDAO;

    /**
     * Constructeur de la classe ControllerClient
     */
    public ControllerClient(){
        //Initialisation des DAO
        this.coordonneesDAO = new CoordonneesDAO();
        this.adressesDAO = new AdressesDAO();
        this.clientDAO = new ClientDAO();
        this.medecinDAO = new MedecinDAO();
        this.mutuelleDAO = new MutuelleDAO();


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
        String rue = rueTextField.getText();
        String ville = villeTextField.getText().trim();
        Object medTraitant = medTraitantComboBox.getSelectedItem();
        Object mutuelle = mutuelleComboBox.getSelectedItem();


        //Vérification des champs
        //trouver un champs de saisie vide et le transformer en rouge

        if(nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || secusocial.isEmpty() || cp.isEmpty() ||
                telephone.isEmpty() || email.isEmpty() || rue.isEmpty() || ville.isEmpty()){
            Fenetre.Fenetre("Veuillez remplir tous les champs");
            throw new SaisieException();
        }

        // 1. Créer des coordonnées
        Coordonnees coordonnees = new Coordonnees(
                email,
                telephone);
        // On récupère l'id des coordonnées
        int newIdCoordonnees = addCoordonnees(coordonnees);
        // 2. Créer des adresses

        Adresses adresses = new Adresses(
                rue,
                ville);
        // On récupère l'id des adresses
        int newIdAdresse = addAdresses(adresses);
        // newIdAdresse = AdresseDAO.create(adresse);
        // tester si medecin existe ? si non creer medecin
        // if comboBoxMedecin.getSelectedItem() == null inertion medecin
            //sinon  newIdMedecin = comboBoxMedecin.getSelectedItem().getId();
        // tester si mutuelle existe ? si non creer mutuelle

        //Création d'un client(
        Client client = new Client(
                int id,
                Verification.NomPrenom(nom, "Nom"),
                Verification.NomPrenom(prenom, "Prénom"),
                rue,
                Verification.CodePostal(cp),
                Verification.NomPrenom(ville, "ville"),
                Verification.Telephone(telephone),
                Verification.Email(email),
                Verification.SecuSocial(secusocial),
                Verification.BirthDate(dateNaissance),
                (Mutuelle) mutuelle,
                ((Medecin) medTraitant).getNomMedecin(),
                ((Medecin) medTraitant).getPrenomMedecin()
        );


        //Ajout du client à la liste des clients
        addClient(client);

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

    public int addCoordonnees(Coordonnees coordonnees) {
        return coordonneesDAO.create(coordonnees);
    }

    public int addAdresses(Adresses adresses) {
        return adressesDAO.create(adresses);
    }

    public int addClient(Client client) {
        return clientDAO.create(client);
    }

    public int addMedecin(Medecin medecin) {
        return medecinDAO.create(medecin);
    }


}
