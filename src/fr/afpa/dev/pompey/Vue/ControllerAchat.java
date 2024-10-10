package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;
import fr.afpa.dev.pompey.Utilitaires.*;

import static fr.afpa.dev.pompey.Modele.GestionListe.*;
import static fr.afpa.dev.pompey.Utilitaires.InterfaceModel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;


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

        // Ajout des éléments avec ID dans le DefaultComboBoxModel
        DefaultComboBoxModel<TypeAchat> typeAchatModel = new DefaultComboBoxModel<>();
        typeAchatModel.addElement(new TypeAchat(0, "Type d'achat"));
        typeAchatModel.addElement(new TypeAchat(1, "Achat direct"));
        typeAchatModel.addElement(new TypeAchat(2, "Via ordonnance"));
        typeAchatCombobox.setModel(typeAchatModel);


        ListeMedicamentTableModel model1 = new ListeMedicamentTableModel(GestionListe.getTableMedicamentTemporaire());
        this.listeDeMedocTable.setModel(model1);
        this.listeDeMedocTable.getTableHeader().setResizingAllowed(false);

        //On écoute les changements dans la table si on ajoute ou supprime un médicament, on recalcule le prix total
        model1.addTableModelListener(e -> {
            try {
                PrixTotalLabel();
            } catch (SaisieException ex) {
                throw new RuntimeException(ex);
            }
        });

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
                int row = listeDeMedocTable.getEditingRow(); // Get the row being edited (clicked)
                if (row >= 0) { // Ensure the row index is valid
                    TableMedicamentTemporaire temp = GestionListe.getTableMedicamentTemporaire().get(row);
                    GestionListe.removeTableMedicamentTemporaire(temp);
                    Refresh(listeDeMedocTable);
                    try {
                        PrixTotalLabel();
                    } catch (SaisieException ex) {
                        throw new RuntimeException(ex);
                    }
                    ShowLabelWithTimer(informationLabel, "Médicament supprimé de la liste", Color.RED);
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
                    PrixTotalLabel();
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
                try {
                    PrixTotalLabel();
                } catch (SaisieException ex) {
                    throw new RuntimeException(ex);
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

    // Créer un médicament
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

    // Ajouter un médicament dans la liste
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
            ShowLabelWithTimer(informationLabel, "Veuillez sélectionner ou ajouter un médicament", Color.RED);
            throw new SaisieException();
        }

        String nomMedicament = selectedMedicament.getNom();
        Medicament medicament = new Medicament(nomMedicament);
        TableMedicamentTemporaire tableMedicamentTemporaire = new TableMedicamentTemporaire(selectedMedicament, 0, selectedMedicament.getPrix());


        // Vérifier si le médicament est déjà dans la table temporaire
        for (TableMedicamentTemporaire uniqueMedicamentTemp : getTableMedicamentTemporaire()) {
            if (uniqueMedicamentTemp.getNom().equals(medicament.getNom())) {
                ShowLabelWithTimer(informationLabel, "Ce médicament est déjà ajouté", Color.ORANGE);
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

            double prixMedoc;
            if(medicamentList.get(i).getPrix() == null || medicamentList.get(i).getPrix().trim().isEmpty()){
                prixMedoc = 0;
            }else{
                prixMedoc = Double.parseDouble(medicamentList.get(i).getPrix());
            }

            if(medicamentList.get(i).getQuantite() == 0){
                ShowLabelWithBlinker(informationLabel, "La quantité de médicament ne doit pas être à 0", Color.RED);
                throw new SaisieException();
            }
            listeMedicament[i][2] = String.valueOf(medicamentList.get(i).getQuantite() * prixMedoc);
        }
        if (typeAchat == 0) {
            ShowLabelWithBlinker(informationLabel, "Veuillez sélectionner un type d'achat valide", Color.RED);
            setComboBoxColor(typeAchatCombobox, Color.RED);
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

        // Vider la table temporaire
        model.clear();
        // Afficher un message de confirmation
        ShowLabelWithTimer(informationLabel, "Achat effectué", Color.GREEN);
        // Actualiser les saisies
        annuler();
        // Rafraîchir le prix total
        PrixTotalLabel();
    }

    //Annuler l'achat
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

        // Vide la liste de médicaments
        ListeMedicamentTableModel model = (ListeMedicamentTableModel) listeDeMedocTable.getModel();
        model.clear();

        actualiserComboClient();
        actualiserComboMedecin();
        actualiserComboMedicament();
    }

    // Récupérer le client
    private Client GetClient() throws SaisieException {
        Object selectedClient = clientCombobox.getSelectedItem();
        if (!(selectedClient instanceof Client)) {
            String[] clientSplit = ((String) selectedClient).split("\\s+", 2);// Limiter à deux parties
            if (clientSplit.length != 2) {
                ShowLabelWithBlinker(informationLabel, "Le nom/prénom du client doivent être séparés par un espace", Color.RED);
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

    // Récupérer le médecin
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

    // Récupérer la table temporaire
    public ListeMedicamentTableModel getTableModel() {
        return (ListeMedicamentTableModel) listeDeMedocTable.getModel();
    }

    // Calculer le prix total
    public void PrixTotalLabel() throws SaisieException {
        double prixTotal = 0.0;
        for (TableMedicamentTemporaire prix : getTableMedicamentTemporaire()) {
            String prixStr = prix.getPrix();
            int qantity = prix.getQuantite();
            if (prixStr != null && !prixStr.trim().isEmpty()) {
                prixStr = prixStr.replace(",", ".");
                prixTotal += Double.parseDouble(prixStr) * qantity;
            }
        }

        //si le client a une mutuelle on applique la réduction sinon on affiche le prix total
        int typeAchat = typeAchatCombobox.getSelectedIndex();
        if(clientCombobox.getSelectedItem() instanceof Client){
            Client client = (Client) clientCombobox.getSelectedItem();
            if(client.getMutuelle() != null){
                if(typeAchat == 2) {
                    BigDecimal total = new BigDecimal(prixTotal).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal apresMutuelle = total.multiply(BigDecimal.valueOf(1 - (Double.parseDouble(client.getMutuelle().getTauxDePriseEnCharge()) / 100)))
                            .setScale(2, RoundingMode.HALF_UP);

                    prixLabel.setText("Prix total : " + total + " €, Après la mutuelle : " + apresMutuelle + " €");
                }else{
                    BigDecimal total = new BigDecimal(prixTotal).setScale(2, RoundingMode.HALF_UP);
                    prixLabel.setText("Prix total : " + total + " €");
                }
            }else{
                BigDecimal total = new BigDecimal(prixTotal).setScale(2, RoundingMode.HALF_UP);
                prixLabel.setText("Prix total : " + total + " €");
            }
        }else{
            BigDecimal total = new BigDecimal(prixTotal).setScale(2, RoundingMode.HALF_UP);
            prixLabel.setText("Prix total : " + total + " €");
        }
    }

    // Calculer le prix total
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

    // Actualiser la combobox des clients
    private void actualiserComboClient() {
        DefaultComboBoxModel<Client> comboBoxModel1 = new DefaultComboBoxModel<>();
        comboBoxModel1.removeAllElements();
        for (Client client : GestionListe.getClient()) {
            comboBoxModel1.addElement(client);
        }
        clientCombobox.setModel(comboBoxModel1);
    }

    // Actualiser la combobox des médecins
    private void actualiserComboMedecin() {
        DefaultComboBoxModel<Medecin> comboBoxModel2 = new DefaultComboBoxModel<>();
        comboBoxModel2.removeAllElements();
        for (Medecin medecin : GestionListe.getMedecin()) {
            comboBoxModel2.addElement(medecin);
        }
        medecinCombobox.setModel(comboBoxModel2);
    }

    // Actualiser la combobox des médicaments
    private void actualiserComboMedicament() {
        DefaultComboBoxModel<Medicament> comboBoxModel3 = new DefaultComboBoxModel<>();
        comboBoxModel3.removeAllElements();
        for (Medicament medicament : getMedicament()) {
            comboBoxModel3.addElement(medicament);
        }
        medicamentCombobox.setModel(comboBoxModel3);
    }



}