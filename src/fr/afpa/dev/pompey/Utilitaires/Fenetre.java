package fr.afpa.dev.pompey.Utilitaires;


import javax.swing.*;

public class Fenetre extends JFrame {
    public Fenetre() {

    }

    public static void Fenetre(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    //Créer un constructeur avec une fenetre avec une confirmation
    public static boolean Confirmation(String message){
        int choix = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
        return choix == JOptionPane.YES_OPTION;
    }
}
