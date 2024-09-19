package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.afpa.dev.pompey.Modele.GestionListe.*;
import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.AjouterPlaceholder;

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

    public ControllerClient(){

        setTitle("Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        AjouterPlaceholder(nomTextField, "Nom");
        AjouterPlaceholder(prenomTextField, "Prénom");
        AjouterPlaceholder(dateNaissanceTextField, "Date de naissance");
        AjouterPlaceholder(secusocialTextField, "Num. sécurité social");
        AjouterPlaceholder(cpTextField, "Code postal");
        AjouterPlaceholder(telephoneTextField, "Numéro de téléphone");
        AjouterPlaceholder(emailTextField, "Email");
        AjouterPlaceholder(rueTextField, "Rue");
        AjouterPlaceholder(villeTextField, "Ville");

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

    //Méthode pour enregistrer un client
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
        if(nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || secusocial.isEmpty() || cp.isEmpty() ||
                telephone.isEmpty() || email.isEmpty() || rue.isEmpty() || ville.isEmpty()){
            Fenetre.Fenetre("Veuillez remplir tous les champs");
            throw new SaisieException();
        }

        //Création d'un client
        Client client = new Client(
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
                (Medecin) medTraitant
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

    //Méthode pour annuler l'enregistrement d'un client
    private void annulerClient() {
        effaceToutLesChamps();
    }

    //Méthode pour effacer tous les champs
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
}
