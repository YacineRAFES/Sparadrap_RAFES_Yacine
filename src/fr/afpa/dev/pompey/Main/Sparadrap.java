package fr.afpa.dev.pompey.Main;

import fr.afpa.dev.pompey.Controller.ControllerAccueil;
import fr.afpa.dev.pompey.Exception.SaisieException;

public class Sparadrap {

    // Main method
    public static void main(String[] args) throws SaisieException {
        Sparadrap sparadrap = new Sparadrap();
        sparadrap.start();
    }

    // Start the application
    public void start() throws SaisieException {
        ControllerAccueil accueil = new ControllerAccueil();
        accueil.setVisible(true);

    }
}
