package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.Generator;
import fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
        this.setResizable(true);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Remplir la combobox de catégorie
        DefaultComboBoxModel<Medicament> categorieModel = (DefaultComboBoxModel<Medicament>) categorieCombobox.getModel();
        for (String s : categorie) {
            categorieModel.addElement(new Medicament(s));
        }
        categorieCombobox.setModel(categorieModel);

        //Les saisies avec placeholder
        AjouterPlaceholder(nomTextField, "Nom du medicament");
        AjouterPlaceholder(miseEnServiceTextField, "Mise en service");
        AjouterPlaceholder(prixTextField, "Prix");
        AjouterPlaceholder(quantiteTextField, "Quantite");

        miseEnServiceTextField.setText("30/12/2024");

        //Bouton Annuler
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annuler();
            }
        });

        //Bouton Créer
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

    //Méthode pour valider la création d'un médicament
    private void valider() throws SaisieException {
        String nomMedoc = nomTextField.getText();
        String categorieMedoc = Objects.requireNonNull(categorieCombobox.getSelectedItem()).toString();
        String miseEnService = miseEnServiceTextField.getText();
        String prixMedoc = prixTextField.getText();
        String quantiteMedoc = quantiteTextField.getText();

        if(nomMedoc.isEmpty() || miseEnService.isEmpty() || prixMedoc.isEmpty() || quantiteMedoc.isEmpty()){
            Fenetre.Fenetre("Veuillez remplir tous les champs");
            throw new SaisieException();
        }
        //Si un médicament avec le même nom existe déjà
        if (getMedicament().stream().anyMatch(medicament -> medicament.getNom().equals(nomMedoc))) {
            Fenetre.Fenetre("Ce médicament existe déjà");
            throw new SaisieException("Ce médicament existe déjà");
        }

        Medicament medicament = new Medicament(
                Verification.NomPrenom(nomMedoc, "médicament"),
                categorieMedoc,
                Verification.Prix(prixMedoc),
                Verification.BirthDate(miseEnService),
                Verification.Quantite(quantiteMedoc));

        addMedicament(medicament);
        Fenetre.Fenetre("Médicament a été enregistrée avec succès");
        System.out.println(medicament);
        annuler();
    }

    //Méthode pour annuler la création d'un médicament
    private void annuler() {
        nomTextField.setText("");
        categorieCombobox.setSelectedItem(0);
        miseEnServiceTextField.setText("");
        prixTextField.setText("");
        quantiteTextField.setText("");
        this.dispose();
    }
}
