package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.PlaceholderTextField;
import fr.afpa.dev.pompey.Utilitaires.Verification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ControllerMedecin est le contrôleur de la fenêtre de création d'un médecin
 */
public class ControllerMedecin extends JFrame {
    private JPanel contentPane;
    private JTextField nomTextField;
    private JLabel coordonneeLabel;
    private JLabel adresseLabel;
    private JLabel ContactLabel;
    private JTextField numAgreementTextField;
    private JTextField cpTextField;
    private JTextField prenomTextField;
    private JTextField rueTextField;
    private JTextField villeTextField;
    private JTextField telephoneTextField;
    private JTextField emailTextField;
    private JButton annulerButton;
    private JButton creerButton;
    private JTextField specialisteTextField;
    private JLabel specialisteLabel;
    private JComboBox regionComboBox;

    private CoordonneesDAO coordonneesDAO;
    private AdressesDAO adressesDAO;
    private MedecinDAO medecinDAO;
    private RegionDAO regionDAO;
    private VilleDAO villeDAO;

    /**
     * Constructeur de la classe ControllerMedecin
     */
    public ControllerMedecin(){
        //Initialisation des DAO
        coordonneesDAO = new CoordonneesDAO();
        adressesDAO = new AdressesDAO();
        medecinDAO = new MedecinDAO();
        regionDAO = new RegionDAO();
        villeDAO = new VilleDAO();

        setTitle("Medecin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Placeholder
        PlaceholderTextField.setPlaceholder(nomTextField, "Nom");
        PlaceholderTextField.setPlaceholder(prenomTextField, "Prénom");
        PlaceholderTextField.setPlaceholder(numAgreementTextField, "Numéro d'agreement");
        PlaceholderTextField.setPlaceholder(rueTextField, "Rue");
        PlaceholderTextField.setPlaceholder(cpTextField, "Code postal");
        PlaceholderTextField.setPlaceholder(villeTextField, "Ville");
        PlaceholderTextField.setPlaceholder(telephoneTextField, "Téléphone");
        PlaceholderTextField.setPlaceholder(emailTextField, "Email");
        PlaceholderTextField.setPlaceholder(specialisteTextField, "Spécialiste");

        //Bouton Créer
        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    creerMedecin();
                } catch (SaisieException ex) {
                    new RuntimeException(ex);
                }
            }
        });

        //Bouton Annuler
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerMedecin();
            }
        });
    }

    /**
     * Méthode pour annuler la création d'un médecin
     */
    private void annulerMedecin() {
        this.dispose();
    }

    /**
     * Méthode pour créer un médecin
     * @throws SaisieException
     */
    private void creerMedecin() throws SaisieException {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String numAgreement = numAgreementTextField.getText();
        String rueName = rueTextField.getText();
        String cp = cpTextField.getText().trim();
        String villeName = villeTextField.getText();
        Object regionSelected = regionComboBox.getSelectedItem();
        String telephone = telephoneTextField.getText();
        String email = emailTextField.getText();
        String specialiste = specialisteTextField.getText();

        int newIdVille = 0;

        //Récupération de la région
        int newIdCoordonnees = 0;
        boolean coordonnesExist = false;

        for (Coordonnees coordonneesCheck : coordonneesDAO.findAll()) {
            if (coordonneesCheck.getEmail().equals(email) || coordonneesCheck.getTelephone().equals(telephone)) {
                coordonnesExist = true;
                break;
            }
        }
        if (coordonnesExist) {
            Fenetre.Fenetre("Les coordonnées existent déjà");
            throw new SaisieException();
        } else {
            Coordonnees coordonnees = new Coordonnees(
                    email,
                    telephone
            );
            // On récupère l'id des coordonnées
            newIdCoordonnees = coordonneesDAO.create(coordonnees);
        }


        // 2. Créer des adresses
        // on crée le nom de la région
        Region nameRegion = null;
        if (regionSelected instanceof RegionDAO) {
            nameRegion = (Region) regionSelected;
        } else if (regionSelected instanceof String) {
            nameRegion = new Region((String) regionSelected);
        }
        int newIdRegion = 0;
        for (Region regionCheck : regionDAO.findAll()) {
            if (regionCheck.getNom().equals(nameRegion.getNom())) {
                Fenetre.Fenetre("La région existe déjà");
                throw new SaisieException();
            } else {
                Region region = new Region(
                        String.valueOf(nameRegion)
                );
                newIdRegion = regionDAO.create(region);
            }
        }
        //on crée le nom de la ville


        boolean villeExist = false;
        for (Ville villeCheck : villeDAO.findAll()) {
            if (villeCheck.getNom().equals(villeName)) {
                villeExist = true;
                break;
            }
        }
        if(villeExist){
            Fenetre.Fenetre("La ville existe déjà");
            throw new SaisieException();
        } else {
            Ville ville = new Ville(
                    villeName,
                    cp,
                    newIdRegion
            );
            newIdVille = villeDAO.create(ville);
        }

        //On créee l'adresse
        int newIdAdresse = 0;
        Adresses adresses = new Adresses(
                rueName,
                newIdVille
        );
        // On récupère l'id de l'adresse
        newIdAdresse = adressesDAO.create(adresses);

        //Vérification des champs
        Medecin medecin = new Medecin(
                Verification.NomPrenom(nom, "Nom"),
                Verification.NomPrenom(prenom, "Prénom"),
                numAgreement,
                specialiste,
                newIdAdresse,
                newIdCoordonnees
        );

        medecinDAO.create(medecin);

        Fenetre.Fenetre("Medecin créé");
        this.dispose();

    }
}
