// src/fr/afpa/dev/pompey/Modele/Tables/ListeOrdonnancesMed.java
package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.DAO.OrdonnancesDAO;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Ordonnances;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeOrdonnancesMed extends AbstractTableModel {


    private final String[] ENTETE = new String[] {
            "Date", "Client", "Détail"
    };

    private OrdonnancesDAO ordonnancesDAO;
    private List<Ordonnances> ordonnances;

    public ListeOrdonnancesMed(Medecin medecin) {
        ordonnancesDAO = new OrdonnancesDAO();
        this.ordonnances = ordonnancesDAO.findAllByIdMed(medecin.getId());
    }

    public Ordonnances getOrdonnanceAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < ordonnances.size()) {
            return ordonnances.get(rowIndex);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return ENTETE[column];
    }

    @Override
    public int getRowCount() {
        return ordonnances.size();
    }

    @Override
    public int getColumnCount() {
        return ENTETE.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ordonnances ordonnance = ordonnances.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ordonnance.getDate();
            case 1:
                return ordonnance.getClient() != null
                        ? ordonnance.getClient().getPrenom() + " " + ordonnance.getClient().getNom()
                        : "Client inconnu";
            case 2:
                return "Détails";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }
}