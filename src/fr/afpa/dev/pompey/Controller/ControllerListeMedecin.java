package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedecinTable;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.button;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.Refresh;

public class ControllerListeMedecin extends JFrame {
    private JPanel contentPane;
    private JLabel titreListeMedecin;
    private JTable listeMedecinTable1;
    private JButton creerUnMedecinButton;
    private JScrollPane scrollPane;
    private JButton fermerButton;

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
                    if (row < GestionListe.getMedecin().size()){
                        Medecin medecin = GestionListe.getMedecin().get(row);
                        GestionListe.removeMedecin(medecin);
                    } else {
                        Fenetre.Fenetre("Medecin n'existe pas");
                        new SaisieException("Medecin n'existe pas");
                    }
                    Refresh(listeMedecinTable1);
                    Fenetre.Fenetre("Client supprimé");
                }
            }
        }));
    }
}
