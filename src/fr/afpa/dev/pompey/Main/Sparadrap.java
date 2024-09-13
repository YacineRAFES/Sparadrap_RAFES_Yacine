package fr.afpa.dev.pompey.Main;

import fr.afpa.dev.pompey.Controller.ControllerAccueil;

public class Sparadrap {
    public static void main(String[] args) {
        Sparadrap sparadrap = new Sparadrap();
        sparadrap.start();
    }
    public void start() {
        ControllerAccueil accueil = new ControllerAccueil();
        accueil.setVisible(true);

    }
}
