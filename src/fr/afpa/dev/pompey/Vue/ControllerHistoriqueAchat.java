package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.AchatSansOrdonnance;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Tables.ListeHistoriqueAchat;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.button;
import fr.afpa.dev.pompey.Utilitaires.InterfaceModel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.*;

public class ControllerHistoriqueAchat extends JFrame {
    private JTable tableHistoriqueAchat;
    private JPanel contentPane;
    private JTextField barreDeRecherche;
    private JScrollPane scrollPane;
    private JLabel rechercheLabel;
    private JLabel titreLabel;
    private JButton fermerButton;
    private JPanel affichageAlertePanel;
    private JLabel informationLabel;

    public ControllerHistoriqueAchat() {
        setTitle("Historique d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        //le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Remplir le tableau
        ListeHistoriqueAchat model1 = new ListeHistoriqueAchat(GestionListe.getAchatSansOrdonnance(), GestionListe.getOrdonnance());
        this.tableHistoriqueAchat.setModel(model1);
        this.tableHistoriqueAchat.getTableHeader().setResizingAllowed(false);

        // Ajouter les boutons dans le tableau
        // Détail
        tableHistoriqueAchat.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        tableHistoriqueAchat.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableHistoriqueAchat.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid
                    ControllerDetailAchat controllerDetailAchat = null;
                    try {
                        controllerDetailAchat = new ControllerDetailAchat(row);
                    } catch (SaisieException ex) {
                        new RuntimeException(ex);
                    }
                    controllerDetailAchat.setVisible(true);

                    Refresh(tableHistoriqueAchat);

                }
            }
        }));

        // Supprimer
        tableHistoriqueAchat.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        tableHistoriqueAchat.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableHistoriqueAchat.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid
                    if (row < GestionListe.getOrdonnance().size()) {
                        GestionListe.removeOrdonnance(row);
                    } else {
                        AchatSansOrdonnance achatSansOrdonnance = GestionListe.getAchatSansOrdonnance().get(row - GestionListe.getOrdonnance().size());
                        GestionListe.removeAchatSansOrdonnance(achatSansOrdonnance);
                    }
                    Refresh(tableHistoriqueAchat);
                    ShowLabelWithTimer(informationLabel, "Achat supprimé dans l'historique", Color.RED);
                }
            }
        }));

        //Méthode qui permet de filtrer le tableau
        filterTable(barreDeRecherche, model1, tableHistoriqueAchat);

        // Les Listeners
        // Fermer la fenêtre
        fermerButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}