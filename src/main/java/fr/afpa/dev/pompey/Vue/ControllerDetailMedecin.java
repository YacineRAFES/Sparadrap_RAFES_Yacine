package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Utilitaires.InterfaceModel;

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
    private JTextField regionTextField;

    private MedecinDAO medecinDAO;
    private CoordonneesDAO coordonneesDAO;
    private AdressesDAO adressesDAO;
    private VilleDAO villeDAO;
    private RegionDAO regionDAO;

    /**
     * Constructeur de la classe ControllerDetailMedecin
     *
     * @param idmedecin L'identifiant du médecin
     */
    public ControllerDetailMedecin(int idmedecin) {
        //Initialisation des DAO
        medecinDAO = new MedecinDAO();
        coordonneesDAO = new CoordonneesDAO();
        adressesDAO = new AdressesDAO();
        villeDAO = new VilleDAO();
        regionDAO = new RegionDAO();

        setTitle("Détail Médecin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        annulerButton.addActionListener(e -> this.dispose());

        modifierButton.addActionListener(e -> {
            try {
                modifier(idmedecin);
            } catch (SaisieException ex) {
                throw new RuntimeException(ex);
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

    private void modifier(int idmedecin) throws SaisieException {
        Medecin medecin = medecinDAO.find(idmedecin);
        String nom = setTextFieldData(nomTextField, medecin.getNom());
        String prenom = setTextFieldData(prenomTextField, medecin.getPrenom());
        String codepostal = setTextFieldData(cpTextField, medecin.getAdresses().getVille().getCp());
        String rue = setTextFieldData(rueTextField, medecin.getAdresses().getRue());
        String ville = setTextFieldData(villeTextField, medecin.getAdresses().getVille().getNom());
        String region = setTextFieldData(regionTextField, medecin.getAdresses().getVille().getRegion().getNom());
        String telephone = setTextFieldData(telephoneTextField, medecin.getCoordonnees().getTelephone());
        String email = setTextFieldData(emailTextField, medecin.getCoordonnees().getEmail());
        String numAgreement = setTextFieldData(numAgreementTextField, medecin.getNumAgreement());
        String specialiste = setTextFieldData(specialisteTextField, medecin.getSpecialite());
        //Vérifier si les données coordonnées sont vides
        if (telephone != null && !telephone.isEmpty() && email != null && !email.isEmpty()) {
            //On vérifie si les données ont été changé
            if (!medecin.getCoordonnees().getTelephone().equals(telephone) || !medecin.getCoordonnees().getEmail().equals(email)) {
                //Mettre à jour les coordonnées du médecin
                Coordonnees coordonnees = new Coordonnees(
                        medecin.getCoordonnees().getId(),
                        telephoneTextField.getText(),
                        emailTextField.getText()
                );
                coordonneesDAO.update(coordonnees);
            }
        } else {
            InterfaceModel.ShowLabelWithTimer(informationLabel, "Veuillez remplir tous les champs", Color.RED);
        }

        int newIdRegion = 0;
        if (region != null && !region.isEmpty()) {
            if (!medecin.getAdresses().getVille().getRegion().equals(region)) {
                //Créer une nouvelle région
                Region region1 = new Region(
                        region
                );
                newIdRegion = regionDAO.create(region1);
            }
        }

        int newIdVille = 0;
        if (ville != null && !ville.isEmpty()) {
            //On vérifie si les données ont été changé
            if (!medecin.getAdresses().getVille().equals(ville)) {
                //Créer une nouvelle ville
                Ville ville1 = new Ville(
                        ville,
                        codepostal,
                        newIdRegion
                );
                newIdVille = villeDAO.create(ville1);
            }
        }

        if (rue != null && !rue.isEmpty()) {
            if (!medecin.getAdresses().getRue().equals(rue)) {
                //Mettre à jour l'adresse du médecin
                Adresses adresses = new Adresses(
                        medecin.getAdresses().getId(),
                        rue,
                        newIdVille
                );
                adressesDAO.update(adresses);

            }
        }

        if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty() && specialiste != null && !specialiste.isEmpty() && numAgreement != null && !numAgreement.isEmpty()) {
            //Mettre à jour les informations du médecin
            Medecin medecin1 = new Medecin(
                    medecin.getId(),
                    nomTextField.getText(),
                    prenomTextField.getText(),
                    specialisteTextField.getText(),
                    numAgreementTextField.getText()
            );
            medecinDAO.update(medecin1);
            informationLabel.setText("Les informations ont été modifiées avec succès");
        }
    }


    /**
     * Mettre les données dans les champs de texte
     *
     * @param textField Le champ de texte
     * @param data      Les données
     * @return
     */
    private String setTextFieldData(JTextField textField, String data) {
        if (data != null && !data.isEmpty()) {
            textField.setText(data);
            textField.setForeground(Color.BLACK); // Texte en noir si les données existent
        }
        return data;
    }

}
