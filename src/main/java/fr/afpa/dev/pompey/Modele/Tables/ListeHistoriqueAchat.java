package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.AchatSansOrdonnance;
import fr.afpa.dev.pompey.Modele.Ordonnance;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeHistoriqueAchat extends AbstractTableModel {

    private final String[] ENTETE = new String[] {
            "Date", "Client", "Type d'Achat", "Détail", "Action"
    };

    private final List<AchatSansOrdonnance> achatSansOrdonnances;
    private final List<Ordonnance> ordonnances;

    public ListeHistoriqueAchat(List<AchatSansOrdonnance> achatSansOrdonnances, List<Ordonnance> ordonnances) {
        this.achatSansOrdonnances = achatSansOrdonnances;
        this.ordonnances = ordonnances;
    }

    @Override
    public String getColumnName(int column) {
        return ENTETE[column];
    }

    @Override
    public int getRowCount() {
        return ordonnances.size() + achatSansOrdonnances.size();
    }

    @Override
    public int getColumnCount() {
        return ENTETE.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < achatSansOrdonnances.size()) {
            // Si l'index est dans les limites de achatSansOrdonnances
            AchatSansOrdonnance achatSansOrdonnance = achatSansOrdonnances.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return achatSansOrdonnance.getDate();
                case 1:
                    return achatSansOrdonnance.getClient() != null
                            ? achatSansOrdonnance.getClient().getPrenom() + " " + achatSansOrdonnance.getClient().getNom()
                            : "Client inconnu";
                case 2:
                    return "Sans Ordonnance";
                case 3:
                    return "Détails";
                case 4:
                    return "Supprimer";
                default:
                    return null;
            }
        } else {
            // Si l'index dépasse achatSansOrdonnances, il faut accéder à la liste des ordonnances
            int ordonnanceIndex = rowIndex - achatSansOrdonnances.size();  // Calculer l'index relatif pour ordonnance
            Ordonnance ordonnance = ordonnances.get(ordonnanceIndex);
            switch (columnIndex) {
                case 0:
                    return ordonnance.getDate();
                case 1:
                    return ordonnance.getClient() != null
                            ? ordonnance.getClient().getPrenom() + " " + ordonnance.getClient().getNom()
                            : "Client inconnu";
                case 2:
                    return "Ordonnance";
                case 3:
                    return "Détails";
                case 4:
                    return "Supprimer";
                default:
                    return null;
            }
        }
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }


}
