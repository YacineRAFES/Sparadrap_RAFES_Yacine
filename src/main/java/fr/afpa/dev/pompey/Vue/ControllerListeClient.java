package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.DAO.AchatDirectDAO;
import fr.afpa.dev.pompey.Modele.DAO.ClientDAO;
import fr.afpa.dev.pompey.Modele.DAO.OrdonnancesDAO;
import fr.afpa.dev.pompey.Modele.Ordonnances;
import fr.afpa.dev.pompey.Modele.Region;
import fr.afpa.dev.pompey.Modele.Tables.ListeClientTable;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.InterfaceModel;
import fr.afpa.dev.pompey.Utilitaires.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.*;

/**
 * La classe ControllerListeClient est le contrôleur de la fenêtre de la liste des clients
 */
public class ControllerListeClient extends JFrame {
    private JTable listeClientTable;
    private JScrollPane scrollPane;
    private JLabel titleLabel;
    private JPanel contentPane;
    private JButton creerUnClientButton;
    private JButton fermerButton;
    private JPanel affichageAlertePanel;
    private JLabel informationLabel;
    private ClientDAO clientDAO;
    private OrdonnancesDAO ordonnanceDAO;
    private AchatDirectDAO achatDirectDAO;


    /**
     * Constructeur de la classe ControllerListeClient
     */
    public ControllerListeClient() {
        clientDAO = new ClientDAO();
        ordonnanceDAO = new OrdonnancesDAO();
        achatDirectDAO = new AchatDirectDAO();

        setTitle("Liste des clients");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        //le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Tableau
        ListeClientTable model1 = new ListeClientTable(clientDAO.findAll());
        this.listeClientTable.setModel(model1);
        this.listeClientTable.getTableHeader().setResizingAllowed(false);

        //Bouton Détail
        listeClientTable.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        listeClientTable.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), e ->  {
            int id = (int) listeClientTable.getValueAt(listeClientTable.getSelectedRow(), 0);
            ControllerDetailClient controllerDetailClient = new ControllerDetailClient(id);
            controllerDetailClient.setVisible(true);
        }));

        //Bouton Supprimer
        listeClientTable.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        listeClientTable.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), e ->  {
            int id = (int) listeClientTable.getValueAt(listeClientTable.getSelectedRow(), 0);
            Client client = clientDAO.find(id);
            if(client != null){
                Ordonnances ordonnances = (Ordonnances) ordonnanceDAO.findAllByIdClient(client.getId());
                if(ordonnances != null) {
                    Fenetre.Confirmation("Le client a des ordonnances, voulez-vous vraiment le supprimer ?", "Confirmation");
                    if (Fenetre.Confirmation("Le client a des ordonnances, voulez-vous vraiment le supprimer ?", "Confirmation")) {
                        //TODO A REVOIR
//                        InterfaceModel.ButtonSupprimer(clientDAO, client.getId());
//                        InterfaceModel.ButtonSupprimer(ordonnanceDAO, ordonnances.getId());
//                        InterfaceModel.ButtonSupprimer(achatDirectDAO, ordonnances.getId());
                        Refresh(listeClientTable);
                    }
                }
            }
            clientDAO.find(id);
        }));


        //Bouton Créer un client
        creerUnClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerClient controllerClient = new ControllerClient();
                controllerClient.setVisible(true);
                //si un client est créé, on rafraichit la liste des clients
                controllerClient.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Refresh(listeClientTable);
                    }
                });
            }
        });

        //Bouton Fermer la fenetre de la liste des clients
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}