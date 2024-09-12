package fr.afpa.dev.pompey.Modele;

import java.util.ArrayList;
import java.util.List;

public class GestionListe {
    private static List<Client> client = new ArrayList<>();
    private static List<Medecin> medecin = new ArrayList<>();
    private static List<Mutuelle> mutuelle = new ArrayList<>();
    private static List<Medicament> medicament = new ArrayList<>();
    private static List<AchatSansOrdonnance> achatSansOrdonnance = new ArrayList<>();
    private static List<Ordonnance> ordonnance = new ArrayList<>();
    private static List<TableMedicamentTemporaire> tableMedicamentTemporaire = new ArrayList<>();

    //GETTER, ADDERS ET REMOVERS
    public static List<Client> getClient() {
        return client;
    }

    public static void addClient(Client client) {
        getClient().add(client);
    }

    public static void removeClient(Client client) {
        getClient().remove(client);
    }

    public static List<Medecin> getMedecin() {
        return medecin;
    }

    public static void addMedecin(Medecin medecin) {
        getMedecin().add(medecin);
    }

    public static void removeMedecin(Medecin medecin) {
        getMedecin().remove(medecin);
    }

    public static List<Mutuelle> getMutuelle() {
        return mutuelle;
    }

    public static void addMutuelle(Mutuelle mutuelle) {
        getMutuelle().add(mutuelle);
    }

    public static List<Medicament> getMedicament() {
        return medicament;
    }

    public static void addMedicament(Medicament medicament) {
        getMedicament().add(medicament);
    }

    public static void removeMedicament(Medicament medicament) {
        getMedicament().remove(medicament);
    }

    public static List<AchatSansOrdonnance> getAchatSansOrdonnance() {
        return achatSansOrdonnance;
    }

    public static void addAchatSansOrdonnance(AchatSansOrdonnance achatSansOrdonnance) {
        getAchatSansOrdonnance().add(achatSansOrdonnance);
    }

    public static void removeAchatSansOrdonnance(AchatSansOrdonnance achatSansOrdonnance) {
        getAchatSansOrdonnance().remove(achatSansOrdonnance);
    }

    public static List<Ordonnance> getOrdonnance() {
        return ordonnance;
    }

    public static void addOrdonnance(Ordonnance ordonnance) {
        getOrdonnance().add(ordonnance);
    }

    public static void removeOrdonnance(int ordonnance) {
        getOrdonnance().remove(ordonnance);
    }

    public static List<TableMedicamentTemporaire> getTableMedicamentTemporaire() {
        return tableMedicamentTemporaire;
    }

    public static void addTableMedicamentTemporaire(TableMedicamentTemporaire tableMedicamentTemporaire) {
        getTableMedicamentTemporaire().add(tableMedicamentTemporaire);
    }

    public static void removeTableMedicamentTemporaire(TableMedicamentTemporaire tableMedicamentTemporaire) {
        getTableMedicamentTemporaire().remove(tableMedicamentTemporaire);
    }

}
