// src/fr/afpa/dev/pompey/Controller/ControllerListeMedecin.java
package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedecinTable;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.*;

/**
 * La classe ControllerListeMedecin est le contrôleur de la fenêtre de liste des médecins
 */
public class ControllerListeMedecin extends JFrame {
    private JPanel contentPane;
    private JLabel titreListeMedecin;
    private JTable listeMedecinTable1;
    private JButton creerUnMedecinButton;
    private JScrollPane scrollPane;
    private JButton fermerButton;
    private JLabel informationLabel;
    private JPanel affichageAlertePanel;

    /**
     * Constructeur de la classe ControllerListeMedecin
     */
    public ControllerListeMedecin() {
        setTitle("Liste des Médecins");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Tableau
        ListeMedecinTable model1 = new ListeMedecinTable(GestionListe.getMedecin());
        this.listeMedecinTable1.setModel(model1);
        this.listeMedecinTable1.getTableHeader().setResizingAllowed(false);

        //Bouton Détail
        listeMedecinTable1.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        listeMedecinTable1.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listeMedecinTable1.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid
                    if (row < GestionListe.getMedecin().size()){
                        Medecin medecin = GestionListe.getMedecin().get(row);
                        ControllerDetailMedecin controllerDetailMedecin = new ControllerDetailMedecin(medecin);
                        controllerDetailMedecin.setVisible(true);
                    } else {
                        Fenetre.Fenetre("Medecin n'existe pas");
                        new SaisieException("Medecin n'existe pas");
                    }
                }
            }
        }));

        //Bouton Supprimer
        listeMedecinTable1.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        listeMedecinTable1.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listeMedecinTable1.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid
                    if (row < GestionListe.getMedecin().size()) {
                        Medecin medecin = GestionListe.getMedecin().get(row);
                        // On vérifie si le médecin est lié à un client ou une ordonnance
                        boolean medecinLieClient = GestionListe.getClient().stream().anyMatch(client -> client.getMedecin() != null && client.getMedecin().equals(medecin));
                        boolean medecinLieOrdonnance = GestionListe.getOrdonnance().stream().anyMatch(ordonnance -> ordonnance.getMedecin() != null && ordonnance.getMedecin().equals(medecin));
                        if (medecinLieClient || medecinLieOrdonnance) {
                            ShowLabelWithTimer(
                                    informationLabel,
                                    "<html><p>Le médecin est lié à un client ou une ordonnance,<br> impossible de le supprimer</p></html>",
                                    Color.RED);
                        } else {
                            GestionListe.removeMedecin(medecin);
                            ShowLabelWithTimer(
                                    informationLabel,
                                    "Le médecin a été supprimé avec succès",
                                    Color.GREEN);
                        }
                    } else {
                        ShowLabelWithTimer(
                                informationLabel,
                                "Le médecin n'existe",
                                Color.RED);
                    }
                    Refresh(listeMedecinTable1); // Rafraîchir la table après la suppression
                }
            }
        }));

        //Bouton Creer un Medecin
        creerUnMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerMedecin controllerMedecin = new ControllerMedecin();
                controllerMedecin.setVisible(true);
                controllerMedecin.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Refresh(listeMedecinTable1);
                    }
                });

            }
        });

        //Bouton Fermer
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}