package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Modele.DAO.MedecinDAO;
import fr.afpa.dev.pompey.Modele.DAO.OrdonnancesDAO;
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
    private MedecinDAO medecinDAO;
    private OrdonnancesDAO ordonnancesDAO;

    /**
     * Constructeur de la classe ControllerListeClientIdMed
     *
     * @param IdMedecin L'identifiant du médecin
     */
    public ControllerListeClientIdMed(int IdMedecin){
        //Initialisation des DAO
        medecinDAO = new MedecinDAO();
        ordonnancesDAO = new OrdonnancesDAO();

        Medecin medecin = medecinDAO.find(IdMedecin);

        setTitle("Liste des clients de " + medecin.getNom() + " " +
                medecin.getPrenom());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        ListesDesClientLabel.setText("Liste des clients de " + medecin.getNom() + " " +
                medecin.getPrenom());

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        ListeClientMed model1 = new ListeClientMed(medecin);

        // Affichage des clients du médecin
        listeDesClientByIdMed.setModel(model1);

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
