package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Tables.ListeMutuelleTable;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.InterfaceModel;
import fr.afpa.dev.pompey.Utilitaires.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ControllerListeMutuelle est le contrôleur de la fenêtre de liste des mutuelles
 */
public class ControllerListeMutuelle extends JFrame {
    private JPanel contentPane;
    private JTextField barDeRechercheTextField;
    private JTable mutuelleTable;
    private JScrollPane scrollPane;
    private JLabel titreLabel;
    private JButton fermerButton;
    private JPanel affichageAlertePanel;
    private JLabel informationLabel;

    /**
     * Constructeur de la classe ControllerListeMutuelle
     */
    public ControllerListeMutuelle() {
        setTitle("Liste des Mutuelles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Affichage des mutuelles
        ListeMutuelleTable model1 = new ListeMutuelleTable(GestionListe.getMutuelle());
        this.mutuelleTable.setModel(model1);
        this.mutuelleTable.getTableHeader().setResizingAllowed(false);

        // Filtrer les mutuelles
        InterfaceModel.filterTable(barDeRechercheTextField, model1, mutuelleTable);

        // Bouton Détail
        mutuelleTable.getColumn("Détails").setCellRenderer(new button.ButtonRenderer());
        mutuelleTable.getColumn("Détails").setCellEditor(new button.ButtonEditor(new JCheckBox(), e -> {
            int row = mutuelleTable.getEditingRow(); // Get the row being edited (clicked)
            if (row >= 0) { // Ensure the row index is valid
                if (row < GestionListe.getMutuelle().size()){
                    Mutuelle mutuelle = GestionListe.getMutuelle().get(row);
                    ControllerDetailMutuelle controllerDetailMutuelle = new ControllerDetailMutuelle(mutuelle);
                    controllerDetailMutuelle.setVisible(true);
                } else {
                    Fenetre.Fenetre("la Mutuelle n'existe pas");
                    new SaisieException("la Mutuelle n'existe pas");
                }
            }
        }));

        // Fermer la fenêtre
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }
}
