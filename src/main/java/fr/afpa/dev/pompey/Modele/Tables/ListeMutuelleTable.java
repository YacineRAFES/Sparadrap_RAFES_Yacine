package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.DAO.MutuelleDAO;
import fr.afpa.dev.pompey.Modele.Mutuelle;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeMutuelleTable extends AbstractTableModel {

    private final String[] ENTETE = new String[] {
            "ID", "Nom", "Ville", "Code Postal", "Détails"
    };

    private List<Mutuelle> mutuelles;

    public ListeMutuelleTable(List<Mutuelle> mutuelles) {
        this.mutuelles = mutuelles;
    }

    public String getColumnName(int column) {
        return ENTETE[column];
    }

    public int getRowCount() {
        return mutuelles.size();
    }

    public int getColumnCount() {
        return ENTETE.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Mutuelle mutuelle = mutuelles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return mutuelle.getId();
            case 1:
                return mutuelle.getNom() != null ? mutuelle.getNom() : "Non renseigné";
            case 2:
                if (mutuelle.getAdresses() != null && mutuelle.getAdresses().getVille() != null) {
                    return mutuelle.getAdresses().getVille().getNom();
                } else {
                    return "Non renseigné";
                }
            case 3:
                if (mutuelle.getAdresses() != null && mutuelle.getAdresses().getVille() != null) {
                    return mutuelle.getAdresses().getVille().getCp();
                } else {
                    return "Non renseigné";
                }
            case 4:
                //boutton détail avec id mutuelle
                return "Détails";
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        if(col == 4) {
            return true;
        } else {
            return false;
        }
    }


}
