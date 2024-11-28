package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.InterfaceModel;
import fr.afpa.dev.pompey.Utilitaires.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;

import static fr.afpa.dev.pompey.Modele.DAO.DAOUtils.getRegions;
import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.ShowLabelWithTimer;
import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.configurerFenetre;

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
    private JComboBox regionCombobox;

    private MedecinDAO medecinDAO;
    private CoordonneesDAO coordonneesDAO;
    private AdressesDAO adressesDAO;
    private VilleDAO villeDAO;
    private RegionDAO regionDAO;

    private int idmedecin;

    public ControllerDetailMedecin(int idmedecin) {
        this.idmedecin = idmedecin;
        medecinDAO = new MedecinDAO();
        coordonneesDAO = new CoordonneesDAO();
        adressesDAO = new AdressesDAO();
        villeDAO = new VilleDAO();
        regionDAO = new RegionDAO();

        configurerFenetre(this, contentPane, false, "Détail Médecin");

        annulerButton.addActionListener(e -> this.dispose());

        modifierButton.addActionListener(e -> {
            try {
                modifier(idmedecin);
            } catch (SaisieException ex) {
                throw new RuntimeException(ex);
            }
        });

        listesDesOrdonnancesButton.addActionListener(e -> new ControllerListeOrdonnanceIdMed(idmedecin).setVisible(true));
        listesDesClientsButton.addActionListener(e -> new ControllerListeClientIdMed(idmedecin).setVisible(true));

        chargerLesRegions();

        InsertPlaceholders();

        chargerLesDonnesMedecin();
    }

    private void InsertPlaceholders(){
        PlaceholderTextField.setPlaceholder(nomTextField,"Nom");
        PlaceholderTextField.setPlaceholder(prenomTextField,"Prénom");
        PlaceholderTextField.setPlaceholder(cpTextField,"Code Postal");
        PlaceholderTextField.setPlaceholder(rueTextField,"Rue");
        PlaceholderTextField.setPlaceholder(villeTextField,"Ville");
        PlaceholderTextField.setPlaceholder(telephoneTextField,"Téléphone");
        PlaceholderTextField.setPlaceholder(emailTextField,"Email");
        PlaceholderTextField.setPlaceholder(numAgreementTextField,"Numéro d'agreement");
        PlaceholderTextField.setPlaceholder(specialisteTextField,"Spécialiste");
    }

    private void chargerLesDonnesMedecin(){
        Medecin medecin = medecinDAO.find(idmedecin);
        if (medecin != null) {
            nomTextField.setText(medecin.getNom());
            prenomTextField.setText(medecin.getPrenom());

            if (medecin.getAdresses() != null) {
                rueTextField.setText(medecin.getAdresses().getRue());
                if (medecin.getAdresses().getVille() != null) {
                    villeTextField.setText(medecin.getAdresses().getVille().getNom());
                    cpTextField.setText(medecin.getAdresses().getVille().getCp());
                    if (medecin.getAdresses().getVille().getRegion() != null) {
                        regionCombobox.setSelectedItem(medecin.getAdresses().getVille().getRegion().getNom());
                    }
                }
            }

            if (medecin.getCoordonnees() != null) {
                telephoneTextField.setText(medecin.getCoordonnees().getTelephone());
                emailTextField.setText(medecin.getCoordonnees().getEmail());
            }

            numAgreementTextField.setText(medecin.getNumAgreement());
            specialisteTextField.setText(medecin.getSpecialite());
        } else {
            InterfaceModel.ShowLabelWithTimer(informationLabel, "Médecin introuvable", Color.RED);
        }
    }

    private void modifier(int idmedecin) throws SaisieException {
        Medecin medecin = medecinDAO.find(idmedecin);
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String cp = cpTextField.getText();
        String rue = rueTextField.getText();
        String villeName = villeTextField.getText();
        Region regionSelected = (Region) regionCombobox.getSelectedItem();
        String telephone = telephoneTextField.getText();
        String email = emailTextField.getText();
        String numAgreement = numAgreementTextField.getText();
        String specialiste = specialisteTextField.getText();

        int idCoordonnees = 0;
        if (medecin.getCoordonnees() != null) {
            if (!telephone.equals(medecin.getCoordonnees().getTelephone()) ||
                    !email.equals(medecin.getCoordonnees().getEmail())) {
                Coordonnees coordonnees = new Coordonnees(
                        medecin.getCoordonnees().getId(),
                        email,
                        telephone
                );
                coordonneesDAO.update(coordonnees);
                idCoordonnees = medecin.getCoordonnees().getId();
            }
        } else {
            Coordonnees nouvellesCoordonnees = new Coordonnees(email, telephone);
            idCoordonnees = coordonneesDAO.create(nouvellesCoordonnees);
        }

        Region nameRegion = null;
        if (regionSelected instanceof Region) {
            nameRegion = regionDAO.find(regionSelected.getId());
        }

        int newIdRegion = nameRegion.getId();

        if (villeName == null || villeName.isEmpty() || cp == null || cp.isEmpty()) {
            InterfaceModel.ShowLabelWithTimer(informationLabel, "Veuillez remplir tous les champs", Color.RED);
            throw new SaisieException();
        }
        int newIdVille = 0;
        boolean villeExist = false;
        for (Ville villeCheck : villeDAO.findAll()) {
            if (villeCheck.getNom().equals(villeName)) {
                villeExist = true;
                newIdVille = villeCheck.getId();
                break;
            }
        }
        if (!villeExist) {
            Ville ville = new Ville(
                    villeName,
                    cp,
                    newIdRegion
            );
            newIdVille = villeDAO.create(ville);
        }

        int idAdresses = 0;
        Adresses adresses1 = adressesDAO.find(medecin.getAdresses().getId());
        if (adresses1.getId() > 0) {
            Adresses adresses = new Adresses(
                    medecin.getAdresses().getId(),
                    rue,
                    newIdVille
            );
            adressesDAO.update(adresses);
            idAdresses = adresses.getId();
        } else {
            Adresses nouvellesAdresses = new Adresses(rue, newIdVille);
            idAdresses = adressesDAO.create(nouvellesAdresses);
        }


        Medecin medecinModifie = new Medecin(
                idmedecin,
                nom,
                prenom,
                numAgreement,
                specialiste,
                idAdresses,
                idCoordonnees
        );
        medecinDAO.update(medecinModifie);
    }

    private String setTextFieldData(JTextField textField, String data) {
        if (data != null && !data.isEmpty()) {
            textField.setText(data);
            textField.setForeground(Color.BLACK);
        }
        return data;
    }

    private void chargerLesRegions() {
        regionCombobox.setModel(new DefaultComboBoxModel<>(getRegions().toArray(new Region[0])));
        regionCombobox.setEditable(false);
    }
}