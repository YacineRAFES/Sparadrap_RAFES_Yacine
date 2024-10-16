package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.TableMedicamentTemporaire;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Utilitaires.Verification;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeMedicamentTableModel extends AbstractTableModel {

    private final String[] ENTETE = new String[] {
            "Nom", "Quantite", "Prix", "Action"
    };

    private final List<TableMedicamentTemporaire> tableMedicamentTemporaire;

    public ListeMedicamentTableModel(List<TableMedicamentTemporaire> tableMedicamentTemporaire) {
        this.tableMedicamentTemporaire = tableMedicamentTemporaire;
    }

    public void clear() {
        tableMedicamentTemporaire.clear();
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return ENTETE[column];
    }

    @Override
    public int getRowCount() {
        return tableMedicamentTemporaire.size();
    }

    @Override
    public int getColumnCount() {
        return ENTETE.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TableMedicamentTemporaire table = tableMedicamentTemporaire.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return table.getNom();
            case 1:
                return table.getQuantite();
            case 2:
                return table.getPrix();
            case 3:
                return "Supprimer";
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int column) {
        if(column == 1 || column == 3){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if (rowIndex >= 0 && rowIndex < tableMedicamentTemporaire.size()) {
            TableMedicamentTemporaire table = tableMedicamentTemporaire.get(rowIndex);
            try {
                if (0 == columnIndex) {
                    table.setNom(Verification.String((String) aValue));
                } else if (1 == columnIndex) {
                    table.setQuantite(Verification.Quantite((String) aValue));
                    fireTableCellUpdated(rowIndex, columnIndex);
                    fireTableDataChanged();
                } else if (2 == columnIndex) {
                    table.setPrix(Verification.Prix((String) aValue));
                }
            } catch (SaisieException e) {
                new SaisieException();
            }
        }
    }

    public List<TableMedicamentTemporaire> getMedicamentList() {
        return tableMedicamentTemporaire;
    }
}
