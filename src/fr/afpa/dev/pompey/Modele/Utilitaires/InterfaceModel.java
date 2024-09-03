package fr.afpa.dev.pompey.Modele.Utilitaires;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InterfaceModel extends javax.swing.JFrame {
    public InterfaceModel() {

    }

    public static void AjouterPlaceholder(JTextField textField, String placeholder) {
        // Définir le placeholder
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        // Ajouter un écouteur pour supprimer ou rétablir le placeholder
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    public static void AjouterPlaceholderCombobox(JComboBox comboBox, String placeholder) {
        comboBox.insertItemAt(placeholder, 0);
        comboBox.setSelectedIndex(0);
    }

    public static void refresh() {
    }

    public static void Refresh(JTable tableModel) {
        tableModel.revalidate();
        tableModel.repaint();
        ((AbstractTableModel) tableModel.getModel()).fireTableDataChanged();
    }
}
