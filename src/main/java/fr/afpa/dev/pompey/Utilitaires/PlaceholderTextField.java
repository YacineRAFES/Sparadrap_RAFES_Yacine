
package fr.afpa.dev.pompey.Utilitaires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * A JTextField with a placeholder
 */
public class PlaceholderTextField extends JTextField {
    private String placeholder;

    /**
     * Creates a new PlaceholderTextField
     *
     * @param placeholder The placeholder text
     */
    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            int padding = (getHeight() - getFont().getSize()) / 2;
            g2.drawString(placeholder, getInsets().left, getHeight() - padding - 1);
            g2.dispose();
        }
    }

    /**
     * Sets a placeholder for a JTextField
     *
     * @param textField   The JTextField to set the placeholder for
     * @param placeholder The placeholder text
     */
    public static void setPlaceholder(JTextField textField, String placeholder) {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.repaint();
            }
        });

        textField.setUI(new javax.swing.plaf.basic.BasicTextFieldUI() {
            @Override
            protected void paintSafely(Graphics g) {
                super.paintSafely(g);
                if (textField.getText().isEmpty() && !textField.isFocusOwner()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(Color.GRAY);
                    g2.setFont(textField.getFont().deriveFont(Font.ITALIC));
                    int padding = (textField.getHeight() - textField.getFont().getSize()) / 2;
                    g2.drawString(placeholder, textField.getInsets().left, textField.getHeight() - padding - 1);
                    g2.dispose();
                }
            }
        });
    }

    public static void setPlaceholderCombobox(JComboBox combobox, String placeholder) {
        combobox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                combobox.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                combobox.repaint();
            }
        });

        combobox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                super.paintCurrentValueBackground(g, bounds, hasFocus);
                if (combobox.getSelectedItem() == null && !combobox.isFocusOwner()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(Color.GRAY);
                    g2.setFont(combobox.getFont().deriveFont(Font.ITALIC));
                    int padding = (combobox.getHeight() - combobox.getFont().getSize()) / 2;
                    g2.drawString(placeholder, combobox.getInsets().left, combobox.getHeight() - padding - 1);
                    g2.dispose();
                }
            }
        });
    }
}