package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Ordonnance;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListeClientMed extends AbstractTableModel  {

    private final String[] ENTETE = new String[] {
            "Nom", "Prenom", "Email", "Téléphone", "Code Postal", "Ville", "Sécu"
    };

    private List<Client> clients;

    public ListeClientMed(Medecin medecin) {

        Set<Client> uniqueClients = new LinkedHashSet<>();
        for (Ordonnance ordonnance : medecin.getOrdonnances()) {
            uniqueClients.add(ordonnance.getClient());
        }
        this.clients = uniqueClients.stream().collect(Collectors.toList());
    }

    @Override
    public String getColumnName(int column) {
        return ENTETE[column];
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return ENTETE.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return client.getNom() != null ? client.getNom() : "Non renseigné";
            case 1:
                return client.getPrenom() != null ? client.getPrenom() : "Non renseigné";
            case 2:
                return client.getEmail() != null ? client.getEmail() : "Non renseigné";
            case 3:
                return client.getTelephone() != null ? client.getTelephone() : "Non renseigné";
            case 4:
                return client.getCodePostal() != null ? client.getCodePostal() : "Non renseigné";
            case 5:
                return client.getVille() != null ? client.getVille() : "Non renseigné";
            case 6:
                return client.getNumeroSecuClient() != null ? client.getNumeroSecuClient() : "Non renseigné";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
    }
}
