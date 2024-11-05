package fr.afpa.dev.pompey.Utilitaires;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

/**
 * A class that contains utility methods for creating and managing interfaces
 */
public class InterfaceModel extends JFrame {
    private static JTextField barRecherche;
    private static TableRowSorter<TableModel> rowSorter;
    private static boolean isVisibleBlink = true;

    /**
     * Creates a new InterfaceModel
     */
    public InterfaceModel() {

    }

    /**
     * Refreshes a JTable
     *
     * @param tableModel The JTable to refresh
     */
    public static void Refresh(JTable tableModel) {
        tableModel.revalidate();
        tableModel.repaint();
        ((AbstractTableModel) tableModel.getModel()).fireTableDataChanged();
    }

    /**
     * Filters a JTable based on a JTextField
     *
     * @param barRecherche The JTextField to filter with
     * @param tableModel   The TableModel to filter
     * @param NomDeLaTable The JTable to filter
     */
    public static void filterTable(JTextField barRecherche, TableModel tableModel, JTable NomDeLaTable) {
        rowSorter = new TableRowSorter<>(tableModel);
        NomDeLaTable.setRowSorter(rowSorter);

        barRecherche.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = barRecherche.getText();
                if (text.trim().isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    RowFilter<Object, Object> rf = RowFilter.regexFilter("(?i)" + text);
                    rowSorter.setRowFilter(rf);
                }
            }
        });
    }

    /**
     * Adds a JLabel with text and color
     *
     * @param label The JLabel to add
     * @param text  The text to display
     * @param color The color of the text
     */
    public static void ShowLabel(JLabel label, String text, Color color) {
        label.setText(text);
        label.setForeground(color);
    }

    /**
     * Adds a JLabel with text and color that blinks for 3 seconds
     *
     * @param label The JLabel to add
     * @param text  The text to display
     * @param color The color of the text
     */
    public static void ShowLabelWithBlinker(JLabel label, String text, Color color) {
        label.setText(text);
        label.setForeground(color);
        Timer blinkTimer = new Timer(500, new ActionListener() {
            private boolean isVisible = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                label.setVisible(isVisible);
            }
        });
        blinkTimer.start();

        Timer stopTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blinkTimer.stop();
                label.setVisible(true); // Ensure the label is visible after stopping

                // Timer to hide the label 2 seconds after stopping the blink
                Timer hideTimer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setVisible(false);
                    }
                });
                hideTimer.setRepeats(false); // Ensure this timer only runs once
                hideTimer.start();
            }
        });
        stopTimer.setRepeats(false); // Ensure this timer only runs once
        stopTimer.start();
    }

    /**
     * Adds a JLabel with text and color that disappears after 3 seconds
     *
     * @param label The JLabel to add
     * @param text  The text to display
     * @param color The color of the text
     */
    public static void ShowLabelWithTimer(JLabel label, String text, Color color) {
        label.setText(text);
        label.setForeground(color);
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Sets the background color of a JComboBox
     *
     * @param comboBox The JComboBox to set the color of
     * @param blinkColor    The color to set
     */
    public static void setComboBoxColor(JComboBox<?> comboBox, Color blinkColor) {
        Color originalColor = comboBox.getBackground(); // Store the original background color

        // it blinks for 3 seconds then stops
        Timer blinkTimer = new Timer(500, new ActionListener() {
            private boolean isVisible = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                comboBox.setBackground(isVisible ? blinkColor : originalColor);
            }
        });
        blinkTimer.start();

        Timer stopTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blinkTimer.stop();
                comboBox.setBackground(originalColor); // Revert to the original background color
            }
        });
        stopTimer.setRepeats(false); // Ensure this timer only runs once
        stopTimer.start();
    }
}