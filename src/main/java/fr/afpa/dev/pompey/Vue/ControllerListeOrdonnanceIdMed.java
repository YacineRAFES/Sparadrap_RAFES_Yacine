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

import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.ButtonDetail;

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
        tableHistoriqueOrdonnanceidmed.setModel(new ListeOrdonnancesMed((Medecin) ordonnancesDAO.findAllByIdMed(medecin.getId())));

        //Bouton Détail
        tableHistoriqueOrdonnanceidmed.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        tableHistoriqueOrdonnanceidmed.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), e -> {
            ButtonDetail(e, ordonnancesDAO, ControllerDetailAchat.class);
        }));

        //A fix!

        // Fermer la fenêtre
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }
}
