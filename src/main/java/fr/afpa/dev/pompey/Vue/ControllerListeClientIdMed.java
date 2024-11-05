package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Tables.ListeClientMed;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.filterTable;

/**
 * La classe ControllerListeClientIdMed est le contrôleur de la fenêtre de liste des clients par identifiant du médecin
 */
public class ControllerListeClientIdMed extends JFrame {
    private JPanel contentPane;
    private JTextField barreDeRecherche;
    private JTable listeDesClientByIdMed;
    private JLabel ListesDesClientLabel;
    private JLabel rechercheLabel;
    private JButton fermerButton;

    /**
     * Constructeur de la classe ControllerListeClientIdMed
     *
     * @param medecin Le médecin
     */
    public ControllerListeClientIdMed(Medecin medecin){
        setTitle("Liste des clients de " + medecin.getNomMedecin() + " " + medecin.getPrenomMedecin());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        ListesDesClientLabel.setText("Liste des clients de " + medecin.getNomMedecin() + " " + medecin.getPrenomMedecin());

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        ListeClientMed model1 = new ListeClientMed(medecin);

        // Affichage des clients du médecin
        listeDesClientByIdMed.setModel(new ListeClientMed(medecin));

        filterTable(barreDeRecherche, model1, listeDesClientByIdMed);

        // Fermer la fenêtre
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }
}
