package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeClientTable extends AbstractTableModel {
    private final String[] ENTETE = new String[] {
            "Nom", "Prenom", "Email", "Téléphone", "Détail", "Action"
    };
    private final List<Client> clients;

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
                return client.getNom();
            case 1:
                return client.getPrenom();
            case 2:
                return client.getEmail();
            case 3:
                return client.getTelephone();
            case 4:
                return "Détail";
            case 5:
                return "Supprimer";
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        if(col == 4 || col == 5) {
            return true;
        }else{
            return false;
        }
    }


}
