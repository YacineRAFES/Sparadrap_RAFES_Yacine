package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import fr.afpa.dev.pompey.Modele.Region;


import java.util.List;

public class DAOUtils {
    private static final MedecinDAO medecinDAO = new MedecinDAO();
    private static final ClientDAO clientDAO = new ClientDAO();
    private static final MutuelleDAO mutuelleDAO = new MutuelleDAO();
    private static final RegionDAO regionDAO = new RegionDAO();

    public static List<Medecin> getMedecins() {
        return medecinDAO.findAll();
    }

    public static List<Client> getClients() {
        return clientDAO.findAll();
    }

    public static List<Mutuelle> getMutuelles() {
        return mutuelleDAO.findAll();
    }

    public static List<Region> getRegions() {
        return regionDAO.findAll();
    }




}
