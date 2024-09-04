package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.afpa.dev.pompey.Modele.GestionListe.*;
import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.*;

public class ControllerMedicament extends javax.swing.JFrame {
    private JPanel contentPane;
    private JTextField nomTextField;
    private JTextField miseEnServiceTextField;
    private JTextField prixTextField;
    private JTextField quantiteTextField;
    private JComboBox categorieCombobox;
    private JLabel titreLabel;
    private JLabel coordonneLabel;
    private JLabel quantiteLabel;
    private JButton annulerButton;
    private JButton creerButton;


    String[] categorie = {
            "Analgésiques",
            "Anti-inflammatoires",
            "Antibiotiques",
            "Antibactériens",
            "Antituberculeux",
            "Antilépreux",
            "Antimycosiques",
            "Antiviraux",
            "Cardiologie"};

    public ControllerMedicament(){
        setTitle("Medicament");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        DefaultComboBoxModel<Client> categorieModel = (DefaultComboBoxModel<Client>) categorieCombobox.getModel();

        categorieCombobox.setModel(categorieModel);
        categorieCombobox.setEditable(true);

        ((JTextField) categorieCombobox.getEditor().getEditorComponent()).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
//                verifierEtAjouterCategorie();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
//                verifierEtAjouterCategorie();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
//                verifierEtAjouterCategorie();
            }
        });

        AjouterPlaceholder(nomTextField, "Nom du medicament");
        AjouterPlaceholder(miseEnServiceTextField, "Mise en service");
        AjouterPlaceholder(prixTextField, "Prix");
        AjouterPlaceholder(quantiteTextField, "Quantite");
        AjouterPlaceholderComboboxEditable(categorieCombobox, "Categorie");


        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annuler();
            }
        });
        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    valider();
                } catch (SaisieException ex) {
                    new RuntimeException(ex);
                }
            }
        });
        AjouterPlaceholderComboboxEditable(categorieCombobox, "Categorie");
    }

    private void valider() throws SaisieException {
        String nomMedoc = nomTextField.getText();
        String categorieMedoc = categorieCombobox.getSelectedItem().toString();
        String miseEnService = miseEnServiceTextField.getText();
        String prixMedoc = prixTextField.getText();
        String quantiteMedoc = quantiteTextField.getText();

        if(nomMedoc.isEmpty() || miseEnService.isEmpty() || prixMedoc.isEmpty() || quantiteMedoc.isEmpty()){
            Fenetre.Fenetre("Veuillez remplir tous les champs");
            throw new SaisieException();
        }

        Medicament medicament = new Medicament(
                Verification.NomPrenom(nomMedoc, "médicament"),
                categorieMedoc,
                Verification.Prix(prixMedoc),
                Verification.BirthDate(miseEnService),
                Verification.Quantite(quantiteMedoc));

        addMedicament(medicament);
        Fenetre.Fenetre("Client enregistrée avec succès");
        this.dispose();

        System.out.println(getClient());
        System.out.println(getMedecin());
        annuler();
    }

    private void annuler() {
        nomTextField.setText("");
        categorieCombobox.setSelectedItem(0);
        miseEnServiceTextField.setText("");
        prixTextField.setText("");
        quantiteTextField.setText("");
        this.dispose();
    }
}
