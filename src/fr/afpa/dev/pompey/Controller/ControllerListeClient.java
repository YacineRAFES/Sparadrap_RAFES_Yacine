package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.AchatSansOrdonnance;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Tables.ListeClientTable;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.Refresh;

public class ControllerListeClient extends JFrame {
    private JTable listeClientTable;
    private JScrollPane scrollPane;
    private JLabel titleLabel;
    private JPanel contentPane;
    private JButton creerUnClientButton;
    private JButton fermerButton;

    public ControllerListeClient() {
        setTitle("Liste des clients");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        //le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Tableau
        ListeClientTable model1 = new ListeClientTable(GestionListe.getClient());
        this.listeClientTable.setModel(model1);
        this.listeClientTable.getTableHeader().setResizingAllowed(false);

        //Bouton Détail
        listeClientTable.getColumn("Détail").setCellRenderer(new button.ButtonRenderer());
        listeClientTable.getColumn("Détail").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listeClientTable.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid
                    if (row < GestionListe.getClient().size()){
                        Client client = GestionListe.getClient().get(row);
                        ControllerDetailClient controllerDetailClient = new ControllerDetailClient(client);
                        controllerDetailClient.setVisible(true);
                    } else {
                        Fenetre.Fenetre("Client n'existe pas");
                        new SaisieException("Client n'existe pas");
                    }
                }
            }
        }));

        //Bouton Supprimer
        listeClientTable.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        listeClientTable.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listeClientTable.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid
                    if (row < GestionListe.getClient().size()){
                        Client client = GestionListe.getClient().get(row);
                        GestionListe.removeClient(client);
                    } else {
                        Fenetre.Fenetre("Client n'existe pas");
                        new SaisieException("Client n'existe pas");
                    }
                    Refresh(listeClientTable);
                    Fenetre.Fenetre("Client supprimé");
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