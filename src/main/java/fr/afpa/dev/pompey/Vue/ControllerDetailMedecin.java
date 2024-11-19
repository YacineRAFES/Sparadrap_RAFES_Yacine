package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.Verification;

import javax.swing.*;
import java.awt.*;

/**
 * La classe ControllerDetailMedecin est le contrôleur de la fenêtre de détail du médecin
 */
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
    private JLabel informationLabel;

    /**
     * Constructeur de la classe ControllerDetailMedecin
     *
     * @param idmedecin L'identifiant du médecin
     */
    public ControllerDetailMedecin(Medecin idmedecin) {
        setTitle("Détail Médecin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // TODO SELECT * FROM medecin WHERE idmedecin = idmedecin
        setTextFieldData(nomTextField, idmedecin.getNomMedecin());
        setTextFieldData(prenomTextField, idmedecin.getPrenomMedecin());
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
            ControllerListeOrdonnanceIdMed controllerListeOrdonnanceIdMed = new ControllerListeOrdonnanceIdMed(idmedecin);
            controllerListeOrdonnanceIdMed.setVisible(true);
        });

        listesDesClientsButton.addActionListener(e -> {
            ControllerListeClientIdMed controllerListeClientIdMed = new ControllerListeClientIdMed(idmedecin);
            controllerListeClientIdMed.setVisible(true);
        });
    }

    /**
     * Méthode pour modifier un médecin
     *
     * @param idmedecin L'identifiant du médecin
     * @throws SaisieException
     */
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

    /**
     * Construire un médecin
     *
     * @return Le médecin
     * @throws SaisieException
     */
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

    /**
     * Mettre à jour les informations du médecin
     *
     * @param idmedecin     L'identifiant du médecin
     * @param updatedMedecin Le médecin mis à jour
     * @throws SaisieException
     */
    private void updateMedecinInfo(Medecin idmedecin, Medecin updatedMedecin) throws SaisieException {
        idmedecin.setNomMedecin(updatedMedecin.getNomMedecin());
        idmedecin.setPrenomMedecin(updatedMedecin.getPrenomMedecin());
        idmedecin.setRue(updatedMedecin.getRue());
        idmedecin.setCodePostal(updatedMedecin.getCodePostal());
        idmedecin.setVille(updatedMedecin.getVille());
        idmedecin.setTelephone(updatedMedecin.getTelephone());
        idmedecin.setEmail(updatedMedecin.getEmail());
        idmedecin.setNumAgreement(updatedMedecin.getNumAgreement());
        idmedecin.setSpecialite(updatedMedecin.getSpecialite());
    }

    /**
     * Mettre les données dans les champs de texte
     *
     * @param textField Le champ de texte
     * @param data      Les données
     */
    private void setTextFieldData(JTextField textField, String data) {
        if (data != null && !data.isEmpty()) {
            textField.setText(data);
            textField.setForeground(Color.BLACK); // Texte en noir si les données existent
        }
    }

}
