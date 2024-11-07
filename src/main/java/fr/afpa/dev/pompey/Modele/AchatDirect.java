package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;

/**
 * La classe AchatSansOrdonnance est le modèle de l'achat sans ordonnance
 */
public class AchatDirect extends Client {
    private int id;
    private LocalDate date;

    //CONSTRUCTEURS
    /**
     * Constructeur de la classe AchatSansOrdonnance
     */
    public AchatDirect(LocalDate date, int id) throws SaisieException {
        super(id);
        setDate(date);
    }


    //GETTER ET SETTER



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



}
