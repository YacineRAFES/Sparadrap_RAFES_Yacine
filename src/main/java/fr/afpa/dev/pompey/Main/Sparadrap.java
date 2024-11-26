package fr.afpa.dev.pompey.Main;

import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.*;
import fr.afpa.dev.pompey.Vue.ControllerAccueil;
import fr.afpa.dev.pompey.Exception.SaisieException;

import java.util.Date;


public class Sparadrap {
    //TODO enlever les exceptions inutiles, sauf les saisies
    //TODO Faire les placeholders pour les champs de saisie
    //TODO Remplacer les alertes de fenetres en showlabel
    private MutuelleDAO mutuelleDAO;
    private VilleDAO villeDAO;
    private AdressesDAO adressesDAO;
    private CoordonneesDAO coordonneesDAO;
    private ClientDAO clientDAO;
    private MedecinDAO medecinDAO;
    private MedicamentDAO medicamentDAO;
    private OrdonnancesDAO ordonnancesDAO;
    private RegionDAO regionDAO;


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
        //TODO : LES TESTS A FAIRE!

    }

    // Start the application
    public void start() throws SaisieException {
        ControllerAccueil controllerAccueil = new ControllerAccueil();
        controllerAccueil.setVisible(true);
//
//        regionDAO = new RegionDAO();
//        String[] regionList = {"Grand Est", "Ile-de-France", "Nouvelle-Aquitaine", "Auvergne-Rhône-Alpes", "Bourgogne-Franche-Comté", "Bretagne", "Centre-Val de Loire", "Corse", "Occitanie", "Hauts-de-France", "Normandie", "Pays de la Loire", "Provence-Alpes-Côte d'Azur"};
//        for (String region : regionList) {
//            regionDAO.create(new Region(region));
//        }




    }
}
