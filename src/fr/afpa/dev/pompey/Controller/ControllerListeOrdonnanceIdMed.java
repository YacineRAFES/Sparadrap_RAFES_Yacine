package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Ordonnance;
import fr.afpa.dev.pompey.Modele.Tables.ListeOrdonnancesMed;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerListeOrdonnanceIdMed extends JFrame {
    private JPanel contentPane;
    private JLabel titreLabel;
    private JScrollPane scrollPane;
    private JTable tableHistoriqueOrdonnanceidmed;
    private JTextField barreDeRecherche;
    private JLabel rechercheLabel;
    private JButton fermerButton;

    public ControllerListeOrdonnanceIdMed(Medecin medecin) {
        setTitle("Historiques des ordonnances de " + medecin.getNom() + " " + medecin.getPrenom());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        titreLabel.setText("Historiques des ordonnances de " + medecin.getNom() + " " + medecin.getPrenom());

        // Affichage des ordonnances du médecin
        tableHistoriqueOrdonnanceidmed.setModel(new ListeOrdonnancesMed(medecin));

        //Bouton Détail
        tableHistoriqueOrdonnanceidmed.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        tableHistoriqueOrdonnanceidmed.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableHistoriqueOrdonnanceidmed.getEditingRow(); // Get the row being edited (clicked)
                ListeOrdonnancesMed model = (ListeOrdonnancesMed) tableHistoriqueOrdonnanceidmed.getModel();
                Ordonnance ordonnance = model.getOrdonnanceAt(row); // Utiliser la méthode du modèle pour obtenir l'ordonnance

                if (ordonnance != null) {
                    ControllerDetailAchat controllerDetailAchat = null;
                    try {
                        // Passez l'ordonnance au contrôleur de détail, pas juste l'index
                        controllerDetailAchat = new ControllerDetailAchat(ordonnance);
                    } catch (SaisieException ex) {
                        throw new RuntimeException(ex);
                    }
                    controllerDetailAchat.setVisible(true);
                } else {
                    Fenetre.Fenetre("Ordonnance n'existe pas");
                    new SaisieException("Ordonnance n'existe pas");
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
