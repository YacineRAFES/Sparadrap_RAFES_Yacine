package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ordonnance {
    private LocalDate date;
    private String[][] listeMedicament;
    private Client client;
    private Medecin medecin;
    private double prixTotal;

    //CONSTRUCTEURS

    public Ordonnance() {
    }

    public Ordonnance(LocalDate date, String[][] listeMedicament, Client client, Medecin medecin, double prixTotal) {
        this.date = date;
        this.listeMedicament = listeMedicament;
        this.client = client;
        this.medecin = medecin;
        this.prixTotal = prixTotal;
    }

    public static List<Ordonnance> getOrdonnancesParMedecin(Medecin medecin) {
        // Supposez que vous avez une liste d'ordonnances en mémoire ou dans une base de données
        List<Ordonnance> toutesLesOrdonnances = GestionListe.getOrdonnance();
        List<Ordonnance> ordonnancesParMedecin = new ArrayList<>();

        for (Ordonnance ordonnance : toutesLesOrdonnances) {
            if (ordonnance.getMedecin().equals(medecin)) {
                ordonnancesParMedecin.add(ordonnance);
            }
        }

        return ordonnancesParMedecin;
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

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) throws SaisieException {
        if (medecin == null) {
            throw new SaisieException("Le medecin ne doit pas être vide");
        }
        this.medecin = medecin;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws SaisieException {
        if(date == null || date.isBefore(LocalDate.now())){
            throw new SaisieException("La date n'est pas valide");
        }
        if(date.isBefore(LocalDate.now())){
            throw new SaisieException("La date ne doit pas être vide");
        }
        if(date.isAfter(LocalDate.now())){
            throw new SaisieException("La date ne doit pas être supérieure à la date du jour");
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
        if (prixTotal < 0 || prixTotal == 0) {
            throw new SaisieException("Le prix total ne peut pas être négatif");
        }
        this.prixTotal = prixTotal;
    }

}
