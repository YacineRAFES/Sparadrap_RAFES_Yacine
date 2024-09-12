package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.AjouterPlaceholder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMedecin extends JFrame {
    private JPanel contentPane;
    private JTextField nomTextField;
    private JLabel coordonneeLabel;
    private JLabel adresseLabel;
    private JLabel ContactLabel;
    private JTextField numAgreementTextField;
    private JTextField cpTextField;
    private JTextField prenomTextField;
    private JTextField rueTextField;
    private JTextField villeTextField;
    private JTextField telephoneTextField;
    private JTextField emailTextField;
    private JButton annulerButton;
    private JButton creerButton;
    private JTextField specialisteTextField;
    private JLabel specialisteLabel;


    public ControllerMedecin(){
        setTitle("Medecin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Les saisies avec placeholder
        AjouterPlaceholder(nomTextField, "Nom");
        AjouterPlaceholder(numAgreementTextField, "numéro d'agreement");
        AjouterPlaceholder(cpTextField, "Code postal");
        AjouterPlaceholder(prenomTextField, "Prénom");
        AjouterPlaceholder(rueTextField, "Rue");
        AjouterPlaceholder(villeTextField, "Ville");
        AjouterPlaceholder(telephoneTextField, "Telephone");
        AjouterPlaceholder(emailTextField, "Email");
        AjouterPlaceholder(specialisteTextField, "Spécialiste");

        //Créer un medecin


        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    creerMedecin();
                } catch (SaisieException ex) {
                    new RuntimeException(ex);
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerMedecin();
            }
        });
    }

    private void annulerMedecin() {
        this.dispose();
    }

    private void creerMedecin() throws SaisieException {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String numAgreement = numAgreementTextField.getText();
        String rue = rueTextField.getText();
        String cp = cpTextField.getText();
        String ville = villeTextField.getText();
        String telephone = telephoneTextField.getText();
        String email = emailTextField.getText();
        String specialiste = specialisteTextField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || numAgreement.isEmpty() || rue.isEmpty() || cp.isEmpty() || ville.isEmpty() || telephone.isEmpty() || email.isEmpty() || specialiste.isEmpty()) {
            Fenetre.Fenetre("Veuillez remplir tous les champs");
        } else {
            Medecin medecin = new Medecin(Verification.NomPrenom(nom, "Nom"),
                    Verification.NomPrenom(prenom, "Prénom"),
                    rue,
                    Verification.CodePostal(cp),
                    Verification.NomPrenom(ville, "Ville"),
                    Verification.Telephone(telephone),
                    Verification.Email(email),
                    numAgreement,
                    Verification.NomPrenom(specialiste, "Spécialiste"));
            GestionListe.getMedecin().add(medecin);
            Fenetre.Fenetre("Medecin créé");
            this.dispose();
        }
    }
}
