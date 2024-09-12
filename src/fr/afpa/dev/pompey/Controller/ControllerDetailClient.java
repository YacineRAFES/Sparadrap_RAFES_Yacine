package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import javax.swing.*;
import java.awt.*;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.AjouterPlaceholder;

public class ControllerDetailClient extends JFrame{
    private JLabel coordonneeLabel;
    private JTextField nomTextField;
    private JTextField secusocialTextField;
    private JTextField mutuelleTextField;
    private JLabel adresseLabel;
    private JTextField cpTextField;
    private JTextField prenomTextField;
    private JTextField dateNaissanceTextField;
    private JTextField rueTextField;
    private JTextField villeTextField;
    private JLabel contactLabel;
    private JTextField telephoneTextField;
    private JTextField emailTextField;
    private JTextField medTraitantTextField;
    private JButton annulerButton;
    private JButton modifierButton;
    private JPanel contentPane;

    public ControllerDetailClient(Client idclient) {
        setTitle("Détail Client");
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

        setTextFieldData(nomTextField, idclient.getNom());
        setTextFieldData(prenomTextField, idclient.getPrenom());
        setTextFieldData(dateNaissanceTextField, idclient.getDateNaissance());
        setTextFieldData(secusocialTextField, idclient.getNumeroSecuClient());
        setTextFieldData(mutuelleTextField, idclient.getMutuelle() != null ? idclient.getMutuelle().getNom() : "");
        setTextFieldData(medTraitantTextField, idclient.getMedecin() != null ? idclient.getMedecin().getNom() + " " + idclient.getMedecin().getPrenom() : "");
        setTextFieldData(cpTextField, idclient.getCodePostal());
        setTextFieldData(rueTextField, idclient.getAdresse());
        setTextFieldData(villeTextField, idclient.getVille());
        setTextFieldData(telephoneTextField, idclient.getTelephone());
        setTextFieldData(emailTextField, idclient.getEmail());

        annulerButton.addActionListener(e -> this.dispose());

        modifierButton.addActionListener(e -> {
            try {
                modifier(idclient);
            } catch (SaisieException ex) {
                Fenetre.Fenetre("Erreur lors de la saisie");
            }
        });
    }

    // La modification du client a été effectué avec succès sinon une exception est levée
    private void modifier(Client idclient) throws SaisieException {
        Client client = buildClient();
        if (client == null) return; // Saisie incorrecte
        try{
            updateClientInfo(idclient, client);
            Fenetre.Fenetre("Client modifié avec succès");
            this.dispose();
        }catch (SaisieException e){
            Fenetre.Fenetre("Erreur lors de la modification du client");
        }
    }

    // Crée un client à partir des champs de texte
    private Client buildClient() throws SaisieException {
        String nom = Verification.NomPrenom(nomTextField.getText(), "Nom");
        String prenom = Verification.NomPrenom(prenomTextField.getText(), "Prénom");
        String dateNaissance = Verification.BirthDate(dateNaissanceTextField.getText());
        String secuSocial = Verification.SecuSocial(secusocialTextField.getText());
        String mutuelleText = mutuelleTextField.getText();
        String medTraitant = medTraitantTextField.getText();
        String cp = Verification.CodePostal(cpTextField.getText());
        String rue = rueTextField.getText();
        String ville = Verification.NomPrenom(villeTextField.getText(), "Ville");
        String telephone = Verification.Telephone(telephoneTextField.getText());
        String email = Verification.Email(emailTextField.getText());

        Mutuelle mutuelle = new Mutuelle(mutuelleText);

        GestionListe.addMutuelle(mutuelle);

        String[] medTraitantSplit = medTraitant.split("\\s+", 2);
        if (medTraitantSplit.length != 2) {
            Fenetre.Fenetre("Le nom et prénom du médecin traitant doivent être séparés par un espace");
            return null;
        }

        Medecin medecin = new Medecin(
                Verification.NomPrenom(medTraitantSplit[0].trim(), "nom du médecin"),
                Verification.NomPrenom(medTraitantSplit[1].trim(), "prénom du médecin")
        );

        GestionListe.addMedecin(medecin);

        return new Client(nom, prenom, rue, cp, ville, telephone, email, secuSocial, dateNaissance, mutuelle, medecin);

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

    private void setTextFieldData(JTextField textField, String data) {
        if (data != null && !data.isEmpty()) {
            textField.setText(data);
            textField.setForeground(Color.BLACK); // Texte en noir si les données existent
        }
    }
}