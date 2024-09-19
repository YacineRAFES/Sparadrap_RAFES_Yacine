package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Ordonnance;
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

    private Ordonnance ordonnance;

    public ControllerDetailAchat(int idAchat) throws SaisieException {
        setTitle("Détail d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // Positionnement de la fenêtre
        this.setLocationRelativeTo(null);

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
            dateAchatLabel.setText("Date de l'achat : " + GestionListe.getAchatSansOrdonnance().get(idAchat).getDate().toString());

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
            //Si le client a une mutuelle, on réduit le prix total sinon on affiche le prix total
            if (GestionListe.getOrdonnance().get(ordonnanceIndex).getClient().getMutuelle() == null) {
                prixTotalLabel.setText("Prix total : " + GestionListe.getOrdonnance().get(ordonnanceIndex).getPrixTotal() + " €");
            } else {
                double prixTotal = GestionListe.getOrdonnance().get(ordonnanceIndex).getPrixTotal();
                double tauxDePriseEnCharge = Double.parseDouble(GestionListe.getOrdonnance().get(ordonnanceIndex).getClient().getMutuelle().getTauxDePriseEnCharge()) / 100;
                double prixApresMutuelle = prixTotal * (1 - tauxDePriseEnCharge);
                prixTotalLabel.setText(String.format(
                        "Prix total : %.2f €, Après la mutuelle : %.2f €",
                        prixTotal,
                        prixApresMutuelle
                ));
            }
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

    public ControllerDetailAchat(Ordonnance ordonnance) throws SaisieException{
        this.ordonnance = ordonnance;
        setTitle("Détail d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // Positionnement de la fenêtre
        this.setLocationRelativeTo(null);

        validerButton.addActionListener(e -> {
            this.dispose();
        });

        if(ordonnance != null){
            typeAchatLabel.setText("Achat avec ordonnance");
            nomLabel.setText("Nom : "+ordonnance.getClient().getNom());
            prenomLabel.setText("Prénom : "+ordonnance.getClient().getPrenom());
            medecinLabel.setText("Medecin : "+ordonnance.getMedecin().getNom() + " " + ordonnance.getMedecin().getPrenom());
            dateAchatLabel.setText("Date de l'achat : " + ordonnance.getDate().toString());
            prixTotalLabel.setText("Prix total : " + ordonnance.getPrixTotal() + " €");
            if (ordonnance.getClient().getMutuelle() == null) {
                prixTotalLabel.setText("Prix total : " +ordonnance.getPrixTotal() + " €");
            } else {
                double prixTotal = ordonnance.getPrixTotal();
                double tauxDePriseEnCharge = Double.parseDouble(ordonnance.getClient().getMutuelle().getTauxDePriseEnCharge()) / 100;
                double prixApresMutuelle = prixTotal * (1 - tauxDePriseEnCharge);
                prixTotalLabel.setText(String.format(
                        "Prix total : %.2f €, Après la mutuelle : %.2f €",
                        prixTotal,
                        prixApresMutuelle
                ));
            }
            // Récupérer et afficher les médicaments dans le tableau
            String[][] listeMedicament = ordonnance.getListeMedicament();
            remplirTableMedicament(listeMedicament);
        } else {
            // Si idAchat dépasse les deux listes, il y a un problème
            Fenetre.Fenetre("Erreur : Achat introuvable");
            throw new SaisieException("Erreur lors de la récupération de l'achat");
        }
    }
}
