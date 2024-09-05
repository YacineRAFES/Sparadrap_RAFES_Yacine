package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;

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
    }

    private void achat() {
        ControllerAchat controllerAchat = new ControllerAchat();
        controllerAchat.setVisible(true);
        controllerAchat.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                TODO : Vider la table de la liste de médicament quand on ferme la fenetre d'achat
//                ListeMedicamentTableModel model = (ListeMedicamentTableModel) listeDeMedocTable.getModel();
//                model.clear();
            }
        });
    }

    private void historiqueDAchat() {
        ControllerHistoriqueAchat historiqueAchat = new ControllerHistoriqueAchat();
        historiqueAchat.setVisible(true);
    }
}
