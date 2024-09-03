package fr.afpa.dev.pompey.Modele.Utilitaires;


import javax.swing.*;

public class Fenetre extends JFrame {
    public Fenetre() {

    }

    public static void Fenetre(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
