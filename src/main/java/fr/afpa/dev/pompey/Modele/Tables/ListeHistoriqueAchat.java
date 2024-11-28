package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.AchatDirect;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.DAO.AchatDirectDAO;
import fr.afpa.dev.pompey.Modele.DAO.ClientDAO;
import fr.afpa.dev.pompey.Modele.DAO.OrdonnancesDAO;
import fr.afpa.dev.pompey.Modele.Ordonnances;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeHistoriqueAchat extends AbstractTableModel {

    private final String[] ENTETE = new String[] {
            "ID" ,"Date", "Client", "Type d'Achat", "Détail", "Action"
    };

    private List<AchatDirect> achatDirect;
    private List<Ordonnances> ordonnances;


    AchatDirectDAO achatDirectDAO = new AchatDirectDAO();
    OrdonnancesDAO ordonnancesDAO = new OrdonnancesDAO();

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
            AchatDirect achatDirects = achatDirect.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return achatDirects.getId();
                case 1:
                    return achatDirects.getDate();
                case 2:
                    Client client = achatDirectDAO.find(achatDirects.getId()).getClient();
                    return client != null
                            ? client.getPrenom() + " " + client.getNom()
                            : "Client inconnu";
                case 3:
                    return "Sans Ordonnance";
                case 4:
                    return "Détails";
                case 5:
                    return "Supprimer";
                default:
                    return null;
            }
        } else if (rowIndex < achatDirect.size() + ordonnances.size()) {
            Ordonnances ordonnance = ordonnances.get(rowIndex - achatDirect.size());
            switch (columnIndex) {
                case 0:
                    return ordonnance.getId();
                case 1:
                    return ordonnance.getDate();
                case 2:
                    Client client = ordonnancesDAO.find(ordonnance.getId()).getClient();
                    return client != null
                            ? client.getPrenom() + " " + client.getNom()
                            : "Client inconnu";
                case 3:
                    return "Ordonnance";
                case 4:
                    return "Détails";
                case 5:
                    return "Supprimer";
                default:
                    return null;
            }
        }
        return null;
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4 || columnIndex == 5;
    }

    public void refreshList() {
        this.achatDirect = achatDirectDAO.findAll();
        this.ordonnances = ordonnancesDAO.findAll();
        fireTableDataChanged();
    }
}
