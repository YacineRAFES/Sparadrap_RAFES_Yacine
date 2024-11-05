package fr.afpa.dev.pompey.Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe GestionListe est le modèle de la gestion des listes
 */
public class GestionListe {
    private static List<Client> client = new ArrayList<>();
    private static List<Medecin> medecin = new ArrayList<>();
    private static List<Mutuelle> mutuelle = new ArrayList<>();
    private static List<Medicament> medicament = new ArrayList<>();
    private static List<AchatSansOrdonnance> achatSansOrdonnance = new ArrayList<>();
    private static List<Ordonnance> ordonnance = new ArrayList<>();
    private static List<TableMedicamentTemporaire> tableMedicamentTemporaire = new ArrayList<>();

    //GETTER, ADDERS ET REMOVERS
    /**
     * Permet d'obtenir la liste des clients
     *
     * @return La liste des clients
     */
    public static List<Client> getClient() {
        return client;
    }

    /**
     * Permet d'ajouter un client
     *
     * @param client Le client
     */
    public static void addClient(Client client) {
        getClient().add(client);
    }

    /**
     * Permet de supprimer un client
     *
     * @param client Le client
     */
    public static void removeClient(Client client) {
        getClient().remove(client);
    }

    /**
     * Permet d'obtenir la liste des médecins
     *
     * @return La liste des médecins
     */
    public static List<Medecin> getMedecin() {
        return medecin;
    }

    /**
     * Permet d'ajouter un médecin
     *
     * @param medecin Le médecin
     */
    public static void addMedecin(Medecin medecin) {
        getMedecin().add(medecin);
    }

    /**
     * Permet de supprimer un médecin
     *
     * @param medecin Le médecin
     */
    public static void removeMedecin(Medecin medecin) {
        getMedecin().remove(medecin);
    }

    /**
     * Permet d'obtenir la liste des mutuelles
     *
     * @return La liste des mutuelles
     */
    public static List<Mutuelle> getMutuelle() {
        return mutuelle;
    }

    /**
     * Permet d'ajouter une mutuelle
     *
     * @param mutuelle La mutuelle
     */
    public static void addMutuelle(Mutuelle mutuelle) {
        getMutuelle().add(mutuelle);
    }

    /**
     * @return La liste des médicaments
     */
    public static List<Medicament> getMedicament() {
        return medicament;
    }

    /**
     * @param medicament Ajouter un médicament
     */
    public static void addMedicament(Medicament medicament) {
        getMedicament().add(medicament);
    }

    /**
     * @param medicament Supprimer un médicament
     */
    public static void removeMedicament(Medicament medicament) {
        getMedicament().remove(medicament);
    }

    /**
     * @return La liste des achats sans ordonnance
     */
    public static List<AchatSansOrdonnance> getAchatSansOrdonnance() {
        return achatSansOrdonnance;
    }

    /**
     * @param achatSansOrdonnance Ajouter un achat sans ordonnance
     */
    public static void addAchatSansOrdonnance(AchatSansOrdonnance achatSansOrdonnance) {
        getAchatSansOrdonnance().add(achatSansOrdonnance);
    }

    /**
     * @param achatSansOrdonnance Supprimer un achat sans ordonnance
     */
    public static void removeAchatSansOrdonnance(AchatSansOrdonnance achatSansOrdonnance) {
        getAchatSansOrdonnance().remove(achatSansOrdonnance);
    }

    /**
     * @return La liste des ordonnances
     */
    public static List<Ordonnance> getOrdonnance() {
        return ordonnance;
    }

    /**
     * @param ordonnance Ajouter une ordonnance
     */
    public static void addOrdonnance(Ordonnance ordonnance) {
        getOrdonnance().add(ordonnance);
    }

    /**
     * @param ordonnance Supprimer une ordonnance
     */
    public static void removeOrdonnance(int ordonnance) {
        getOrdonnance().remove(ordonnance);
    }

    /**
     * @return La liste des médicaments temporaires
     */
    public static List<TableMedicamentTemporaire> getTableMedicamentTemporaire() {
        return tableMedicamentTemporaire;
    }

    /**
     * @param tableMedicamentTemporaire Ajouter un médicament temporaire
     */
    public static void addTableMedicamentTemporaire(TableMedicamentTemporaire tableMedicamentTemporaire) {
        getTableMedicamentTemporaire().add(tableMedicamentTemporaire);
    }

    /**
     * @param tableMedicamentTemporaire Supprimer un médicament temporaire
     */
    public static void removeTableMedicamentTemporaire(TableMedicamentTemporaire tableMedicamentTemporaire) {
        getTableMedicamentTemporaire().remove(tableMedicamentTemporaire);
    }
}
