package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentDetailAchat;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ControllerDetailAchat extends JFrame {
    private String[][] oldData;
    private JPanel contentPane;
    private JLabel titreLabel;
    private JTable listeDeMedicamentTable;
    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel medecinLabel;
    private JLabel ListedeMedicamentLabel;
    private JScrollPane scrollPane;
    private JButton validerButton;
    private JLabel typeAchatLabel;
    private JLabel dateAchatLabel;
    private JLabel prixTotalLabel;

    public ControllerDetailAchat(int idAchat) throws SaisieException {


        setTitle("Détail d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // Positionnement de la fenêtre
        this.setLocationRelativeTo(null);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //ne rien faire pour empêcher la fermeture de la fenêtre

            }
        });

        validerButton.addActionListener(e -> {
            this.dispose();
        });

        // Vérifiez si idAchat est dans la liste des AchatSansOrdonnance
        if (idAchat < GestionListe.getAchatSansOrdonnance().size()) {
            typeAchatLabel.setText("Achat sans ordonnance");
            nomLabel.setText(GestionListe.getAchatSansOrdonnance().get(idAchat).getClient().getNom());
            prenomLabel.setText(GestionListe.getAchatSansOrdonnance().get(idAchat).getClient().getPrenom());
            medecinLabel.setText("");
            prixTotalLabel.setText("Prix total : " + GestionListe.getAchatSansOrdonnance().get(idAchat).getPrixTotal() + " €");

            // Récupérer et afficher les médicaments dans le tableau
            String[][] listeMedicament = GestionListe.getAchatSansOrdonnance().get(idAchat).getListeMedicament();
            remplirTableMedicament(listeMedicament);
        }
        // Vérifiez si idAchat est dans la liste des Ordonnances
        else if (idAchat - GestionListe.getAchatSansOrdonnance().size() < GestionListe.getOrdonnance().size()) {
            int ordonnanceIndex = idAchat - GestionListe.getAchatSansOrdonnance().size();
            typeAchatLabel.setText("Achat avec ordonnance");
            nomLabel.setText("Nom : "+GestionListe.getOrdonnance().get(ordonnanceIndex).getClient().getNom());
            prenomLabel.setText("Prénom : "+GestionListe.getOrdonnance().get(ordonnanceIndex).getClient().getPrenom());
            medecinLabel.setText("Medecin : "+GestionListe.getOrdonnance().get(ordonnanceIndex).getMedecin().getNom() + " " + GestionListe.getOrdonnance().get(ordonnanceIndex).getMedecin().getPrenom());
            dateAchatLabel.setText("Date de l'achat : " + GestionListe.getOrdonnance().get(ordonnanceIndex).getDate().toString());
            prixTotalLabel.setText("Prix total : " + GestionListe.getOrdonnance().get(ordonnanceIndex).getPrixTotal() + " €");
            // Récupérer et afficher les médicaments dans le tableau
            String[][] listeMedicament = GestionListe.getOrdonnance().get(ordonnanceIndex).getListeMedicament();
            remplirTableMedicament(listeMedicament);
        } else {
            // Si idAchat dépasse les deux listes, il y a un problème
            Fenetre.Fenetre("Erreur : Achat introuvable");
            throw new SaisieException("Erreur lors de la récupération de l'achat");
        }
    }

    private void remplirTableMedicament(String[][] medicamentList) {
        ListeMedicamentDetailAchat tableModel = new ListeMedicamentDetailAchat(medicamentList);
        listeDeMedicamentTable.setModel(tableModel);

        tableModel.addTableModelListener(e -> {
            // Mettre à jour le prix total
            prixTotalLabel.setText("Prix total : " + ListeMedicamentDetailAchat.getPrixTotal() + " €");
        });
    }
}
