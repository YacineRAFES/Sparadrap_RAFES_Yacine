package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 * La classe AchatSansOrdonnance est le modèle de l'achat sans ordonnance
 */
public class AchatDirect<T> implements Serializable {
    private int id;
    private Date date;
    private Client client;

    //CONSTRUCTEURS

    public AchatDirect() {
    }

    public AchatDirect(int id){
        setId(id);
    }

    public AchatDirect(Date date, Client client) throws SaisieException {
        setDate(date);
        setClient(client);
    }
    /**
     * Constructeur de la classe AchatDirect
     */
    public AchatDirect(Date date, int id, Client client) throws SaisieException {
        setId(id);
        setDate(date);
        setClient(client);
    }


    //GETTER ET SETTER

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }
    /**
     * Permet d'obtenir la date
     *
     * @return La date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Permet de définir la date
     *
     * @param date La date
     */
    public void setDate(Date date) throws SaisieException {
        if(date == null){
            throw new SaisieException("La date ne doit pas être vide");
        }
        if(date.toLocalDate().isAfter(LocalDate.now())){
            throw new SaisieException("La date n'est pas valide");
        }
        if(date.toLocalDate().isAfter(LocalDate.now())){
            throw new SaisieException("La date ne peut pas être supérieur à la date du jour");
        }
        this.date = date;
    }



}
