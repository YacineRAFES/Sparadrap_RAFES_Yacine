package fr.afpa.dev.pompey.Modele.Utilitaires;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InterfaceModel extends javax.swing.JFrame {
    public InterfaceModel() {

    }

    public static void AjouterPlaceholder(JTextField textField, String placeholder) {
        // Définir le placeholder
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        // Listeners s'il est cliqué ou pas
        ActionPlaceholder(placeholder, textField);
    }

    public static void AjouterPlaceholderComboboxNonEditable(JComboBox<String> comboBox, String placeholder) {
        JTextField editorComponent = (JTextField) comboBox.getEditor().getEditorComponent();
        editorComponent.setForeground(Color.GRAY);
        editorComponent.setText(placeholder);

        // Listeners s'il est cliqué ou pas
        ActionPlaceholder(placeholder, editorComponent);
    }
    public static void AjouterPlaceholderComboboxEditable(JComboBox<String> comboBox, String placeholder) {
        comboBox.setEditable(true);
        JTextField editorComponent = (JTextField) comboBox.getEditor().getEditorComponent();
        editorComponent.setForeground(Color.GRAY);
        editorComponent.setText(placeholder);

        // Listeners s'il est cliqué ou pas
        ActionPlaceholder(placeholder, editorComponent);
    }

    private static void ActionPlaceholder(String placeholder, JTextField editorComponent) {
        editorComponent.addFocusListener(new FocusAdapter() {

            //Si le composant est cliqué, on efface le placeholder
            @Override
            public void focusGained(FocusEvent e) {
                if (editorComponent.getText().equals(placeholder)) {
                    editorComponent.setText("");
                    editorComponent.setForeground(Color.BLACK);
                }
            }

            //SI le composant est lâché, on ajoute le placeholder
            @Override
            public void focusLost(FocusEvent e) {
                if (editorComponent.getText().isEmpty()) {
                    editorComponent.setText(placeholder);
                    editorComponent.setForeground(Color.GRAY);
                }
            }
        });
    }

    public static void Refresh(JTable tableModel) {
        tableModel.revalidate();
        tableModel.repaint();
        ((AbstractTableModel) tableModel.getModel()).fireTableDataChanged();
    }
}
