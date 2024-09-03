package fr.afpa.dev.pompey.Main;

import fr.afpa.dev.pompey.Controller.Accueil;

public class Sparadrap {
    public static void main(String[] args) {
        Sparadrap sparadrap = new Sparadrap();
        sparadrap.start();
    }
    public void start() {
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
}
