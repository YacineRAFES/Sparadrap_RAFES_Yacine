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


        String[] medicament = {"Nom de médicament", "Quantité", "Prix", "Action"};
        String[][] data = {
                {"Doliprane", "3", "2.5", "Supprimer"},
                {"Ibuprofène", "2", "3.5", "Supprimer"},
                {"Paracétamol", "1", "1.5", "Supprimer"}
        };
        DefaultTableModel model = new DefaultTableModel(data, medicament);
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
                    DefaultTableModel model = (DefaultTableModel) listeDeMedocTable.getModel();
                    model.removeRow(row);
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
        clientController.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
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
        controllerMedecin.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
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
        controllerMedicament.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
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
        if (medicamentSelected instanceof MedicamentDAO) {
            nameMedicament = (Medicament) medicamentSelected;
        } else if (medicamentSelected instanceof String) {
            nameMedicament = new Medicament((String) medicamentSelected);
        }
        int newIdMedicament = 0;
        for (Medicament medicamentCheck : medicamentDAO.findAll()) {
            if (medicamentCheck.getNom().equals(nameMedicament.getNom())) {
                Fenetre.Fenetre("Le médicament existe déjà");
                throw new SaisieException();
            } else {
                Medicament medicament = new Medicament(
                        nameMedicament.getNom()
                );
                newIdMedicament = medicamentDAO.create(medicament);
            }
        }

        if (medicamentSelected == null) {
            ShowLabelWithTimer(informationLabel, "Veuillez sélectionner ou ajouter un médicament", Color.RED);
            throw new SaisieException();
        }

        // Rafraîchir le modèle de la combobox
        DefaultComboBoxModel<Medicament> comboBoxModel3 = new DefaultComboBoxModel<>();
        for (Medicament medicamentList : medicamentDAO.findAll()) {
            comboBoxModel3.addElement(medicamentList);
        }
        medicamentCombobox.setModel(comboBoxModel3);

        //Ajouter une ligne dans la table
        DefaultTableModel model = (DefaultTableModel) listeDeMedocTable.getModel();
        model.addRow(new Object[]{nameMedicament.getNom(), 1, nameMedicament.getPrix(), "Supprimer"});

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
            Object newIdAchatDirect = achatDirectDAO.create(achatDirect);

            //Faire un boucle for qui lit la table côté client et ajouter dans commandeDAO à chaque fois
            for(int i = 0; i < listeDeMedocTable.getRowCount(); i++){
                //On récupère les informations du tableau
                Medicament medicament = new Medicament();
                medicament.setId((Integer) listeDeMedocTable.getValueAt(i, 0));
                int quantite = Integer.parseInt(listeDeMedocTable.getValueAt(i, 1).toString());
                Commande commande = new Commande(medicament, (AchatDirect) newIdAchatDirect, quantite);
                commandeDAO.create(commande);
            }
        } else if (typeAchat == 2) {
            Ordonnances ordonnances = new Ordonnances(DateCustom.DateNow(), (Client) clientSelected, (Medecin) medecinSelected);
            ordonnancesDAO.create(ordonnances);
            //Faire un boucle for qui lit la table côté client et ajouter dans commandeDAO à chaque fois
            for(int i = 0; i < listeDeMedocTable.getRowCount(); i++){
                Medicament medicament = new Medicament();
                medicament.setId((Integer) listeDeMedocTable.getValueAt(i, 0));
                int quantite = Integer.parseInt(listeDeMedocTable.getValueAt(i, 1).toString());
                Demande demande = new Demande(quantite, medicament, ordonnances);
                demandeDAO.create(demande);
            }
        }
        // Afficher un message de confirmation
        ShowLabelWithTimer(informationLabel, "Achat effectué", Color.GREEN);
        // Actualiser les saisies
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