package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Ordonnances;
import fr.afpa.dev.pompey.Modele.Tables.ListeOrdonnancesMed;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * La classe ControllerListeOrdonnanceIdMed est le contrôleur de la fenêtre de liste des ordonnances par identifiant du médecin
 */
public class ControllerListeOrdonnanceIdMed extends JFrame {
    private JPanel contentPane;
    private JLabel titreLabel;
    private JScrollPane scrollPane;
    private JTable tableHistoriqueOrdonnanceidmed;
    private JTextField barreDeRecherche;
    private JLabel rechercheLabel;
    private JButton fermerButton;

    private OrdonnancesDAO ordonnancesDAO;
    private MedecinDAO medecinDAO;

    /**
     * Constructeur de la classe ControllerListeOrdonnanceIdMed
     *
     * @param idMedecin L'identifiant du médecin
     */
    public ControllerListeOrdonnanceIdMed(int idMedecin) {
        ordonnancesDAO = new OrdonnancesDAO();
        medecinDAO = new MedecinDAO();

        Medecin medecin = medecinDAO.find(idMedecin);

        setTitle("Historiques des ordonnances de " + medecin.getNom() + " " +
                medecin.getPrenom());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        titreLabel.setText("Historiques des ordonnances de " + medecin.getNom() + " " +
                medecin.getPrenom());

        // Affichage des ordonnances du médecin
        ListeOrdonnancesMed model1 = new ListeOrdonnancesMed(medecin);
        tableHistoriqueOrdonnanceidmed.setModel(model1);


        //Bouton Détail
        tableHistoriqueOrdonnanceidmed.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        tableHistoriqueOrdonnanceidmed.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), e -> {
            int id = (int) tableHistoriqueOrdonnanceidmed.getValueAt(tableHistoriqueOrdonnanceidmed.getSelectedRow(), 0);
            ControllerDetailAchat controllerDetailAchat = new ControllerDetailAchat(id, 1);
            controllerDetailAchat.setVisible(true);
        }));

        // TODO: A fixé!

        // Fermer la fenêtre
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }
}
