package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.DAO.MutuelleDAO;
import fr.afpa.dev.pompey.Modele.Mutuelle;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeMutuelleTable extends AbstractTableModel {

    private final String[] ENTETE = new String[] {
            "Nom", "Adresse", "Ville", "Code Postal", "Détails"
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
                return mutuelle.getNom() != null ? mutuelle.getNom() : "Non renseigné";
            case 1:
                return mutuelle.getAdresses() != null ? mutuelle.getAdresses() : "Non renseigné";
            case 2:
                return mutuelle.getAdresses().getVille() != null ? mutuelle.getAdresses().getVille() : "Non renseigné";
            case 3:
                return mutuelle.getAdresses().getCodePostal() != 0 ? mutuelle.getAdresses().getCodePostal() : "Non renseigné";
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
