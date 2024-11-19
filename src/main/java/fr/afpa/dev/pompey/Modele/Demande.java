package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.io.Serializable;

public class Demande<T> implements Serializable {
    private int quantite;
    private Medicament medicament;
    private Ordonnances ordonnances;

    public Demande() {
    }

    public Demande(int quantite, Medicament medicament, Ordonnances ordonnances) throws SaisieException {
        setQuantite(quantite);
        setMedicament(medicament);
        setOrdonnances(ordonnances);
    }

    //GETTER ET SETTER



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) throws SaisieException {
        if(medicament == null){
            throw new SaisieException("Le médicament ne doit pas être vide");
        }
        this.medicament = medicament;
    }

    public Ordonnances getOrdonnances() {
        return ordonnances;
    }

    public void setOrdonnances(Ordonnances ordonnances) throws SaisieException {
        if(ordonnances == null){
            throw new SaisieException("L'ordonnance ne doit pas être vide");
        }
        this.ordonnances = ordonnances;
    }
}
