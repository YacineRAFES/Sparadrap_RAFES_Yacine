// src/fr/afpa/dev/pompey/Controller/ControllerListeMedecin.java
package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.DAO.ClientDAO;
import fr.afpa.dev.pompey.Modele.DAO.MedecinDAO;
import fr.afpa.dev.pompey.Modele.DAO.OrdonnancesDAO;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedecinTable;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private MedecinDAO medecinDAO;
    private ClientDAO clientDAO;
    private OrdonnancesDAO ordonnancesDAO;
    /**
     * Constructeur de la classe ControllerListeMedecin
     */
    public ControllerListeMedecin() {
        //Initialisation des DAO
        medecinDAO = new MedecinDAO();
        clientDAO = new ClientDAO();
        ordonnancesDAO = new OrdonnancesDAO();


        setTitle("Liste des Médecins");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Tableau
        ListeMedecinTable model1 = new ListeMedecinTable(medecinDAO.findAll());
        this.listeMedecinTable1.setModel(model1);
        this.listeMedecinTable1.getTableHeader().setResizingAllowed(false);

        //Bouton Détail
        listeMedecinTable1.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        listeMedecinTable1.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), e -> {
            int id = (int) listeMedecinTable1.getValueAt(listeMedecinTable1.getSelectedRow(), 0);
            ControllerDetailMedecin controllerDetailMedecin = new ControllerDetailMedecin(id);
            controllerDetailMedecin.setVisible(true);
        }));

        //Bouton Supprimer
        listeMedecinTable1.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        listeMedecinTable1.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), e -> {
            int row = listeMedecinTable1.getEditingRow();
            int id = (int) listeMedecinTable1.getValueAt(row, 0);
            Medecin medecin = medecinDAO.find(id);
            if(ordonnancesDAO.findAllByIdMed(medecin.getId()) != null) {
                ShowLabelWithTimer(
                        informationLabel,
                        "Impossible de supprimer ce médecin car il a des ordonnances enregistrées",
                        Color.RED
                );
            } else {
                medecinDAO.delete(medecin);
            }
            Refresh(listeMedecinTable1); // Rafraîchir la table après la suppression
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