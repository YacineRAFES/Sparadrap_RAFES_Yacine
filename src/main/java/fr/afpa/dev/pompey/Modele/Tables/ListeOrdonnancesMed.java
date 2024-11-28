// src/fr/afpa/dev/pompey/Modele/Tables/ListeOrdonnancesMed.java
package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.DAO.ClientDAO;
import fr.afpa.dev.pompey.Modele.DAO.OrdonnancesDAO;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Ordonnances;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeOrdonnancesMed extends AbstractTableModel {


    private final String[] ENTETE = new String[] {
            "ID" ,"Date", "Client", "Détail"
    };

    private OrdonnancesDAO ordonnancesDAO;
    private ClientDAO clientDAO;
    private List<Ordonnances> ordonnances;

    public ListeOrdonnancesMed(Medecin medecin) {
        ordonnancesDAO = new OrdonnancesDAO();
        this.ordonnances = ordonnancesDAO.findAllByIdMed(medecin.getId());
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
                return ordonnance.getId();
            case 1:
                return ordonnance.getDate();
            case 2:
                return ordonnance.getClient() != null
                        ? ordonnance.getClient().getPrenom() + " " + ordonnance.getClient().getNom()
                        : "Client inconnu";
            case 3:
                return "Détails";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }
}