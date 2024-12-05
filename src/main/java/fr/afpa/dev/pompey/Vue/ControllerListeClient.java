package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Modele.Tables.ListeClientTable;
import fr.afpa.dev.pompey.Modele.Tables.ListeHistoriqueAchat;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.InterfaceModel;
import fr.afpa.dev.pompey.Utilitaires.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

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
    private DemandeDAO demandeDAO;
    private CommandeDAO commandeDAO;
    private ListeClientTable model1;


    /**
     * Constructeur de la classe ControllerListeClient
     */
    public ControllerListeClient() {
        clientDAO = new ClientDAO();
        ordonnanceDAO = new OrdonnancesDAO();
        demandeDAO = new DemandeDAO();
        achatDirectDAO = new AchatDirectDAO();
        commandeDAO = new CommandeDAO();

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
        listeClientTable.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), e -> {
            int id = (int) listeClientTable.getValueAt(listeClientTable.getSelectedRow(), 0);
            Client client = clientDAO.find(id);
            if (client != null) {
                List<Ordonnances> ordonnances = ordonnanceDAO.findAllByIdClient(client.getId());
                List<AchatDirect> achatDirects = achatDirectDAO.findAllByIdClient(client.getId());
                //Si le client a des ordonnandes et/ou achatdirect, on demande une confirmation
                if (ordonnances != null || achatDirects != null) {
                    if (Fenetre.Confirmation("Le client a des ordonnances et/ou les achat direct, voulez-vous vraiment le supprimer ?", "Confirmation de suppression")) {
                        //Supprimer les demandes
                        //Trouver tout les id Ordonnances par un ID client
                        List<Ordonnances> ordonnancesList = ordonnanceDAO.findAllByIdClient(client.getId());

                        for (Ordonnances ordonnance : ordonnancesList) {
                            List<Demande> demandes = demandeDAO.findAllByOrdonnance(ordonnance.getId());
                            demandes.forEach(demandeDAO::delete);
                            ordonnanceDAO.delete(ordonnance);
                        }
                        //Supprimer les commandes
                        List<AchatDirect> achatDirectList = achatDirectDAO.findAllByIdClient(client.getId());
                        for (AchatDirect achatDirect : achatDirectList) {
                            List<Commande> commandes = commandeDAO.findAllByIdClient(client.getId());
                            commandes.forEach(commandeDAO::delete);
                            achatDirectDAO.delete(achatDirect);
                        }
//                        Supprimer le client
                        clientDAO.delete(client);
                        model1.refreshList();
                    }
                }
            }
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