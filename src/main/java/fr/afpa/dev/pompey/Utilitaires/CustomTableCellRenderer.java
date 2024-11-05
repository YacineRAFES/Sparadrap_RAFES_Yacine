package fr.afpa.dev.pompey.Utilitaires;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Si la valeur est null, on n'applique pas le truncation, on laisse la cellule vide
        if (value == null) {
            setText("");
            return cell;
        }

        // Récupère la largeur de la colonne
        int columnWidth = table.getColumnModel().getColumn(column).getWidth();

        // Récupère la largeur du texte dans la cellule
        String text = value.toString();
        int textWidth = cell.getFontMetrics(cell.getFont()).stringWidth(text);

        // Si le texte dépasse la taille de la colonne, on ajoute "..."
        if (textWidth > columnWidth) {
            String truncatedText = truncateTextToFitWidth(text, columnWidth, cell);
            setText(truncatedText);
        } else {
            setText(text);
        }

        return cell;
    }

    // Fonction pour tronquer le texte selon la taille de la colonne
    private String truncateTextToFitWidth(String text, int columnWidth, Component cell) {
        String ellipsis = "...";
        int ellipsisWidth = cell.getFontMetrics(cell.getFont()).stringWidth(ellipsis);
        int availableWidth = columnWidth - ellipsisWidth;
        String truncatedText = "";

        for (int i = 0; i < text.length(); i++) {
            String substring = text.substring(0, i + 1);
            int substringWidth = cell.getFontMetrics(cell.getFont()).stringWidth(substring);
            if (substringWidth > availableWidth) {
                break;
            }
            truncatedText = substring;
        }

        return truncatedText + ellipsis;
    }
}
