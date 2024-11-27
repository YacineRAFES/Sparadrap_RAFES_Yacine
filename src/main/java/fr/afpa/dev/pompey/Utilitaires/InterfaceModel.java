package fr.afpa.dev.pompey.Utilitaires;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Vue.ControllerDetailClient;
import fr.afpa.dev.pompey.Vue.ControllerDetailMutuelle;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.awt.event.ActionEvent;

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
     * Affiche un message sur un JLabel pendant une durée spécifiée, puis le cache.
     * Le label est réinitialisé avant d'afficher le message pour s'assurer qu'il peut être affiché plusieurs fois.
     *
     * @param label   Le JLabel sur lequel afficher le message.
     * @param message Le message à afficher.
     * @param color   La couleur du texte du message.
     */
    public static void ShowLabelWithTimer(JLabel label, String message, Color color) {
        label.setText("");
        label.setForeground(Color.BLACK);
        Timer resetTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(message);
                label.setForeground(color);
                Timer timer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setText("");
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        resetTimer.setRepeats(false);
        resetTimer.start();
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

    //TODO: Fonction à revoir
//    public static void ButtonDetail(Object NomDuController, int id) {
//
//        try {
//            System.out.println("Appel de setId avec id = " + id);
//            Method setIdMethod = NomDuController.getClass().getMethod("setId", int.class);
//            setIdMethod.invoke(NomDuController, id);
//
//            Method getIdMethod = NomDuController.getClass().getMethod("getId");
//            int newId = (int) getIdMethod.invoke(NomDuController);
//            System.out.println("Après setId, id = " + newId);
//
//            Method setVisibleMethod = NomDuController.getClass().getMethod("setVisible", boolean.class);
//            setVisibleMethod.invoke(NomDuController, true);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

    //TODO: Fonction à revoir également
//    public static void ButtonSupprimer(Object NomDeLaDAO, int id){
//       try{
//              Method method = NomDeLaDAO.getClass().getMethod("delete", int.class);
//              method.invoke(NomDeLaDAO, id);
//       }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
//              e.printStackTrace();
//       }
//    }

    public static void VidangeDuTableau(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    public static void AjoutLigneDansTableau(JTable table, Object[] data){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(data);
    }

    public static void SuppressionLigneDansTableau(JTable table, int rowIndex){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(rowIndex);
    }

    public static void TimerAppelFonction(int delay, ActionListener action){
        Timer timer = new Timer(delay, action);
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Configurer un JFrame avec un JPanel, redimensionnable et un titre
     *
     * @param frame     La Fenêtre à configurer
     * @param panel     Le Panel à ajouter au JFrame
     * @param resizable Si le JFrame est redimensionnable
     * @param title     Le titre de la Fenêtre
     */
    public static void configurerFenetre(JFrame frame, JPanel panel, boolean resizable, String title){
        frame.setContentPane(panel);
        frame.setResizable(resizable);
        frame.setTitle(title);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public static void hauteurCellule(JTable table, int height){
        table.setRowHeight(height);
    }

}