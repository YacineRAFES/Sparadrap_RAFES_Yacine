package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.AchatSansOrdonnance;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.TableMedicamentTemporaire;
import fr.afpa.dev.pompey.Modele.Tables.ListeHistoriqueAchat;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.button;
import fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.*;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.Refresh;
import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.filterTable;

public class ControllerHistoriqueAchat extends JFrame {
    private JTable tableHistoriqueAchat;
    private JPanel contentPane;
    private JTextField barreDeRecherche;
    private JScrollPane scrollPane;
    private JLabel rechercheLabel;
    private JLabel titreLabel;

    public ControllerHistoriqueAchat() {
        // TODO A FAIRE
        setTitle("Historique d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        //le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        ListeHistoriqueAchat model1 = new ListeHistoriqueAchat(GestionListe.getAchatSansOrdonnance(), GestionListe.getOrdonnance());
        this.tableHistoriqueAchat.setModel(model1);
        this.tableHistoriqueAchat.getTableHeader().setResizingAllowed(false);

        tableHistoriqueAchat.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        tableHistoriqueAchat.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableHistoriqueAchat.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid

                    JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(tableHistoriqueAchat);
                    mainFrame.setEnabled(false);

                    ControllerDetailAchat controllerDetailAchat = null;
                    try {
                        controllerDetailAchat = new ControllerDetailAchat(row);
                    } catch (SaisieException ex) {
                        new RuntimeException(ex);
                    }
                    controllerDetailAchat.setVisible(true);

                    controllerDetailAchat.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            mainFrame.setEnabled(true);
                        }
                    });
                    Refresh(tableHistoriqueAchat);

                }
            }
        }));

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
                    Fenetre.Fenetre("Achat supprimé dans l'historique");
                }
            }
        }));

        filterTable(barreDeRecherche, model1, tableHistoriqueAchat);
    }
}
