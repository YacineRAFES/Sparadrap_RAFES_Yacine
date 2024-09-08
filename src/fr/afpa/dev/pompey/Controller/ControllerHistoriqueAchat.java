package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Tables.ListeHistoriqueAchat;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;

import javax.swing.*;

public class ControllerHistoriqueAchat extends javax.swing.JFrame {
    private JTable tableHistoriqueAchat;
    private JPanel contentPane;
    private JTextField barreDeRecherche;

    public ControllerHistoriqueAchat() {
        // TODO A FAIRE
        setTitle("Historique d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        //le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        ListeHistoriqueAchat model1 = new ListeHistoriqueAchat();
        this.tableHistoriqueAchat.setModel(model1);
        this.tableHistoriqueAchat.getTableHeader().setResizingAllowed(false);


    }
}
