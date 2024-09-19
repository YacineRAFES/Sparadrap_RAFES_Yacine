package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.afpa.dev.pompey.Modele.GestionListe.getMedecin;
import static fr.afpa.dev.pompey.Modele.GestionListe.getMutuelle;
import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.AjouterPlaceholder;

public class ControllerDetailClient extends JFrame{
    private JLabel coordonneeLabel;
    private JTextField nomTextField;
    private JTextField secusocialTextField;
    private JLabel adresseLabel;
    private JTextField cpTextField;
    private JTextField prenomTextField;
    private JTextField dateNaissanceTextField;
    private JTextField rueTextField;
    private JTextField villeTextField;
    private JLabel contactLabel;
    private JTextField telephoneTextField;
    private JTextField emailTextField;
    private JButton annulerButton;
    private JButton modifierButton;
    private JPanel contentPane;
    private JComboBox medTraitantComboBox;
    private JComboBox mutuelleComboBox;
    private JButton mutuelleDuClientButton;

    public ControllerDetailClient(Client idclient) {
        setTitle("Détail Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Remplir les combobox
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

        AjouterPlaceholder(nomTextField, "Nom");
        AjouterPlaceholder(prenomTextField, "Prénom");
        AjouterPlaceholder(dateNaissanceTextField, "Date de naissance");
        AjouterPlaceholder(secusocialTextField, "Num. sécurité social");
        AjouterPlaceholder(cpTextField, "Code postal");
        AjouterPlaceholder(telephoneTextField, "Numéro de téléphone");
        AjouterPlaceholder(emailTextField, "Email");
        AjouterPlaceholder(rueTextField, "Rue");
        AjouterPlaceholder(villeTextField, "Ville");

        setTextFieldData(nomTextField, idclient.getNom());
        setTextFieldData(prenomTextField, idclient.getPrenom());
        setTextFieldData(dateNaissanceTextField, idclient.getDateNaissance());
        setTextFieldData(secusocialTextField, idclient.getNumeroSecuClient());
        setTextFieldData(cpTextField, idclient.getCodePostal());
        setTextFieldData(rueTextField, idclient.getAdresse());
        setTextFieldData(villeTextField, idclient.getVille());
        setTextFieldData(telephoneTextField, idclient.getTelephone());
        setTextFieldData(emailTextField, idclient.getEmail());
        mutuelleComboBox.setSelectedItem(idclient.getMutuelle());
        medTraitantComboBox.setSelectedItem(idclient.getMedecin());

        annulerButton.addActionListener(e -> this.dispose());

        modifierButton.addActionListener(e -> {
            try {
                modifier(idclient);
                dispose();
            } catch (SaisieException ex) {
                Fenetre.Fenetre("Erreur lors de la saisie");
            }
        });

        // Ouvre la fenêtre de détail de la mutuelle du client
        mutuelleDuClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mutuelleDuClient();
                } catch (SaisieException ex) {
                    Fenetre.Fenetre("Erreur lors de l'ouverture de la fenêtre de mutuelle");
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    // Ouvre la fenêtre de détail de la mutuelle du client
    private void mutuelleDuClient() throws SaisieException {
        Mutuelle mutuelle = (Mutuelle) mutuelleComboBox.getSelectedItem();
        ControllerDetailMutuelle controllerDetailMutuelle = new ControllerDetailMutuelle(mutuelle);
        controllerDetailMutuelle.setVisible(true);
    }

    // La modification du client a été effectué avec succès sinon une exception est levée
    private void modifier(Client idclient) throws SaisieException {
        Client client = buildClient();
        if (client == null) return; // Saisie incorrecte
        try{
            updateClientInfo(idclient, client);
            Fenetre.Fenetre("Client modifié avec succès");
        } catch (SaisieException e) {
            Fenetre.Fenetre("Une erreur s'est produite lors de la modification du client" + e.getMessage());
            new RuntimeException(e);
        }
    }

    // Crée un client à partir des champs de texte
    private Client buildClient() throws SaisieException {
        String nom = Verification.NomPrenom(nomTextField.getText(), "Nom");
        String prenom = Verification.NomPrenom(prenomTextField.getText(), "Prénom");
        String dateNaissance = Verification.BirthDate(dateNaissanceTextField.getText());
        String secuSocial = Verification.SecuSocial(secusocialTextField.getText());
        Object mutuelleObject = mutuelleComboBox.getSelectedItem();
        Object medTraitantObject = medTraitantComboBox.getSelectedItem();
        String cp = Verification.CodePostal(cpTextField.getText());
        String rue = rueTextField.getText();
        String ville = Verification.NomPrenom(villeTextField.getText(), "Ville");
        String telephone = Verification.Telephone(telephoneTextField.getText());
        String email = Verification.Email(emailTextField.getText());

        return new Client(nom, prenom, rue, cp, ville, telephone, email, secuSocial, dateNaissance, (Mutuelle) mutuelleObject, (Medecin) medTraitantObject);

    }

    // Met à jour les informations du client
    private void updateClientInfo(Client idclient, Client updatedClient) throws SaisieException {
        idclient.setNom(updatedClient.getNom());
        idclient.setPrenom(updatedClient.getPrenom());
        idclient.setAdresse(updatedClient.getAdresse());
        idclient.setCodePostal(updatedClient.getCodePostal());
        idclient.setVille(updatedClient.getVille());
        idclient.setTelephone(updatedClient.getTelephone());
        idclient.setEmail(updatedClient.getEmail());
        idclient.setNumeroSecuClient(updatedClient.getNumeroSecuClient());
        idclient.setDateNaissance(updatedClient.getDateNaissance());
        idclient.setMutuelle(updatedClient.getMutuelle());
        idclient.setMedecin(updatedClient.getMedecin());
    }

    // Remplir les champs de texte avec les données du client
    private void setTextFieldData(JTextField textField, String data) {
        if (data != null && !data.isEmpty()) {
            textField.setText(data);
            textField.setForeground(Color.BLACK); // Texte en noir si les données existent
        }
    }
}