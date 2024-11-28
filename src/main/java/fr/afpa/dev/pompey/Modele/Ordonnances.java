package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Ordonnances<T> implements Serializable {
    private int id;
    private Date date;
    private Medecin medecin;
    private Client client;

    //CONSTRUCTEURS

    public Ordonnances() {
    }

    public Ordonnances(Date date, Client client, Medecin medecin) throws SaisieException {
        setDate(date);
        setClient(client);
        setMedecin(medecin);
    }

    public Ordonnances(int id, Date date, int client, int medecin) throws SaisieException {
        setId(id);
        setDate(date);
        setClient(new Client(client));
        setMedecin(new Medecin(medecin));
    }

    public Ordonnances(int ordonnances) {
        setId(ordonnances);
    }

    //GETTER ET SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) throws SaisieException {
        if(date == null){
            throw new SaisieException("La date ne doit pas être vide");
        }
        if(date.toString().isEmpty()){
            throw new SaisieException("La date ne doit pas être vide");
        }
        LocalDate localDate = date.toLocalDate();
        if (localDate.isAfter(LocalDate.now())){
            throw new SaisieException("La date ne doit pas être dans le futur");
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
