package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.AchatDirect;
import fr.afpa.dev.pompey.Modele.Ordonnances;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeHistoriqueAchat extends AbstractTableModel {

    private final String[] ENTETE = new String[] {
            "Date", "Client", "Type d'Achat", "Détail", "Action"
    };

    private final List<AchatDirect> achatDirect;
    private final List<Ordonnances> ordonnances;

    public ListeHistoriqueAchat(List<AchatDirect> achatDirect, List<Ordonnances> ordonnances) {
        this.achatDirect = achatDirect;
        this.ordonnances = ordonnances;
    }

    @Override
    public String getColumnName(int column) {
        return ENTETE[column];
    }

    @Override
    public int getRowCount() {
        return ordonnances.size() + achatDirect.size();
    }

    @Override
    public int getColumnCount() {
        return ENTETE.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < achatDirect.size()) {
            // Si l'index est dans les limites de achatSansOrdonnances
            AchatDirect achatDirects = achatDirect.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return achatDirects.getDate();
                case 1:
                    return achatDirects.getClient() != null
                            ? achatDirects.getClient().getPrenom() + " " + achatDirects.getClient().getNom()
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
            int ordonnanceIndex = rowIndex - achatDirect.size();  // Calculer l'index relatif pour ordonnance
            Ordonnances ordonnance = ordonnances.get(ordonnanceIndex);
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
