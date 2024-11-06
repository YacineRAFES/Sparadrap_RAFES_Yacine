package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;
import java.util.Map;

/**
 * La classe AchatSansOrdonnance est le modèle de l'achat sans ordonnance
 */
public class AchatSansOrdonnance {
    private Client client;
    private LocalDate date;
    private String[][] listeMedicament;
    private double prixTotal;

    //CONSTRUCTEURS
    /**
     * Constructeur de la classe AchatSansOrdonnance
     */
    public AchatSansOrdonnance() {

    }

    /**
     * Constructeur de la classe AchatSansOrdonnance
     *
     * @param client Le client
     * @param date La date
     * @param listeMedicament La liste des médicaments
     * @param prixTotal Le prix total
     */
    public AchatSansOrdonnance(Client client, LocalDate date, String[][] listeMedicament, double prixTotal) {
        this.client = client;
        this.date = date;
        this.listeMedicament = listeMedicament;
        this.prixTotal = prixTotal;
    }

    //GETTER ET SETTER
    /**
     * Permet d'obtenir le client
     *
     * @return Le client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Permet de définir le client
     *
     * @param client Le client
     */
    public void setClient(Client client) throws SaisieException {
        if (client == null) {
            throw new SaisieException("Le client ne doit pas être vide");
        }
        this.client = client;
    }

    /**
     * Permet d'obtenir la date
     *
     * @return La date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Permet de définir la date
     *
     * @param date La date
     */
    public void setDate(LocalDate date) throws SaisieException {
        if(date == null){
            throw new SaisieException("La date ne doit pas être vide");
        }
        if(date.isBefore(LocalDate.now())){
            throw new SaisieException("La date n'est pas valide");
        }
        if(date.isAfter(LocalDate.now())){
            throw new SaisieException("La date ne peut pas être supérieur à la date du jour");
        }
        this.date = date;
    }

    /**
     * Permet d'obtenir la liste des médicaments
     *
     * @return La liste des médicaments
     */
    public String[][] getListeMedicament() {
        return listeMedicament;
    }

    /**
     * Permet de définir la liste des médicaments
     *
     * @param listeMedicament La liste des médicaments
     */
    public void setListeMedicament(String[][] listeMedicament) throws SaisieException {
        if (listeMedicament == null || listeMedicament.length == 0) {
            throw new SaisieException("La liste des médicaments ne doit pas être vide");
        }
        this.listeMedicament = listeMedicament;
    }

    /**
     * Permet d'obtenir le prix total
     *
     * @return Le prix total
     */
    public double getPrixTotal() {
        return prixTotal;
    }


}
