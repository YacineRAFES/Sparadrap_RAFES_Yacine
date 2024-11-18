package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Categorie;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Utilitaires.DateCustom;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.PlaceholderTextField;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ControllerMedicament est le contrôleur de la fenêtre de création d'un médicament
 */
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

    private CategorieDAO categorieDAO;
    private MedicamentDAO medicamentDAO;

    /**
     * Constructeur de la classe ControllerMedicament
     */
    public ControllerMedicament(){
        categorieDAO = new CategorieDAO();
        medicamentDAO = new MedicamentDAO();

        setTitle("Medicament");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(true);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Remplir la combobox de catégorie
        DefaultComboBoxModel<Categorie> categorieModel = new DefaultComboBoxModel<>();
        for (Categorie categorie : categorieDAO.findAll()) {
            categorieModel.addElement(categorie);
        }
        categorieCombobox.setModel(categorieModel);

        //Placeholder
        PlaceholderTextField.setPlaceholder(nomTextField, "Nom");
        PlaceholderTextField.setPlaceholder(miseEnServiceTextField, "JJ/MM/AAAA");
        PlaceholderTextField.setPlaceholder(prixTextField, "Prix");
        PlaceholderTextField.setPlaceholder(quantiteTextField, "Quantité");


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
    }

    /**
     * Méthode pour valider la création d'un médicament
     *
     * @throws SaisieException
     */
    private void valider() throws SaisieException {
        String nomMedoc = nomTextField.getText();
        Object categorieSelected = categorieCombobox.getSelectedItem();
        String miseEnService = miseEnServiceTextField.getText();
        Double prix = Double.valueOf(prixTextField.getText());
        Integer quantite = Integer.valueOf(quantiteTextField.getText());

        if(nomMedoc.isEmpty() || miseEnService.isEmpty()){
            Fenetre.Fenetre("Veuillez remplir tous les champs");
            throw new SaisieException();
        }

        //On vérifie si la catégorie existe déjà sinon on le crée
        int newIdCategorie = 0;
        boolean categorieExist = false;

        for(Categorie categorieCheck : categorieDAO.findAll()){
            if(categorieCheck.getNom().equals(categorieSelected)){
                categorieExist = true;
                break;
            }
        }
        if (categorieExist) {
            Fenetre.Fenetre("La catégorie existe déjà");
            throw new SaisieException();
        } else {
            Categorie categorie = new Categorie(
                    categorieSelected.toString()
            );
            newIdCategorie = categorieDAO.create(categorie);
        }

        //On vérifie si le médicament existe déjà sinon on le crée
        int newIdMedicament = 0;
        boolean medicamentExist = false;
        for(Medicament medicamentCheck : medicamentDAO.findAll()){
            if(medicamentCheck.getNom().equals(nomMedoc)){
                medicamentExist = true;
                break;
            }
        }
        if (medicamentExist) {
            Fenetre.Fenetre("Le médicament existe déjà");
            throw new SaisieException();
        } else {
            Medicament medicament = new Medicament(
                    nomMedoc,
                    DateCustom.convertStringToDate(miseEnService),
                    quantite,
                    prix,
                    newIdCategorie
            );
            medicamentDAO.create(medicament);
        }

        Fenetre.Fenetre("Médicament a été enregistrée avec succès");
        annuler();
    }

    /**
     * Méthode pour annuler la création d'un médicament
     */
    private void annuler() {
        nomTextField.setText("");
        categorieCombobox.setSelectedItem(0);
        miseEnServiceTextField.setText("");
        prixTextField.setText("");
        quantiteTextField.setText("");
        this.dispose();
    }

}
