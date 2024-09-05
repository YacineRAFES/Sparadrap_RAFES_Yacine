package fr.afpa.dev.pompey.Modele.Tables;

import fr.afpa.dev.pompey.Modele.AchatSansOrdonnance;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Modele.Ordonnance;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeHistoriqueAchat extends AbstractTableModel {
    private final String[] ENTETE = new String[] {
            "Nom Client", "Prénom Client", "Type d'Achat", "Prix", "Actions"
    };

    private final List<AchatSansOrdonnance> AchatSansOrdonnance;
    private final List<Ordonnance> Ordonnance;
    private final List<Medicament> Medicament;

    public ListeHistoriqueAchat(List<AchatSansOrdonnance> AchatSansOrdonnance, List<Ordonnance> ordonnance, List<Medicament> medicament) {
        this.AchatSansOrdonnance = AchatSansOrdonnance;
        this.Ordonnance = ordonnance;
        this.Medicament = medicament;
    }

    @Override
    public String getColumnName(int column) {
        return ENTETE[column];
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AchatSansOrdonnance achatSansOrdonnance = AchatSansOrdonnance.get(rowIndex);
        Ordonnance ordonnance = Ordonnance.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return achatSansOrdonnance.getClient().getNom() != null ? achatSansOrdonnance.getClient().getNom() : ordonnance.getClient().getNom();
            case 1:
                return achatSansOrdonnance.getClient().getPrenom() != null ? achatSansOrdonnance.getClient().getPrenom() : ordonnance.getClient().getPrenom();
            case 2:
                if (achatSansOrdonnance == null) {
                    return "Ordonnance";
                } else {
                    return "Sans Ordonnance";
                }
            case 3:
                int total = 0;
                for (int i = 0; i < achatSansOrdonnance.getListeMedicament().length; i++) {
                    total = achatSansOrdonnance.getListeMedicament()[i].equals(Medicament.get(i).getNom()) ? Integer.parseInt(Medicament.get(i).getPrix()) : 0;
                }
                return total;
            case 4:
                return "Supprimer";
            default:
                return null;
        }
    }
}
