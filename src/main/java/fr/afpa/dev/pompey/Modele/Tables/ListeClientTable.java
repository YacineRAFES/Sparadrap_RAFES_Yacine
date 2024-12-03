package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;

import static fr.afpa.dev.pompey.Modele.DAO.DAOUtils.getClients;

public class ListeClientTable extends AbstractTableModel {
    private final String[] ENTETE = new String[] {
            "ID", "Nom", "Prenom", "Email", "Téléphone", "Détail", "Action"
    };
    private List<Client> clients;

    public ListeClientTable(List<Client> clients) {
        this.clients = clients;
    }

    public String getColumnName(int column) {
        return ENTETE[column];
    }

    public int getRowCount() {
        return clients.size();
    }

    public int getColumnCount() {
        return ENTETE.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return client.getId();
            case 1:
                return client.getNom();
            case 2:
                return client.getPrenom();
            case 3:
                return client.getCoordonnees().getEmail();
            case 4:
                return client.getCoordonnees().getTelephone();
            case 5:
                return "Détail";
            case 6:
                return "Supprimer";
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        if(col == 5 || col == 6) {
            return true;
        }else{
            return false;
        }
    }

    public void refreshList() {
        this.clients = getClients();
        fireTableDataChanged();
    }


}
