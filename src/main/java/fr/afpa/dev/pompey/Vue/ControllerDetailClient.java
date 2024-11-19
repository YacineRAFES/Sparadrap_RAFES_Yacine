package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.PlaceholderTextField;
import fr.afpa.dev.pompey.Utilitaires.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ControllerDetailClient est le contrôleur de la fenêtre de détail du client
 */
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

    /**
     * Constructeur de la classe ControllerDetailClient
     *
     * @param idclient L'identifiant du client
     */
    public ControllerDetailClient(Client idclient) {
        setTitle("Détail Client");
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

        // Remplir les combobox
        DefaultComboBoxModel<Medecin> MedTraitantModel = new DefaultComboBoxModel<>();
        // TODO : Refactor this to DAO medecin
        for (Medecin medecin : getMedecin()) {
            MedTraitantModel.addElement(medecin);
        }
        medTraitantComboBox.setModel(MedTraitantModel);

        DefaultComboBoxModel<Mutuelle> mutuelleModel = new DefaultComboBoxModel<>();
        // TODO : Refactor this to DAO mutuelle
        for (Mutuelle mutuelle : getMutuelle()) {
            mutuelleModel.addElement(mutuelle);
        }
        mutuelleComboBox.setModel(mutuelleModel);

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
                    mutuelleDuClient();
                } catch (SaisieException ex) {
                    Fenetre.Fenetre("Erreur lors de l'ouverture de la fenêtre de mutuelle");
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Ouvre la fenêtre de détail de la mutuelle du client
     *
     * @throws SaisieException si une erreur survient lors de l'ouverture de la fenêtre de mutuelle
     */
    private void mutuelleDuClient() throws SaisieException {
        Mutuelle mutuelle = (Mutuelle) mutuelleComboBox.getSelectedItem();
        ControllerDetailMutuelle controllerDetailMutuelle = new ControllerDetailMutuelle(mutuelle);
        controllerDetailMutuelle.setVisible(true);
    }

    /**
     * Modifie les informations du client
     *
     * @param idclient Le client à modifier
     * @throws SaisieException si une erreur survient lors de la modification du client
     */
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

    /**
     * Construit un client à partir des données saisies
     *
     * @return Le client construit
     * @throws SaisieException si une erreur survient lors de la construction du client
     */
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

        return new Client(nom, prenom, rue, cp, ville, telephone, email, secuSocial, dateNaissance, (Mutuelle) mutuelleObject, ((Medecin) medTraitantObject).getNomMedecin(), ((Medecin) medTraitantObject).getPrenomMedecin());

    }

    /**
     * Met à jour les informations du client
     *
     * @param idclient      Le client à mettre à jour
     * @param updatedClient Le client mis à jour
     * @throws SaisieException si une erreur survient lors de la mise à jour du client
     */

    // TODO : DAO Update CLIENT
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
}