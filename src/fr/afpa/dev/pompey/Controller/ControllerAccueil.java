package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAccueil extends JFrame {
    private JPanel contentPane;
    private JButton achatButton;
    private JButton historiqueDAchatButton;
    private JButton detailMedecinButton;
    private JButton detailClientButton;
    private JTable listeDeMedocTable;

    public ControllerAccueil() throws SaisieException {
        //TODO FAIRE LA MUTUELLE
        
        //Tests
        GestionListe gestionListe = new GestionListe();

        Medecin medecin = new Medecin("Dr. Dupont", "Jean", "1 rue de la paix", "54000", "Nancy", "0372000000", "dupont@gmail.com", "123456", "Cardiologue");
        Medecin medecin1 = new Medecin("Dr. Durand", "Pierre", "10 rue des champs", "75000", "Paris", "0172000000", "durand@mail.com", "654321", "Dentiste");
        Medecin medecin2 = new Medecin("Dr. Martin", "Paul", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "martin@gmail.com", "789456", "Ophtalmologue");

        Mutuelle mutuelle1 = new Mutuelle("MGEL", "1 rue de la paix", "54000", "Nancy", "0372000000", "email@mail.com", "54000", "80");
        Mutuelle mutuelle2 = new Mutuelle("MGEN", "10 rue des champs", "75000", "Paris", "0172000000", "contact@mgen.fr", "75000", "75");
        Mutuelle mutuelle3 = new Mutuelle("Harmonie Mutuelle", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "info@harmonie.fr", "69000", "70");

        Medicament medicament = new Medicament("Doliprane", "Analgésiques", "2.5", "01/01/2021", 100);
        Medicament medicament1 = new Medicament("Nurofen", "Anti-inflammatoires", "3.5", "01/01/2021", 50);
        Medicament medicament2 = new Medicament("Amoxicilline", "Antibiotiques", "4.5", "01/01/2021", 30);
        Medicament medicament3 = new Medicament("Paracetamol", "Analgésiques", "2.5", "01/01/2021", 100);
        Medicament medicament4 = new Medicament("Ibuprofene", "Anti-inflammatoires", "3.5", "01/01/2021", 50);
        Medicament medicament5 = new Medicament("Azithromycine", "Antibiotiques", "4.5", "01/01/2021", 30);

        gestionListe.addMedecin(medecin);
        gestionListe.addMedecin(medecin1);
        gestionListe.addMedecin(medecin2);

        gestionListe.addMedicament(medicament);
        gestionListe.addMedicament(medicament1);
        gestionListe.addMedicament(medicament2);
        gestionListe.addMedicament(medicament3);
        gestionListe.addMedicament(medicament4);
        gestionListe.addMedicament(medicament5);

        gestionListe.addMutuelle(mutuelle1);
        gestionListe.addMutuelle(mutuelle2);
        gestionListe.addMutuelle(mutuelle3);

        setTitle("Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setPreferredSize(new Dimension(400, 150));
        this.setResizable(false);
        pack();

        //le positionnement de la fenetre
        setLocationRelativeTo(null);

        achatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    achat();
                } catch (SaisieException ex) {
                    Fenetre.Fenetre("Erreur lors d'ouvrir la fenêtre d'achat");
                    throw new RuntimeException(ex);
                }
            }
        });
        historiqueDAchatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    historiqueDAchat();
                }catch (SaisieException ex){
                    Fenetre.Fenetre("Erreur lors d'ouvrir la fenêtre d'Historique d'Achat");
                    throw new RuntimeException(ex);
                }
            }
        });
        detailClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listeClient();
                } catch (SaisieException ex) {
                    Fenetre.Fenetre("Erreur lors d'ouvrir la fenêtre de liste de client");
                    throw new RuntimeException(ex);
                }
            }
        });
        detailMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listeMdecin();
                } catch (SaisieException ex) {
                    Fenetre.Fenetre("Erreur lors d'ouvrir la fenêtre de liste de médecin");
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    private void achat() throws SaisieException {
        ControllerAchat controllerAchat = new ControllerAchat();
        controllerAchat.setVisible(true);
        controllerAchat.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                ListeMedicamentTableModel modelListeDeMedoc = controllerAchat.getTableModel();
                // Vider le modèle
                if (modelListeDeMedoc != null) {
                    modelListeDeMedoc.clear();
                }

            }
        });
    }

    private void historiqueDAchat() throws SaisieException {
        ControllerHistoriqueAchat historiqueAchat = new ControllerHistoriqueAchat();
        historiqueAchat.setVisible(true);
    }

    private void listeClient() throws SaisieException{
        ControllerListeClient listeClient = new ControllerListeClient();
        listeClient.setVisible(true);
    }

    private void listeMdecin() throws SaisieException{
        ControllerListeMedecin listeMedecin = new ControllerListeMedecin();
        listeMedecin.setVisible(true);
    }
}
