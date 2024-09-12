package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;
import fr.afpa.dev.pompey.Modele.Utilitaires.*;

import static fr.afpa.dev.pompey.Modele.GestionListe.*;
import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    public ControllerAchat() {
        //TODO Faire l'historique des achats
        //TODO quand on crée un médicament avec la quantité, dans la tabletemporaire, la quantité est à 0
        //TODO AJOUTER LE PRIX DANS LA TABLE TEMPORAIRE

        setTitle("Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(true);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Fonctionnalité qui permet d'afficher le placeholder dans un combobox
//        AjouterPlaceholderComboboxNonEditable(typeAchatCombobox, "Type d'achat");
//        AjouterPlaceholderComboboxEditable(clientCombobox, "Selectionner un Client");
//        AjouterPlaceholderComboboxEditable(medecinCombobox, "Selectionner un Médecin");
//        AjouterPlaceholderComboboxEditable(medicamentCombobox, "Selectionner un Medicament");

        // Ajout des éléments avec ID dans le DefaultComboBoxModel
        DefaultComboBoxModel<TypeAchat> typeAchatModel = new DefaultComboBoxModel<>();
        typeAchatModel.addElement(new TypeAchat(0, "Type d'achat"));
        typeAchatModel.addElement(new TypeAchat(1, "Achat direct"));
        typeAchatModel.addElement(new TypeAchat(2, "Via ordonnance"));
        typeAchatCombobox.setModel(typeAchatModel);

        ListeMedicamentTableModel model1 = new ListeMedicamentTableModel(GestionListe.getTableMedicamentTemporaire());
        this.listeDeMedocTable.setModel(model1);
        this.listeDeMedocTable.getTableHeader().setResizingAllowed(false);

        model1.addTableModelListener(e -> PrixTotalLabel());

        actualiserComboClient();
        actualiserComboMedecin();
        actualiserComboMedicament();

        // Quand le texte dépasse la largeur de la colonne, on ajout "..."
        for (int i = 0; i < listeDeMedocTable.getColumnCount(); i++) {
            listeDeMedocTable.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
        }

        listeDeMedocTable.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        listeDeMedocTable.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listeDeMedocTable.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid
                    TableMedicamentTemporaire temp = GestionListe.getTableMedicamentTemporaire().get(row);
                    GestionListe.removeTableMedicamentTemporaire(temp);
                    Refresh(listeDeMedocTable);
                    PrixTotalLabel();
                    Fenetre.Fenetre("Médicament supprimé de la liste");
                }
            }
        }));

        // Les boutons de listeners
        creerUnClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client();
            }
        });
        creerUnMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medecin();
            }
        });
        creerUnMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicament();
            }
        });
        ajouterUnMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ajouterUnMedicament();
                } catch (SaisieException ex) {
                    new RuntimeException(ex);
                }
            }
        });
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    valider();
                } catch (SaisieException ex) {
                    new RuntimeException(ex);
                }
            }
        });
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annuler();
            }
        });
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

    // les actions
    // Ajouter un client
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

    // Ajouter un médecin
    private void medecin() {
        ControllerMedecin controllerMedecin = new ControllerMedecin();
        controllerMedecin.setVisible(true);
        controllerMedecin.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                actualiserComboMedecin();
            }
        });
    }

    // Ajouter un médicament
    private void medicament() {
        ControllerMedicament controllerMedicament = new ControllerMedicament();
        controllerMedicament.setVisible(true);
        controllerMedicament.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                actualiserComboMedicament();
            }
        });
    }

    // Ajouter un médicament
    private void ajouterUnMedicament() throws SaisieException {
        // Vérifier si un médicament est sélectionné dans la combobox

        Object selectedItem = medicamentCombobox.getSelectedItem();
        Medicament selectedMedicament = null;
        if (selectedItem instanceof Medicament) {
            selectedMedicament = (Medicament) selectedItem;
        } else if (selectedItem instanceof String) {
            selectedMedicament = new Medicament((String) selectedItem);
        }

        if (selectedMedicament == null) {
            Fenetre.Fenetre("Veuillez sélectionner ou ajouter un médicament");
            throw new SaisieException();
        }

        String nomMedicament = selectedMedicament.getNom();
        Medicament medicament = new Medicament(nomMedicament);
        TableMedicamentTemporaire tableMedicamentTemporaire = new TableMedicamentTemporaire(selectedMedicament, 0, selectedMedicament.getPrix());


        // Vérifier si le médicament est déjà dans la table temporaire
        for (TableMedicamentTemporaire uniqueMedicamentTemp : getTableMedicamentTemporaire()) {
            if (uniqueMedicamentTemp.getNom().equals(medicament.getNom())) {
                Fenetre.Fenetre("Ce médicament est déjà ajouté");
                throw new SaisieException();
            }
        }

        // Ajouter le médicament à la table temporaire
        GestionListe.addTableMedicamentTemporaire(tableMedicamentTemporaire);

        // Vérifier si le médicament est déjà dans la liste globale
        boolean existsInList = false;
        for (Medicament uniqueMedicament : getMedicament()) {
            if (uniqueMedicament.getNom().equals(medicament.getNom())) {
                existsInList = true;
                break;
            }
        }

        // Ajouter le médicament s'il n'existe pas dans la liste
        if (!existsInList) {
            GestionListe.addMedicament(medicament);
        }

        // Rafraîchir le modèle de la combobox
        DefaultComboBoxModel<Medicament> comboBoxModel3 = new DefaultComboBoxModel<>();
        for (Medicament medoc : getMedicament()) {
            comboBoxModel3.addElement(medoc);
        }
        medicamentCombobox.setModel(comboBoxModel3);
//        prixLabel.setText((ListeMedicamentTableModel) listeDeMedocTable.getModel().g);

        PrixTotalLabel();
        // Rafraîchir la table
        Refresh(listeDeMedocTable);
    }

    //Validation de l'achat
    private void valider() throws SaisieException {
        Refresh(listeDeMedocTable);
        int typeAchat = typeAchatCombobox.getSelectedIndex();
        ListeMedicamentTableModel model = (ListeMedicamentTableModel) listeDeMedocTable.getModel();
        List<TableMedicamentTemporaire> medicamentList = model.getMedicamentList();
        String[][] listeMedicament = new String[medicamentList.size()][3];
        for (int i = 0; i < medicamentList.size(); i++) {
            listeMedicament[i][0] = medicamentList.get(i).getNom();
            listeMedicament[i][1] = String.valueOf(medicamentList.get(i).getQuantite());
            int prixMedoc;
            if(medicamentList.get(i).getPrix() == null || medicamentList.get(i).getPrix().trim().isEmpty()){
                prixMedoc = 0;
            }else{
                prixMedoc = Integer.parseInt(medicamentList.get(i).getPrix());
            }
            listeMedicament[i][2] = String.valueOf(medicamentList.get(i).getQuantite() * prixMedoc);
        }

        System.out.println(Arrays.deepToString(listeMedicament));

        if (typeAchat == 0) {
            Fenetre.Fenetre("Veuillez sélectionner un type d'achat valide");
            throw new SaisieException();
        } else if (typeAchat == 1) {
            GetClient();
            AchatSansOrdonnance achatSansOrdonnance = new AchatSansOrdonnance(GetClient(), Generator.DateNow(), listeMedicament, PrixTotal());
            GestionListe.addAchatSansOrdonnance(achatSansOrdonnance);
        } else if (typeAchat == 2) {
            GetClient();
            GetMedecin();
            Ordonnance ordonnance = new Ordonnance(Generator.DateNow(), listeMedicament, GetClient(), GetMedecin(), PrixTotal());
            GestionListe.addOrdonnance(ordonnance);
        }

        model.clear();
        Fenetre.Fenetre("Achat effectué");
        annuler();
        PrixTotalLabel();
    }

    //Annuler l'achat
    private void annuler() {
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
        // Vide la liste de médicaments
        ListeMedicamentTableModel model = (ListeMedicamentTableModel) listeDeMedocTable.getModel();
        model.clear();

        actualiserComboClient();
        actualiserComboMedecin();
        actualiserComboMedicament();
    }

    //Actualiser les combobox


    private Client GetClient() throws SaisieException {
        Object selectedClient = clientCombobox.getSelectedItem();
        if (!(selectedClient instanceof Client)) {
            String[] clientSplit = ((String) selectedClient).split("\\s+", 2); // Limiter à deux parties
            if (clientSplit.length != 2) {
                Fenetre.Fenetre("Le nom et prénom du client doivent être séparés par un espace");
                throw new SaisieException();
            }

            String clientNom = clientSplit[0].trim();
            String clientPrenom = clientSplit[1].trim();

            Client client = new Client(clientNom, clientPrenom);
            addClient(client);
            clientCombobox.setSelectedItem(client);
        }
        return (Client) clientCombobox.getSelectedItem();

    }

    private Medecin GetMedecin() throws SaisieException {
        Object selectedMedecin = medecinCombobox.getSelectedItem();

        // Si aucun médecin n'est sélectionné ou saisi
        if (selectedMedecin == null || selectedMedecin.toString().trim().isEmpty()) {
            Fenetre.Fenetre("Veuillez sélectionner ou saisir un médecin valide");
            throw new SaisieException("Médecin non sélectionné ou saisi.");
        }

        // Vérifier si l'élément est déjà un objet Medecin
        if (selectedMedecin instanceof Medecin) {
            return (Medecin) selectedMedecin; // Retourner le médecin sélectionné
        } else {
            // Si c'est une chaîne de caractères, essayer de créer un nouveau médecin
            String medecinText = selectedMedecin.toString().trim();
            String[] medecinSplit = medecinText.split("\\s+", 2); // Diviser en nom et prénom

            if (medecinSplit.length != 2) {
                Fenetre.Fenetre("Le nom et prénom du médecin doivent être séparés par un espace.");
                throw new SaisieException("Format de saisie incorrect.");
            }

            String medecinNom = medecinSplit[0].trim();
            String medecinPrenom = medecinSplit[1].trim();

            // Créer un nouveau médecin
            Medecin nouveauMedecin = new Medecin(medecinNom, medecinPrenom);

            // Ajouter le nouveau médecin à la liste et actualiser le ComboBox
            addMedecin(nouveauMedecin);

            // Sélectionner le nouveau médecin dans le ComboBox
            medecinCombobox.setSelectedItem(nouveauMedecin);

            return nouveauMedecin;
        }
    }

    public ListeMedicamentTableModel getTableModel() {
        return (ListeMedicamentTableModel) listeDeMedocTable.getModel();
    }

    public void PrixTotalLabel() {
        double prixTotal = 0.0;
        for (TableMedicamentTemporaire prix : getTableMedicamentTemporaire()) {
            String prixStr = prix.getPrix();
            int qantity = prix.getQuantite();
            if (prixStr != null && !prixStr.trim().isEmpty()) {
                prixTotal += Double.parseDouble(prixStr) * qantity;
            }
        }
        prixLabel.setText("Prix total : " + prixTotal);
    }

    public double PrixTotal() {
        double prixTotal = 0.0;
        for (TableMedicamentTemporaire prix : getTableMedicamentTemporaire()) {
            String prixStr = prix.getPrix();
            int qantity = prix.getQuantite();
            if (prixStr != null && !prixStr.trim().isEmpty()) {
                prixTotal += Double.parseDouble(prixStr) * qantity;
            }
        }
        return prixTotal;

    }

    private void actualiserComboClient() {
        DefaultComboBoxModel<Client> comboBoxModel1 = new DefaultComboBoxModel<>();
        comboBoxModel1.removeAllElements();
        for (Client client : GestionListe.getClient()) {
            comboBoxModel1.addElement(client);
        }
        clientCombobox.setModel(comboBoxModel1);
    }

    private void actualiserComboMedecin() {
        DefaultComboBoxModel<Medecin> comboBoxModel2 = new DefaultComboBoxModel<>();
        comboBoxModel2.removeAllElements();
        for (Medecin medecin : GestionListe.getMedecin()) {
            comboBoxModel2.addElement(medecin);
        }
        medecinCombobox.setModel(comboBoxModel2);
    }

    private void actualiserComboMedicament() {
        DefaultComboBoxModel<Medicament> comboBoxModel3 = new DefaultComboBoxModel<>();
        comboBoxModel3.removeAllElements();
        for (Medicament medicament : getMedicament()) {
            comboBoxModel3.addElement(medicament);
        }
        medicamentCombobox.setModel(comboBoxModel3);
    }



}