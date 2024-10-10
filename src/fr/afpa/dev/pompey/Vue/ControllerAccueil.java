package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;

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
