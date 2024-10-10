package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Tables.ListeClientTable;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.button;

import javax.swing.*;
import java.awt.event.*;

import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.Refresh;

public class ControllerListeClient extends JFrame {
    private JTable listeClientTable;
    private JScrollPane scrollPane;
    private JLabel titleLabel;
    private JPanel contentPane;
    private JButton creerUnClientButton;
    private JButton fermerButton;
    private JPanel affichageAlertePanel;
    private JLabel informationLabel;

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
                    if (row < GestionListe.getClient().size()) {
                        Client client = GestionListe.getClient().get(row);
                        ControllerDetailClient controllerDetailClient = new ControllerDetailClient(client);
                        controllerDetailClient.setVisible(true);

                        // Écouteur pour rafraîchir la table quand la fenêtre se ferme
                        controllerDetailClient.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                // Rafraîchir la table une fois la fenêtre fermée
                                Refresh(listeClientTable);
                            }
                        });
                    } else {
                        Fenetre.Fenetre("Le client n'existe pas");
                        new SaisieException("Le client n'existe pas");
                    }
                }
            }
        }));

        //Bouton Supprimer
        listeClientTable.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        listeClientTable.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listeClientTable.getEditingRow(); // Obtenez la ligne en cours d'édition (cliquée)
                if (row >= 0) { // Vérifiez que l'index de la ligne est valide
                    if (row < GestionListe.getClient().size()) {
                        Client client = GestionListe.getClient().get(row);

                        // Vérifiez si le client est lié à une ordonnance
                        boolean ordonnanceLie = GestionListe.getOrdonnance().stream()
                                .anyMatch(ordonnance -> ordonnance.getClient().equals(client));
                        // Vérifiez si le client est lié à un achat sans ordonnance
                        boolean achatSansOrdonnanceLie = GestionListe.getAchatSansOrdonnance().stream()
                                .anyMatch(achatSansOrdonnance -> achatSansOrdonnance.getClient().equals(client));

                        if (ordonnanceLie || achatSansOrdonnanceLie) {
                            // Si le client est lié à une ordonnance ou à un achat sans ordonnance, on affiche un message d'erreur
                            Fenetre.Fenetre("Client lié à une ordonnance ou un achat sans ordonnance, impossible de le supprimer");
                        } else {
                            // Supprimer le client de la liste des clients
                            GestionListe.getClient().remove(client);

                            // Supprimer les achats sans ordonnance liés à ce client (au cas où ils existeraient)
                            GestionListe.getAchatSansOrdonnance().removeIf(achatSansOrdonnance -> achatSansOrdonnance.getClient().equals(client));

                            // Rafraîchir la table après la suppression
                            Refresh(listeClientTable);
                            Fenetre.Fenetre("Client supprimé");
                        }
                    } else {
                        Fenetre.Fenetre("Client n'existe pas");
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