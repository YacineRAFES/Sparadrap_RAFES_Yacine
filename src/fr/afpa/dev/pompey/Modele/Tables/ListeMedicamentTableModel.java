package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Modele.TableMedicamentTemporaire;
import fr.afpa.dev.pompey.Modele.Utilitaires.Fenetre;
import fr.afpa.dev.pompey.Modele.Utilitaires.Verification;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.stream.Collectors;

public class ListeMedicamentTableModel extends AbstractTableModel {

    private final String[] ENTETE = new String[] {
            "Nom", "Quantite", "Prix", "Actions"
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
        if (column == 1 || column == 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        TableMedicamentTemporaire table = tableMedicamentTemporaire.get(rowIndex);
        try{
            if(0 == columnIndex) {
                table.setNom(Verification.String((String) aValue));
            }
            else if(1 == columnIndex) {
                table.setQuantite(Verification.Quantite((String) aValue));
            }
        }catch (SaisieException e){
            Fenetre.Fenetre("Une erreur est survenu");
        }

    }

    public List<TableMedicamentTemporaire> getMedicamentList() {
        return tableMedicamentTemporaire;
    }
}
