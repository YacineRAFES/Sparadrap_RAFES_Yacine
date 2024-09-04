package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.Medicament;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeMedicamentTableModel extends AbstractTableModel {

    private final String[] ENTETE = new String[] {
            "Nom", "Action"
    };

    private final List<Medicament> medicament;

    public ListeMedicamentTableModel(List<Medicament> medicament) {
        this.medicament = medicament;
    }

    public void addMedicament(Medicament medicament) {
        this.medicament.add(medicament);
        fireTableRowsInserted(this.medicament.size() - 1, this.medicament.size() - 1);
    }

    @Override
    public String getColumnName(int column) {
        return ENTETE[column];
    }

    @Override
    public int getRowCount() {
        return medicament.size();
    }

    @Override
    public int getColumnCount() {
        return ENTETE.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Medicament medoc = medicament.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return medoc.getNom();
            case 1:
                return "Supprimer";
            default:
                return null;
        }
    }
    public boolean isCellEditable(int row, int column) {
        return true;
    }
}
