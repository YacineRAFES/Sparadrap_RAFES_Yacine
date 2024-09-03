package fr.afpa.dev.pompey.Controller;

import javax.swing.*;

public class HistoriqueAchat extends javax.swing.JFrame {
    private JTable table1;
    private JPanel contentPane;
    private JTextField barreDeRecherche;

    public HistoriqueAchat() {
        setTitle("Historique d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        //le positionnement de la fenetre
        this.setLocationRelativeTo(null);
    }
}
