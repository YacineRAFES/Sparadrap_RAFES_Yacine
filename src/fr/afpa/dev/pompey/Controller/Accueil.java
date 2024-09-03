package fr.afpa.dev.pompey.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {
    private JPanel contentPane;
    private JButton achatButton;
    private JButton historiqueDAchatButton;
    private JButton détailMédecinButton;
    private JButton détailClientButton;

    public Accueil(){

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
        ControllerAchat achat = new ControllerAchat();
        achat.setVisible(true);
    }

    private void historiqueDAchat() {
        HistoriqueAchat historiqueAchat = new HistoriqueAchat();
        historiqueAchat.setVisible(true);
    }
}
