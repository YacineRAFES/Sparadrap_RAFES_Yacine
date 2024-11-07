package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;

public class Ordonnances {
    private int id;
    private LocalDate date;
    private Medecin medecin;
    private Client client;
    //CONSTRUCTEURS

    public Ordonnances() {
    }

    public Ordonnances(int id, LocalDate date, Client client, Medecin medecin) throws SaisieException {
        setId(id);
        setDate(date);
        setClient(new Client(client.getId()));
        setMedecin(medecin);
    }

    //GETTER ET SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }




}
