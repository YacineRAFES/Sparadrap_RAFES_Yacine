package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.Medecin;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeMedecinTable extends AbstractTableModel {
    private final String[] ENTETE = new String[] {
            "Nom", "Prenom", "Détail", "Action"
    };

    private final List<Medecin> medecins;

    public ListeMedecinTable(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    public String getColumnName(int column) {
        return ENTETE[column];
    }

    public int getRowCount() {
        return medecins.size();
    }

    public int getColumnCount() {
        return ENTETE.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Medecin medecin = medecins.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return medecin.getNomMedecin();
            case 1:
                return medecin.getPrenomMedecin();
            case 2:
                return "Détail";
            case 3:
                return "Supprimer";
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        if(col == 2 || col == 3) {
            return true;
        } else {
            return false;
        }
    }

}
