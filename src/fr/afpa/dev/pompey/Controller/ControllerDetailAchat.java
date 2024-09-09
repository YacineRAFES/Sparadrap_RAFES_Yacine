package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.GestionListe;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

public class ControllerDetailAchat extends JFrame {
    private JPanel contentPane;
    private JLabel titreLabel;
    private JTextField nomTextField;
    private JTextField prenomTextField;
    private JTextField medecinTextField;
    private JTable listeDeMedicamentTable;
    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel medecinLabel;
    private JLabel ListedeMedicamentLabel;
    private JScrollPane scrollPane;
    private JButton annulerButton;
    private JButton validerButton;
    private JLabel typeAchatLabel;
    private JLabel dateAchatLabel;

    public ControllerDetailAchat(int idAchat) throws SaisieException {
        setTitle("Détail d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // Positionnement de la fenêtre
        this.setLocationRelativeTo(null);

        // Vérifiez si idAchat est dans les limites de la liste AchatSansOrdonnance
        if (idAchat < GestionListe.getAchatSansOrdonnance().size()) {
            typeAchatLabel.setText("Achat sans ordonnance");
            nomTextField.setText(GestionListe.getAchatSansOrdonnance().get(idAchat).getClient().getNom());
            prenomTextField.setText(GestionListe.getAchatSansOrdonnance().get(idAchat).getClient().getPrenom());
            dateAchatLabel.setText("Date de l'achat : " + GestionListe.getAchatSansOrdonnance().get(idAchat).getDate().toString());

            // Récupérer et afficher les médicaments dans le tableau
            Map<String, Integer> listeMedicament = GestionListe.getAchatSansOrdonnance().get(idAchat).getListeMedicament();
            remplirTableMedicament(listeMedicament);
        }
        // Vérifiez s'il est dans la liste des Ordonnances
        else if (idAchat - GestionListe.getAchatSansOrdonnance().size() < GestionListe.getOrdonnance().size()) {
            int ordonnanceIndex = idAchat - GestionListe.getAchatSansOrdonnance().size();
            typeAchatLabel.setText("Achat avec ordonnance");
            nomTextField.setText(GestionListe.getOrdonnance().get(ordonnanceIndex).getClient().getNom());
            prenomTextField.setText(GestionListe.getOrdonnance().get(ordonnanceIndex).getClient().getPrenom());
            medecinTextField.setText(GestionListe.getOrdonnance().get(ordonnanceIndex).getMedecin().getNom() + " " + GestionListe.getOrdonnance().get(ordonnanceIndex).getMedecin().getPrenom());
            dateAchatLabel.setText("Date de l'achat : " + GestionListe.getOrdonnance().get(ordonnanceIndex).getDate().toString());

            // Récupérer et afficher les médicaments dans le tableau
            Map<String, Integer> listeMedicament = GestionListe.getOrdonnance().get(ordonnanceIndex).getListeMedicament();
            remplirTableMedicament(listeMedicament);
        } else {
            // Si idAchat dépasse les deux listes, il y a un problème
            Fenetre.Fenetre("Erreur : Achat introuvable");
            throw new SaisieException("Erreur lors de la récupération de l'achat");
        }
    }

    /**
     * Méthode pour remplir le tableau des médicaments avec les données
     */
    private void remplirTableMedicament(Map<String, Integer> medicamentList) {
        // Créer un tableau à deux dimensions pour contenir les données des médicaments
        String[][] data = new String[medicamentList.length][1];
        for (int i = 0; i < medicamentList.length; i++) {
            data[i][0] = medicamentList[i]; // Chaque ligne contient un médicament
        }

        // Colonnes pour la table
        String[] columnNames = {"Nom du Médicament"};

        // Créer un modèle de table avec les données et les colonnes
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            // Pour rendre les cellules non modifiables
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Appliquer ce modèle à la JTable
        listeDeMedicamentTable.setModel(tableModel);
    }
}
