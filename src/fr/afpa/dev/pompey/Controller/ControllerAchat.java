package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentTableModel;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.button;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ControllerAchat extends JFrame {
    private JPanel contentPane;

    private JButton annulerButton;
    private JButton validerButton;
    private JComboBox clientCombobox;
    private JButton creerUnClientButton;
    private JButton creerUnMédecinButton;
    private JButton creerUnMédicamentButton;
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

    public ControllerAchat() {
        //Création des constructeurs pour le test sur l'application
//        Mutuelle mutuelleTest = new Mutuelle("Mutuelle X");
//        Medecin medecinTest = new Medecin("Doctor", "House");
//        Medicament medicamentTest = new Medicament("antibactériens");
//        Client clientTest = new Client(
//                "Dupont",
//                "Jean",
//                "123 rue de la Paix",
//                "75001",
//                "Paris",
//                "0123456789",
//                "jean.dupont@example.com",
//                "123456789012345",
//                "01/01/1980",
//                mutuelleTest,
//                medecinTest
//        );
//
//        addClient(clientTest);
//        addMedecin(medecinTest);
//        addMutuelle(mutuelleTest);
//        addMedicament(medicamentTest);

        setTitle("Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Fonctionnalité qui permet d'afficher le placeholder dans un combobox
        AjouterPlaceholderComboboxNonEditable(typeAchatCombobox, "Type d'achat");
        AjouterPlaceholderComboboxEditable(clientCombobox, "Selectionner un Client");
        AjouterPlaceholderComboboxEditable(medecinCombobox, "Selectionner un Médecin");
        AjouterPlaceholderComboboxEditable(medicamentCombobox, "Selectionner un Medicament");



        DefaultComboBoxModel<Client> comboBoxModel1 = (DefaultComboBoxModel<Client>) clientCombobox.getModel();
        DefaultComboBoxModel<Medecin> comboBoxModel2 = (DefaultComboBoxModel<Medecin>) medecinCombobox.getModel();
        DefaultComboBoxModel<Medicament> comboBoxModel3 = (DefaultComboBoxModel<Medicament>) medicamentCombobox.getModel();

        DefaultComboBoxModel<String> typeAchatModel = new DefaultComboBoxModel<>();
        typeAchatModel.addElement("Achat direct");
        typeAchatModel.addElement("Via ordonnance");
        typeAchatCombobox.setModel(typeAchatModel);

        ListeMedicamentTableModel model1 = new ListeMedicamentTableModel(GestionListe.getMedicament());
        listeDeMedocTable.setModel(model1);
        listeDeMedocTable.getTableHeader().setResizingAllowed(false);

        for(Client client : GestionListe.getClient()){
            comboBoxModel1.addElement(client);
        }
        for(Medecin medecin : GestionListe.getMedecin()){
            comboBoxModel2.addElement(medecin);
        }
        for(Medicament medicament : GestionListe.getMedicament()){
            comboBoxModel3.addElement(medicament);
        }

        listeDeMedocTable.getColumn("Action").setCellRenderer(new button.ButtonRenderer());
        listeDeMedocTable.getColumn("Action").setCellEditor(new button.ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = listeDeMedocTable.getSelectedRow();
                Medicament medicament = GestionListe.getMedicament().get(row);

                GestionListe.removeMedicament(medicament);
                Refresh(listeDeMedocTable);
                Fenetre.Fenetre("test");
            }
        }));

        // Les listeners
        creerUnClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client();
            }
        });
        creerUnMédecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medecin();
            }
        });
        creerUnMédicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicament();
            }
        });
        ajouterUnMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterUnMedicament();
            }
        });
    }

    // les actions
    private void client() {
        ControllerClient clientController = new ControllerClient();
        clientController.setVisible(true);
        clientController.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                actualiserLesCombobox();
            }
        });
    }

    private void medecin() {
        ControllerMedecin medecin = new ControllerMedecin();
        medecin.setVisible(true);
    }

    private void medicament() {
        ControllerMedicament medicament = new ControllerMedicament();
        medicament.setVisible(true);
    }

    private void ajouterUnMedicament() {

        Medicament medicament = new Medicament((String) medicamentCombobox.getSelectedItem());

        ListeMedicamentTableModel newModel = new ListeMedicamentTableModel(GestionListe.getMedicament());
        listeDeMedocTable.setModel(newModel);
        listeDeMedocTable.getTableHeader().setResizingAllowed(false);

        ListeMedicamentTableModel tableMedoc = (ListeMedicamentTableModel) listeDeMedocTable.getModel();
        tableMedoc.addMedicament(medicament);

        Refresh(listeDeMedocTable);
    }



    //Actualiser les combobox
    private void actualiserLesCombobox() {

        DefaultComboBoxModel<Client> comboBoxModel1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Medecin> comboBoxModel2 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Medicament> comboBoxModel3 = new DefaultComboBoxModel<>();

        for (Client client : GestionListe.getClient()) {
            comboBoxModel1.addElement(client);
        }
        for(Medecin medecin : GestionListe.getMedecin()){
            comboBoxModel2.addElement(medecin);
        }
        for(Medicament medicament : GestionListe.getMedicament()){
            comboBoxModel3.addElement(medicament);
        }
        clientCombobox.setModel(comboBoxModel1);
        medecinCombobox.setModel(comboBoxModel2);
        medicamentCombobox.setModel(comboBoxModel3);

    }



}
