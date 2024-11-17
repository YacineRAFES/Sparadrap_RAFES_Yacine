package fr.afpa.dev.pompey.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ControllerAccueil est le contrôleur de la fenêtre d'accueil
 */
public class ControllerAccueil extends JFrame {
    private JPanel contentPane;
    private JButton achatButton;
    private JButton historiqueDAchatButton;
    private JButton detailMedecinButton;
    private JButton detailClientButton;
    private JButton mutuelleButton;
    private JTable listeDeMedocTable;

    /**
     * Constructeur de la classe ControllerAccueil
     */
    public ControllerAccueil(){

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
                achat();
            }
        });
        //Les Listeners
        //Bouton Historique d'achat
        historiqueDAchatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historiqueDAchat();
            }
        });
        //Bouton Detail Client
        detailClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeClient();
            }
        });
        //Bouton Detail Medecin
        detailMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeMdecin();
            }
        });
        //Bouton Detail Mutuelle
        mutuelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeMutuelle();
            }
        });
    }

    /**
     * Méthode pour afficher la fenêtre
     */
    private void achat() {
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

    /**
     * Méthode pour ouvrir la fenêtre de l'historique d'achat
     */
    private void historiqueDAchat() {
        ControllerHistoriqueAchat historiqueAchat = new ControllerHistoriqueAchat();
        historiqueAchat.setVisible(true);
    }

    /**
     * Méthode pour ouvrir la fenêtre de liste de client
     */
    private void listeClient(){
        ControllerListeClient listeClient = new ControllerListeClient();
        listeClient.setVisible(true);
    }

    /**
     * Méthode pour ouvrir la fenêtre de liste de médecin
     */
    private void listeMdecin(){
        ControllerListeMedecin listeMedecin = new ControllerListeMedecin();
        listeMedecin.setVisible(true);
    }

    /**
     * Méthode pour ouvrir la fenêtre de liste de mutuelle
     */
    private void listeMutuelle(){
        ControllerListeMutuelle listeMutuelle = new ControllerListeMutuelle();
        listeMutuelle.setVisible(true);
    }
}
