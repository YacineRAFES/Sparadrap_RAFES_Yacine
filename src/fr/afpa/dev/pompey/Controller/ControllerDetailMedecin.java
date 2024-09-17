package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import javax.swing.*;
import java.awt.*;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.AjouterPlaceholder;

public class ControllerDetailMedecin extends JFrame {
    private JPanel contentPane;
    private JLabel coordonneeLabel;
    private JTextField nomTextField;
    private JTextField numAgreementTextField;
    private JLabel adresseLabel;
    private JTextField cpTextField;
    private JTextField prenomTextField;
    private JTextField rueTextField;
    private JTextField villeTextField;
    private JLabel ContactLabel;
    private JTextField telephoneTextField;
    private JTextField emailTextField;
    private JLabel specialisteLabel;
    private JTextField specialisteTextField;
    private JButton annulerButton;
    private JButton modifierButton;
    private JLabel titreLabel;
    private JButton listesDesOrdonnancesButton;
    private JButton listesDesClientsButton;

    public ControllerDetailMedecin(Medecin idmedecin) {
        setTitle("Détail Médecin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        AjouterPlaceholder(nomTextField, "Nom");
        AjouterPlaceholder(prenomTextField, "Prénom");
        AjouterPlaceholder(cpTextField, "Code postal");
        AjouterPlaceholder(telephoneTextField, "Numéro de téléphone");
        AjouterPlaceholder(emailTextField, "Email");
        AjouterPlaceholder(rueTextField, "Rue");
        AjouterPlaceholder(villeTextField, "Ville");
        AjouterPlaceholder(numAgreementTextField, "Numéro d'agreement");
        AjouterPlaceholder(specialisteTextField, "Spécialiste");

        setTextFieldData(nomTextField, idmedecin.getNom());
        setTextFieldData(prenomTextField, idmedecin.getPrenom());
        setTextFieldData(cpTextField, idmedecin.getCodePostal());
        setTextFieldData(rueTextField, idmedecin.getRue());
        setTextFieldData(villeTextField, idmedecin.getVille());
        setTextFieldData(telephoneTextField, idmedecin.getTelephone());
        setTextFieldData(emailTextField, idmedecin.getEmail());
        setTextFieldData(numAgreementTextField, idmedecin.getNumAgreement());
        setTextFieldData(specialisteTextField, idmedecin.getSpecialite());

        annulerButton.addActionListener(e -> this.dispose());

        modifierButton.addActionListener(e -> {
            try {
                modifier(idmedecin);
            } catch (SaisieException ex) {
                Fenetre.Fenetre("Erreur lors de la saisie");
            }
        });

        listesDesOrdonnancesButton.addActionListener(e -> {
            ControllerListeOrdonnance controllerListeOrdonnance = new ControllerListeOrdonnance(idmedecin);
            controllerListeOrdonnance.setVisible(true);
        });
    }

    // La modification du client a été effectué avec succès sinon une exception est levée
    private void modifier(Medecin idmedecin) throws SaisieException {
        Medecin medecin = buildMedecin();
        if (medecin == null) return; // Saisie incorrecte
        try{
            updateMedecinInfo(idmedecin, medecin);
            Fenetre.Fenetre("Medecin modifié avec succès");
            this.dispose();
        }catch (SaisieException e){
            Fenetre.Fenetre("Erreur lors de la modification du medecin");
        }
    }

    // Crée un client à partir des champs de texte
    private Medecin buildMedecin() throws SaisieException {
        String nom = Verification.NomPrenom(nomTextField.getText(), "Nom");
        String prenom = Verification.NomPrenom(prenomTextField.getText(), "Prénom");
        String cp = Verification.CodePostal(cpTextField.getText());
        String rue = rueTextField.getText();
        String ville = Verification.NomPrenom(villeTextField.getText(), "Ville");
        String telephone = Verification.Telephone(telephoneTextField.getText());
        String email = Verification.Email(emailTextField.getText());
        String numAgreement = numAgreementTextField.getText();
        String specialiste = specialisteTextField.getText();

        return new Medecin(nom, prenom, rue, cp, ville, telephone, email, numAgreement, specialiste);
    }

    // Met à jour les informations du client
    private void updateMedecinInfo(Medecin idmedecin, Medecin updatedMedecin) throws SaisieException {
        idmedecin.setNom(updatedMedecin.getNom());
        idmedecin.setPrenom(updatedMedecin.getPrenom());
        idmedecin.setRue(updatedMedecin.getRue());
        idmedecin.setCodePostal(updatedMedecin.getCodePostal());
        idmedecin.setVille(updatedMedecin.getVille());
        idmedecin.setTelephone(updatedMedecin.getTelephone());
        idmedecin.setEmail(updatedMedecin.getEmail());
        idmedecin.setNumAgreement(updatedMedecin.getNumAgreement());
        idmedecin.setSpecialite(updatedMedecin.getSpecialite());
    }

    private void setTextFieldData(JTextField textField, String data) {
        if (data != null && !data.isEmpty()) {
            textField.setText(data);
            textField.setForeground(Color.BLACK); // Texte en noir si les données existent
        }
    }

}
