package fr.afpa.dev.pompey.Main;

import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Vue.ControllerAccueil;
import fr.afpa.dev.pompey.Exception.SaisieException;

public class Sparadrap {
    //TODO enlever les exceptions inutiles, sauf les saisies
    //TODO Faire les placeholders pour les champs de saisie
    //TODO Remplacer les alertes de fenetres en showlabel

    // Main method
    public static void main(String[] args) throws SaisieException {
        Sparadrap sparadrap = new Sparadrap();
        sparadrap.start();

        //TODO : ControllerAchat A FAIRE!
        //TODO : ControllerClient FAIT!
        //TODO : ControllerDetailAchat A FAIRE!
        //TODO : ControllerDetailClient A FAIRE!
        //TODO : ControllerDetailMedecin A FAIRE!
        //TODO : ControllerDetailMutuelle A FAIRE!
        //TODO : ControllerHistoriqueAchat A FAIRE!
        //TODO : ControllerListeClient A FAIRE!
        //TODO : ControllerListeClientIdMed A FAIRE
        //TODO : ControllerListeMedecin A FAIRE!
        //TODO : ControllerListeMutuelle A FAIRE!
        //TODO : ControllerListeOrdonnanceIdMed A FAIRE!
        //TODO : ControllerMedecin A FAIRE!
        //TODO : ControllerMedicament A FAIRE!

    }

    // Start the application
    public void start() throws SaisieException {
        ControllerAccueil accueil = new ControllerAccueil();
        accueil.setVisible(true);

    }
}
