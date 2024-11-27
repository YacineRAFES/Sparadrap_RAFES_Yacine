package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Utilitaires.*;

import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

/**
 * La classe ControllerAchat est le contrôleur de la fenêtre d'achat
 */
public class ControllerAchat extends JFrame {
    private JPanel contentPane;

    private JButton annulerButton;
    private JButton validerButton;
    private JComboBox clientCombobox;
    private JButton creerUnClientButton;
    private JButton creerUnMedecinButton;
    private JButton creerUnMedicamentButton;
    private JComboBox medecinCombobox;
    private JComboBox medicamentCombobox;
    private JTable listeDeMedocTable;
    private JScrollPane scrollPane;
    private JLabel medicamentLabel;
    private JLabel typeachatLabel;
    private JLabel clientLabel;
    private JLabel medecinLabel;
    private JComboBox typeAchatCombobox;
    private JButton ajouterUnMedicamentButton;
    private JLabel prixLabel;
    private JLabel informationLabel;

    private ClientDAO clientDAO;
    private MedecinDAO medecinDAO;
    private MedicamentDAO medicamentDAO;
    private AchatDirectDAO achatDirectDAO;
    private OrdonnancesDAO ordonnancesDAO;
    private CommandeDAO commandeDAO;
    private DemandeDAO demandeDAO;

