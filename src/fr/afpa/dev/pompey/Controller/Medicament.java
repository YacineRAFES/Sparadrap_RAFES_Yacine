package fr.afpa.dev.pompey.Controller;

import javax.swing.*;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.AjouterPlaceholder;

public class Medicament extends javax.swing.JFrame {
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

    public Medicament(){
        setTitle("Medicament");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        AjouterPlaceholder(nomTextField, "Nom du medicament");
        AjouterPlaceholder(miseEnServiceTextField, "Mise en service");
        AjouterPlaceholder(prixTextField, "Prix");
        AjouterPlaceholder(quantiteTextField, "Quantite");




    }
}
