package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;
import fr.afpa.dev.pompey.Modele.Utilitaires.CreateTestConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAccueil extends JFrame {
    private JPanel contentPane;
    private JButton achatButton;
    private JButton historiqueDAchatButton;
    private JButton détailMédecinButton;
    private JButton détailClientButton;
    private JTable listeDeMedocTable;

    public ControllerAccueil(){

        setTitle("Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setPreferredSize(new Dimension(400, 150));
        this.setResizable(false);
        pack();

        //le positionnement de la fenetre
        setLocationRelativeTo(null);

        new CreateTestConstructor();
        achatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                achat();
            }
        });
        historiqueDAchatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historiqueDAchat();
            }
        });
        détailClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeClient();
            }
        });
    }

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

    private void historiqueDAchat() {
        ControllerHistoriqueAchat historiqueAchat = new ControllerHistoriqueAchat();
        historiqueAchat.setVisible(true);
    }

    private void listeClient() {
        ControllerListeClient listeClient = new ControllerListeClient();
        listeClient.setVisible(true);
    }
}
