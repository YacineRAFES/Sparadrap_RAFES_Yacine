package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;
import java.util.Map;

public class Ordonnance {
    private LocalDate date;
    private Map<String, Integer> listeMedicament;
    private Client client;
    private Medecin medecin;

    //CONSTRUCTEURS
    public Ordonnance(LocalDate date, Map<String, Integer> listeMedicament, Client client, Medecin medecin) {
        this.date = date;
        this.listeMedicament = listeMedicament;
        this.client = client;
        this.medecin = medecin;

    }

    //GETTER ET SETTER

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws SaisieException {
        if(date == null || date.isBefore(LocalDate.now())){
            throw new SaisieException("La date n'est pas valide");
        }
        this.date = date;
    }

    public Map<String, Integer> getListeMedicament() {
        return listeMedicament;
    }

    public void setListeMedicament(Map<String, Integer> listeMedicament) {
        this.listeMedicament = listeMedicament;
    }

}
