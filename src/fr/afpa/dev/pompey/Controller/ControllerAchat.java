package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.GestionListe;
import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.AjouterPlaceholderCombobox;

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

    public ControllerAchat() {
        setTitle("Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        //Fonctionnalité qui permet d'afficher le placeholder dans un combobox
        AjouterPlaceholderCombobox(typeAchatCombobox, "Type d'achat");
        AjouterPlaceholderCombobox(clientCombobox, "Selectionner un Client");
        AjouterPlaceholderCombobox(medecinCombobox, "Selectionner un Médecin");
        AjouterPlaceholderCombobox(medicamentCombobox, "Selectionner un Medicament");


        DefaultComboBoxModel<Client> comboBoxModel1 = (DefaultComboBoxModel<Client>) clientCombobox.getModel();

        for(Client client : GestionListe.getClient()){
            comboBoxModel1.addElement(client);
        }

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
        Medicament medicament = new Medicament();
        medicament.setVisible(true);
    }

    //Actualiser les combobox
    private void actualiserLesCombobox() {
        DefaultComboBoxModel<Client> model = new DefaultComboBoxModel<>();
        for (Client client : GestionListe.getClient()) {
            model.addElement(client);
        }
        clientCombobox.setModel(model);
    }


}
