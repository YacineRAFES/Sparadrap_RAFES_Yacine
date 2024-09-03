package fr.afpa.dev.pompey.Modele;

import java.util.ArrayList;
import java.util.List;

public class GestionListe {
    private static List<Client> client = new ArrayList<>();
    private static List<Medecin> medecin = new ArrayList<>();

    //GETTER, ADDERS ET REMOVERS
    public static List<Client> getClient() {
        return client;
    }

    public static void addClient(Client client) {
        getClient().add(client);
    }

    public static List<Medecin> getMedecin() {
        return medecin;
    }

    public static void addMedecin(Medecin medecin) {
        getMedecin().add(medecin);
    }
}
