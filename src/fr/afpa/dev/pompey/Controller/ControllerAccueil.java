package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Medicament;
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

        Medicament medicament91 = new Medicament("amikacine","antibiotique", "33", "10/09/2024", 10);
        Medicament medicament92 = new Medicament("dibékacine","antibiotique", "61", "10/09/2024", 10);
        Medicament medicament93 = new Medicament("gentamicine","antibiotique", "6", "10/09/2024", 10);
        Medicament medicament94 = new Medicament("kanamycine","antibiotique", "97", "10/09/2024", 10);
        Medicament medicament95 = new Medicament("néomycine","antibiotique", "54", "10/09/2024", 10);
        GestionListe.addMedicament(medicament91);
        GestionListe.addMedicament(medicament92);
        GestionListe.addMedicament(medicament93);
        GestionListe.addMedicament(medicament94);
        GestionListe.addMedicament(medicament95);

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
}
