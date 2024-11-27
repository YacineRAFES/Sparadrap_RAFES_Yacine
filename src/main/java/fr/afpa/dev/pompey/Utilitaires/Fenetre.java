package fr.afpa.dev.pompey.Utilitaires;


import javax.swing.*;

public class Fenetre extends JFrame {
    public Fenetre() {

    }

    /**
     * Crée une fenêtre avec un message
     *
     * @param message Le message à afficher
     */
    public static void Fenetre(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Crée une fenêtre de confirmation
     *
     * @param message Le message à afficher
     * @return true si l'utilisateur clique sur "Oui", false sinon
     */
    public static boolean Confirmation(String message, String titreOfFenetre){
        int choix = JOptionPane.showConfirmDialog(null, message, titreOfFenetre, JOptionPane.YES_NO_OPTION);
        return choix == JOptionPane.YES_OPTION;
    }
}