    /**
     * Constructeur de la classe ControllerAchat
     */
    public ControllerAchat() {
        // Initialisation des DAO
        clientDAO = new ClientDAO();
        medecinDAO = new MedecinDAO();
        medicamentDAO = new MedicamentDAO();
        achatDirectDAO = new AchatDirectDAO();
        ordonnancesDAO = new OrdonnancesDAO();
        commandeDAO = new CommandeDAO();
        demandeDAO = new DemandeDAO();


        // Initialisation de la fenêtre
        setTitle("Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(true);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Ajout des éléments avec ID dans le DefaultComboBoxModel
        String[] TypeAchat = {"Type d'achat", "Achat direct", "Via ordonnance"};
        DefaultComboBoxModel<String> typeAchatModel = new DefaultComboBoxModel<>(TypeAchat);
        typeAchatCombobox.setModel(typeAchatModel);

        // Création de la table


        Object[] medicament = {"ID", "Nom de médicament", "Quantité", "Prix", "Action"};
        DefaultTableModel model = new DefaultTableModel(medicament, 0);
        listeDeMedocTable.setModel(model);
        // Actualiser les combobox
        actualiserComboClient();
        actualiserComboMedecin();
        actualiserComboMedicament();


        // Quand le texte dépasse la largeur de la colonne, on ajout "..."
        for (int i = 0; i < listeDeMedocTable.getColumnCount(); i++) {
            listeDeMedocTable.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
        }

        // Les boutons de la table
        // Supprimer un médicament de la liste
        listeDeMedocTable.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        listeDeMedocTable.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listeDeMedocTable.getEditingRow();
                if (row >= 0) {
                    // Supprimer la ligne
                    InterfaceModel.SuppressionLigneDansTableau(listeDeMedocTable, row);
                    ShowLabelWithTimer(informationLabel, "Médicament supprimé de la liste", Color.RED);
                    Refresh(listeDeMedocTable);
                }
            }
        }));

        // Les boutons de listeners
        // Ajouter un client
        creerUnClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client();
            }
        });

        // Ajouter un médecin
        creerUnMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medecin();
            }
        });

        // Ajouter un médicament
        creerUnMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicament();
            }
        });

        // Ajouter un médicament dans la liste
        ajouterUnMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ajouterUnMedicament();
                } catch (SaisieException ex) {
                    new SaisieException("Erreur d'ajout de médicament");
                }
            }
        });

        // Valider l'achat
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    valider();
                } catch (SaisieException ex) {
                    new SaisieException("Erreur de validation");
                }
            }
        });

        // Annuler l'achat
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    annuler();
                }catch (SaisieException ex){
                    new SaisieException("Erreur lors de l'annulation");
                }
            }
        });

        // Listeners pour le combobox si on change le type d'achat et le prix total
        typeAchatCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeAchatCombobox.getSelectedIndex() == 1) {
                    medecinCombobox.setEnabled(false);
                    medecinCombobox.setSelectedItem(null);
                    medecinLabel.setForeground(Color.GRAY);
                    creerUnMedecinButton.setEnabled(false);
                } else {
                    medecinCombobox.setEnabled(true);
                    medecinLabel.setForeground(Color.BLACK);
                    creerUnMedecinButton.setEnabled(true);
                }
            }
        });
    }

    /**
     * Méthode pour ouvrir une fenêtre pour créer un client
     */
    private void client() {
        ControllerClient clientController = new ControllerClient();
        clientController.setVisible(true);
        clientController.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                actualiserComboClient();
                actualiserComboMedecin();
            }
        });
    }

    /**
     * Méthode pour ouvrir une fenêtre pour créer un médecin
     */
    private void medecin(){
        ControllerMedecin controllerMedecin = new ControllerMedecin();
        controllerMedecin.setVisible(true);
        controllerMedecin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                actualiserComboMedecin();
            }
        });
    }

    /**
     * Méthode pour ouvrir une fenetre pour créer un médicament
     */
    private void medicament(){
        ControllerMedicament controllerMedicament = new ControllerMedicament();
        controllerMedicament.setVisible(true);
        controllerMedicament.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                actualiserComboMedicament();
            }
        });
    }

    /**
     * Méthode pour ajouter un médicament dans la table
     *
     */
    private void ajouterUnMedicament() throws SaisieException {
        // Vérifier si un médicament est sélectionné dans la combobox
        Object medicamentSelected = medicamentCombobox.getSelectedItem();
        Medicament nameMedicament = null;
        if (medicamentSelected instanceof Medicament) {
            nameMedicament = (Medicament) medicamentSelected;
        } else if (medicamentSelected instanceof String) {
            nameMedicament = new Medicament((String) medicamentSelected);
        }

        if (nameMedicament == null) {
            ShowLabelWithTimer(informationLabel, "Veuillez sélectionner ou ajouter un médicament", Color.RED);
            throw new SaisieException();
        }

        for(int i = 0; i < listeDeMedocTable.getRowCount(); i++){
            if(listeDeMedocTable.getValueAt(i,1).equals(nameMedicament.getNom())){
                ShowLabelWithTimer(informationLabel, "Le médicament est déjà dans la liste", Color.RED);
                throw new SaisieException();
            }
        }
        //Ajouter une ligne dans la table
        DefaultTableModel model = (DefaultTableModel) listeDeMedocTable.getModel();
        model.addRow(new Object[]{nameMedicament.getId(), nameMedicament.getNom(), 1, nameMedicament.getPrix(), "Supprimer"});

        // Rafraîchir le modèle de la combobox
        DefaultComboBoxModel<Medicament> comboBoxModel3 = new DefaultComboBoxModel<>();
        for (Medicament medicamentList : medicamentDAO.findAll()) {
            comboBoxModel3.addElement(medicamentList);
        }
        medicamentCombobox.setModel(comboBoxModel3);

        // Rafraîchir la table
        Refresh(listeDeMedocTable);
    }

    /**
     * Valider l'achat
     */
    private void valider() throws SaisieException {
        Refresh(listeDeMedocTable);
        int typeAchat = typeAchatCombobox.getSelectedIndex();
        Object clientSelected = clientCombobox.getSelectedItem();
        Object medecinSelected = medecinCombobox.getSelectedItem();

        if (typeAchat == 0) {
            ShowLabelWithBlinker(informationLabel, "Veuillez sélectionner un type d'achat valide", Color.RED);
            setComboBoxColor(typeAchatCombobox, Color.RED);
            throw new SaisieException();
        } else if (typeAchat == 1) {
            AchatDirect achatDirect = new AchatDirect(DateCustom.DateNow(), (Client) clientSelected);
            int newIdAchatDirect = achatDirectDAO.create(achatDirect);

            for (int i = 0; i < listeDeMedocTable.getRowCount(); i++) {
                Object medicamentId = listeDeMedocTable.getValueAt(i, 0);
                if ((int) medicamentId == 0) {
                    String nomMedicament = listeDeMedocTable.getValueAt(i, 1).toString();
                    double prixMedicament = Double.parseDouble(listeDeMedocTable.getValueAt(i, 3).toString());
                    Medicament medicament = new Medicament(nomMedicament, DateCustom.DateNow(), 1, prixMedicament, 1);
                    int newIdMedicament = medicamentDAO.create(medicament);
                    int quantite = Integer.parseInt(listeDeMedocTable.getValueAt(i, 2).toString());
                    Commande commande = new Commande(newIdMedicament, newIdAchatDirect, quantite);
                    commandeDAO.create(commande);
                } else {
                    int quantite = Integer.parseInt(listeDeMedocTable.getValueAt(i, 2).toString());
                    Commande commande = new Commande((Integer) medicamentId, newIdAchatDirect, quantite);
                    commandeDAO.create(commande);
                }
                ShowLabel(informationLabel, "Création de la commande effectué", Color.GREEN);
            }
            ShowLabelWithTimer(informationLabel, "Achat effectué", Color.GREEN);
        } else if (typeAchat == 2) {
            Ordonnances ordonnances = new Ordonnances(DateCustom.DateNow(), (Client) clientSelected, (Medecin) medecinSelected);
            int newidordonnances = ordonnancesDAO.create(ordonnances);

            for (int i = 0; i < listeDeMedocTable.getRowCount(); i++) {
                Object medicamentId = listeDeMedocTable.getValueAt(i, 0);
                if ((int) medicamentId == 0) {
                    String nomMedicament = listeDeMedocTable.getValueAt(i, 1).toString();
                    double prixMedicament = Double.parseDouble(listeDeMedocTable.getValueAt(i, 3).toString());
                    Medicament medicament = new Medicament(nomMedicament, DateCustom.DateNow(), 1, prixMedicament, 1);
                    int newIdMedicament = medicamentDAO.create(medicament);
                    int quantite = Integer.parseInt(listeDeMedocTable.getValueAt(i, 2).toString());
                    Demande demande = new Demande(newidordonnances, newIdMedicament, quantite);
                    demandeDAO.create(demande);
                } else {
                    int quantite = Integer.parseInt(listeDeMedocTable.getValueAt(i, 2).toString());
                    Demande demande = new Demande(newidordonnances, (Integer) medicamentId, quantite);
                    demandeDAO.create(demande);
                }
            }
            ShowLabel(informationLabel, "Création de la demande effectué", Color.GREEN);
        }
        ShowLabelWithTimer(informationLabel, "Achat effectué", Color.GREEN);

        //Après l'achat effectué, on vide la table après 2 secondes
        TimerAppelFonction(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) listeDeMedocTable.getModel();
                model.setRowCount(0);
                Refresh(listeDeMedocTable);
            }
        });

        annuler();
    }

    /**
     * Annuler l'achat
     */
    private void annuler() throws SaisieException {
        if (typeAchatCombobox.getItemCount() > 0) {
            typeAchatCombobox.setSelectedIndex(0);
        }
        if (clientCombobox.getItemCount() > 0) {
            clientCombobox.setSelectedIndex(0);
        }
        if (medicamentCombobox.getItemCount() > 0) {
            medicamentCombobox.setSelectedIndex(0);
        }
        if (medecinCombobox.getItemCount() > 0) {
            medecinCombobox.setSelectedIndex(0);
        }

        actualiserComboClient();
        actualiserComboMedecin();
        actualiserComboMedicament();
    }

    /**
     * Actualiser la combobox des clients
     */
    private void actualiserComboClient() {
        DefaultComboBoxModel<Client> comboBoxModel1 = new DefaultComboBoxModel<>();
        comboBoxModel1.removeAllElements();
        for (Client client : clientDAO.findAll()) {
            comboBoxModel1.addElement(client);
        }
        clientCombobox.setModel(comboBoxModel1);
    }

    /**
     * Actualiser la combobox des médecins
     */
    private void actualiserComboMedecin() {
        DefaultComboBoxModel<Medecin> comboBoxModel2 = new DefaultComboBoxModel<>();
        comboBoxModel2.removeAllElements();
        for (Medecin medecin : medecinDAO.findAll()) {
            comboBoxModel2.addElement(medecin);
        }
        medecinCombobox.setModel(comboBoxModel2);
    }

    /**
     * Actualiser la combobox des médicaments
     */
    private void actualiserComboMedicament() {
        DefaultComboBoxModel<Medicament> comboBoxModel3 = new DefaultComboBoxModel<>();
        comboBoxModel3.removeAllElements();
        for (Medicament medicament : medicamentDAO.findAll()) {
            comboBoxModel3.addElement(medicament);
        }
        medicamentCombobox.setModel(comboBoxModel3);
    }



}