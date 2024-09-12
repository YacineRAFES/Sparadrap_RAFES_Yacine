package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Controller.ControllerDetailAchat;
import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ListeMedicamentDetailAchat extends AbstractTableModel {
    private static String[][] data;
    private final String[] columnNames = {"Nom du Médicament", "Quantité", "Prix"};
    // Ajouter un tableau pour stocker les prix unitaires
    private final double[] prixUnitaires;


    public ListeMedicamentDetailAchat(String[][] data){
        this.data = data;

        // Utiliser un format décimal qui supporte les virgules comme séparateur
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);

        prixUnitaires = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            try {
                // Supposons que la colonne 2 contient le prix initial pour la quantité de 1
                Number prix = format.parse(data[i][2]); // Parse le prix en utilisant la convention locale (virgule)
                int quantite = Integer.parseInt(data[i][1]);
                prixUnitaires[i] = prix.doubleValue() / quantite;
            } catch (ParseException | NumberFormatException e) {
                Fenetre.Fenetre("Erreur lors de la conversion du prix ou de la quantité à la ligne " + (i+1));
            }
        }
    }

    //Récupére la longueur de la ligne
    @Override
    public int getRowCount() {
        return data.length;
    }

    //Récupérer la longueur de la colonne
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    //Récupérer la valeur de la cellule
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    // Seule la quantité est modifiable
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    //Récupérer le nom de la colonne
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    //Saisir la nouvelle quantité
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); // Utiliser pour formater les nombres avec virgules
            DecimalFormat decimalFormat = (DecimalFormat) format;
            decimalFormat.applyPattern("#,##0.00");

            boolean autoriserModif = Fenetre.Confirmation("Voulez-vous vraiment modifier la quantité ?");
            if(!autoriserModif){
                return;
            }

            if (columnIndex == 1) { // Quantité modifiée
                int nouvelleQuantite = Verification.Quantite((String) aValue);
                data[rowIndex][columnIndex] = String.valueOf(nouvelleQuantite);

                // Mettre à jour le prix en fonction de la nouvelle quantité
                double nouveauPrix = prixUnitaires[rowIndex] * nouvelleQuantite;
                data[rowIndex][2] = decimalFormat.format(nouveauPrix); // Formater avec virgule

                // Notifie que les cellules Quantité et Prix ont été mises à jour
                fireTableCellUpdated(rowIndex, 1); // Quantité
                fireTableCellUpdated(rowIndex, 2); // Prix
            }
        } catch (SaisieException e) {
            Fenetre.Fenetre("Erreur lors de la saisie");
            new SaisieException("Erreur lors de la saisie");
        }
    }

    // Calculer le prix total
    public static double getPrixTotal(){
        double prixTotal = 0;
        for (String[] ligne : data) {
            try {
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                Number prix = format.parse(ligne[2]);
                prixTotal += prix.doubleValue();
            } catch (ParseException e) {
                Fenetre.Fenetre("Erreur lors de la conversion du prix");
            }
        }
        BigDecimal bd = new BigDecimal(prixTotal);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
