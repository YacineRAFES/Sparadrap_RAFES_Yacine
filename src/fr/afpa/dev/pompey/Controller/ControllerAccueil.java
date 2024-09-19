package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
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
    private JButton mutuelleButton;
    private JTable listeDeMedocTable;

    public ControllerAccueil() throws SaisieException {
        //TODO FAIRE LA MUTUELLE
        
        //Tests
        Medecin medecin = new Medecin("Dr. Dupont", "Jean", "1 rue de la paix", "54000", "Nancy", "0372000000", "dupont@gmail.com", "123456", "Cardiologue");
        Medecin medecin1 = new Medecin("Dr. Durand", "Pierre", "10 rue des champs", "75000", "Paris", "0172000000", "durand@mail.com", "654321", "Dentiste");
        Medecin medecin2 = new Medecin("Dr. Martin", "Paul", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "martin@gmail.com", "789456", "Ophtalmologue");

        Mutuelle mutuelle1 = new Mutuelle("MGEL", "1 rue de la paix", "54000", "Nancy", "0372000000", "email@mail.com", "54", "80");
        Mutuelle mutuelle2 = new Mutuelle("MGEN", "10 rue des champs", "75000", "Paris", "0172000000", "contact@mgen.fr", "75", "75");
        Mutuelle mutuelle3 = new Mutuelle("Harmonie Mutuelle", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "info@harmonie.fr", "69", "70");
        Mutuelle mutuelle4 = new Mutuelle("MMA", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "mma@gmail.com", "69", "70");
        Mutuelle mutuelle5 = new Mutuelle("AXA", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "axa@gmail.com", "69", "70");

        Medicament medicament = new Medicament("Doliprane", "Analgésiques", "2.5", "30/09/2024", 100);
        Medicament medicament1 = new Medicament("Nurofen", "Anti-inflammatoires", "3.5", "30/09/2024", 50);
        Medicament medicament2 = new Medicament("Amoxicilline", "Antibiotiques", "4.5", "30/09/2024", 30);
        Medicament medicament3 = new Medicament("Paracetamol", "Analgésiques", "2.5", "30/09/2024", 100);
        Medicament medicament4 = new Medicament("Ibuprofene", "Anti-inflammatoires", "3.5", "30/09/2024", 50);
        Medicament medicament5 = new Medicament("Azithromycine", "Antibiotiques", "4.5", "30/09/2024", 30);

        Client client = new Client("Dupont", "Jean", "1 rue de la paix", "54000", "Nancy", "0372000000", "dupont@mail.com", "123456789012345", "13/01/1920", mutuelle2, medecin2);
        Client client1 = new Client("Marc", "Pierre", "10 rue des champs", "75000", "Paris", "0172000000", "durand@mail.com", "543216789012345", "13/01/1920", mutuelle1, medecin2);
        Client client2 = new Client("Yohan", "Paul", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "paul@gmail.Com", "987654321012345", "13/01/1920", mutuelle3, medecin2);
        Client client3 = new Client("Semiremorque", "Leroutier", "1 rue de la paix", "54000", "Nancy", "0372000000", "semiremorque@gmail.com", "123456789012345", "13/01/1920", mutuelle2, medecin1);
        Client client4 = new Client("Vié", "Maxime", "10 rue des champs", "75000", "Paris", "0172000000", "maximevie@mail.com", "543216789012345", "13/01/1920", mutuelle1, medecin1);

        GestionListe.addClient(client);
        GestionListe.addClient(client2);
        GestionListe.addClient(client3);
        GestionListe.addClient(client4);
        GestionListe.addClient(client1);


        GestionListe.addMedecin(medecin);
        GestionListe.addMedecin(medecin1);
        GestionListe.addMedecin(medecin2);

        GestionListe.addMedicament(medicament);
        GestionListe.addMedicament(medicament1);
        GestionListe.addMedicament(medicament2);
        GestionListe.addMedicament(medicament3);
        GestionListe.addMedicament(medicament4);
        GestionListe.addMedicament(medicament5);

        GestionListe.addMutuelle(mutuelle1);
        GestionListe.addMutuelle(mutuelle2);
        GestionListe.addMutuelle(mutuelle3);
        GestionListe.addMutuelle(mutuelle4);
        GestionListe.addMutuelle(mutuelle5);


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
        //Les Listeners
        //Bouton Historique d'achat
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
        //Bouton Detail Client
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
        //Bouton Detail Medecin
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
        //Bouton Detail Mutuelle
        mutuelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    listeMutuelle();
                }catch (SaisieException ex){
                    Fenetre.Fenetre("Erreur lors d'ouvrir la fenêtre de liste de mutuelle");
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //Les méthodes
    //Méthode pour ouvrir la fenêtre d'achat
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

    //Méthode pour ouvrir la fenêtre d'historique d'achat
    private void historiqueDAchat() throws SaisieException {
        ControllerHistoriqueAchat historiqueAchat = new ControllerHistoriqueAchat();
        historiqueAchat.setVisible(true);
    }

    //Méthode pour ouvrir la fenêtre de liste de client
    private void listeClient() throws SaisieException{
        ControllerListeClient listeClient = new ControllerListeClient();
        listeClient.setVisible(true);
    }

    //Méthode pour ouvrir la fenêtre de liste de médecin
    private void listeMdecin() throws SaisieException{
        ControllerListeMedecin listeMedecin = new ControllerListeMedecin();
        listeMedecin.setVisible(true);
    }

    //Méthode pour ouvrir la fenêtre de liste de mutuelle
    private void listeMutuelle() throws SaisieException{
        ControllerListeMutuelle listeMutuelle = new ControllerListeMutuelle();
        listeMutuelle.setVisible(true);
    }
}
