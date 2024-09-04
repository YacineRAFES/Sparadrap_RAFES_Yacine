package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel;
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
    private JTextField mutuelleTextField;
    private JTextField medTraitantTextField;
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
        AjouterPlaceholder(mutuelleTextField, "Mutuelle");
        AjouterPlaceholder(medTraitantTextField, "Médecin Traitant");
        AjouterPlaceholder(cpTextField, "Code postal");
        AjouterPlaceholder(telephoneTextField, "Numéro de téléphone");
        AjouterPlaceholder(emailTextField, "Email");
        AjouterPlaceholder(rueTextField, "Rue");
        AjouterPlaceholder(villeTextField, "Ville");

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


    private void enregistrerClient() throws SaisieException {
        String nom = nomTextField.getText().trim();
        String prenom = prenomTextField.getText().trim();
        String dateNaissance = dateNaissanceTextField.getText();
        String secusocial = secusocialTextField.getText();
        String mutuelle = mutuelleTextField.getText();
        String medTraitant = medTraitantTextField.getText();
        String cp = cpTextField.getText().trim();
        String telephone = telephoneTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String rue = rueTextField.getText();
        String ville = villeTextField.getText().trim();


        if(nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || secusocial.isEmpty() ||
                mutuelle.isEmpty() || medTraitant == null || cp.isEmpty() || telephone.isEmpty() ||
                email.isEmpty() || rue.isEmpty() || ville.isEmpty()){
            Fenetre.Fenetre("Veuillez remplir tous les champs");
            throw new SaisieException();
        }

        String[] medTraitantSplit = medTraitant.split("\\s+", 2); // Limiter à deux parties
        if (medTraitantSplit.length != 2) {
            Fenetre.Fenetre("Le nom et prénom du médecin traitant doivent être séparés par un espace");
            throw new SaisieException();
        }

        String medTraitantNom = medTraitantSplit[0].trim();
        String medTraitantPrenom = medTraitantSplit[1].trim();

        //Transformations en Object
        Mutuelle mutuelleObj = new Mutuelle(Verification.NomPrenom(mutuelle, "Mutuelle"));
        Medecin medecinObj = new Medecin(
                Verification.NomPrenom(medTraitantNom, "le médecin traitant"),
                Verification.NomPrenom(medTraitantPrenom, "le prénom du medecin traitant"));

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
                mutuelleObj,
                medecinObj
        );

        Medecin medecin = new Medecin(
                Verification.NomPrenom(medTraitantNom, "nom du médecin"),
                Verification.NomPrenom(medTraitantPrenom, "prénom du médecin")
        );

        addClient(client);
        addMedecin(medecin);
        addMutuelle(mutuelleObj);

        Fenetre.Fenetre("Client enregistrée avec succès");
        this.dispose();

        System.out.println(getClient());
        System.out.println(getMedecin());

        effaceToutLesChamps();

    }
    private void annulerClient() {
        effaceToutLesChamps();
    }

    private void effaceToutLesChamps(){
        nomTextField.setText("");
        prenomTextField.setText("");
        dateNaissanceTextField.setText("");
        secusocialTextField.setText("");
        mutuelleTextField.setText("");
        medTraitantTextField.setText("");
        cpTextField.setText("");
        telephoneTextField.setText("");
        emailTextField.setText("");
        rueTextField.setText("");
        villeTextField.setText("");
    }
}
