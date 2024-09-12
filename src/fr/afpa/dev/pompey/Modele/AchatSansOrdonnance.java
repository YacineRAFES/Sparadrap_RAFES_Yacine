package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;
import java.util.Map;

public class AchatSansOrdonnance {
    private Client client;
    private LocalDate date;
    private String[][] listeMedicament;
    private double prixTotal;

    //CONSTRUCTEURS
    public AchatSansOrdonnance() {

    }

    public AchatSansOrdonnance(Client client, LocalDate date, String[][] listeMedicament, double prixTotal) {
        this.client = client;
        this.date = date;
        this.listeMedicament = listeMedicament;
        this.prixTotal = prixTotal;
    }

    //GETTER ET SETTER
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) throws SaisieException {
        if (client == null) {
            throw new SaisieException("Le client ne doit pas être vide");
        }
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

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

    public String[][] getListeMedicament() {
        return listeMedicament;
    }

    public void setListeMedicament(String[][] listeMedicament) throws SaisieException {
        if (listeMedicament == null || listeMedicament.length == 0) {
            throw new SaisieException("La liste des médicaments ne doit pas être vide");
        }
        this.listeMedicament = listeMedicament;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) throws SaisieException {
        if(prixTotal < 0.0 || prixTotal == 0.0){
            throw new SaisieException("Le prix total ne peut pas être négatif ou vide");
        }
        this.prixTotal = prixTotal;
    }

}
