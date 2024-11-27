package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.AchatDirect;
import fr.afpa.dev.pompey.Modele.DAO.AchatDirectDAO;
import fr.afpa.dev.pompey.Modele.DAO.CommandeDAO;
import fr.afpa.dev.pompey.Modele.DAO.DemandeDAO;
import fr.afpa.dev.pompey.Modele.DAO.OrdonnancesDAO;
import fr.afpa.dev.pompey.Modele.Ordonnances;
import fr.afpa.dev.pompey.Modele.Tables.ListeHistoriqueAchat;
import fr.afpa.dev.pompey.Utilitaires.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.*;

/**
 * La classe ControllerHistoriqueAchat est le contrôleur de la fenêtre de l'historique d'achat
 */
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
    private ListeHistoriqueAchat model1;

    /**
     * Constructeur de la classe ControllerHistoriqueAchat
     */
    public ControllerHistoriqueAchat() {
        setTitle("Historique d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        //le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        AchatDirectDAO achatDirectDAO = new AchatDirectDAO();
        OrdonnancesDAO ordonnancesDAO = new OrdonnancesDAO();
        CommandeDAO commandeDAO = new CommandeDAO();
        DemandeDAO demandeDAO = new DemandeDAO();

        // Remplir le tableau
        model1 = new ListeHistoriqueAchat(achatDirectDAO.findAll(), ordonnancesDAO.findAll());
        this.tableHistoriqueAchat.setModel(model1);
        this.tableHistoriqueAchat.getTableHeader().setResizingAllowed(false);

        // Ajouter les boutons dans le tableau
        // Détail
        tableHistoriqueAchat.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        tableHistoriqueAchat.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), e -> {
            int row = tableHistoriqueAchat.getEditingRow();
            String type = (String) tableHistoriqueAchat.getValueAt(row, 3);
            int id = (int) tableHistoriqueAchat.getValueAt(row, 0);

            if("Sans Ordonnance".equals(type)) {
                ControllerDetailAchat controllerDetailAchat = new ControllerDetailAchat(id, 0);
                controllerDetailAchat.setVisible(true);
            } else if("Ordonnance".equals(type)) {
                ControllerDetailAchat controllerDetailAchat = new ControllerDetailAchat(id, 1);
                controllerDetailAchat.setVisible(true);
            }
        }));

// Supprimer
        tableHistoriqueAchat.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        tableHistoriqueAchat.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), e -> {
            int row = tableHistoriqueAchat.getEditingRow();
            String type = (String) tableHistoriqueAchat.getValueAt(row, 3);
            if("Sans Ordonnance".equals(type)) {
                int id = (int) tableHistoriqueAchat.getValueAt(row, 0);
                AchatDirect achatDirect = achatDirectDAO.find(id);
                if(achatDirect!= null) {
                    commandeDAO.findAllByAchatDirect(achatDirect.getId()).forEach(commandeDAO::delete);
                    achatDirectDAO.delete(achatDirect);
                    ShowLabelWithTimer(informationLabel, "Achat supprimé dans l'historique", Color.RED);
                } else {
                    ShowLabelWithTimer(informationLabel, "Achat non trouvé", Color.RED);
                }
            } else if("Ordonnance".equals(type)) {
                int id = (int) tableHistoriqueAchat.getValueAt(row, 0);
                Ordonnances ordonnances = ordonnancesDAO.find(id);
                if(ordonnances != null) {
                    demandeDAO.findAllByOrdonnance(ordonnances.getId()).forEach(demandeDAO::delete);
                    ordonnancesDAO.delete(ordonnances);
                    ShowLabelWithTimer(informationLabel, "Ordonnance supprimée dans l'historique", Color.GREEN);
                } else {
                    ShowLabelWithTimer(informationLabel, "Ordonnance non trouvée", Color.RED);
                }
            }
            model1.refreshList();

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