package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Utilitaires.Regex;

import java.io.Serializable;

public class Mutuelle<T> implements Serializable {
    private int id;
    private String nom;
    private int tauxDePriseEnCharge;
    private Adresses adresses;
    private Coordonnees coordonnees;

    //CONSTRUCTEURS
    public Mutuelle(){

    }

    public Mutuelle(int id){
        setId(id);
    }

    public Mutuelle(String nom, int tauxDePriseEnCharge) throws SaisieException {
        setNom(nom);
        setTauxDePriseEnCharge(tauxDePriseEnCharge);
    }

    public Mutuelle(String nom) throws SaisieException {
        setNom(nom);
    }

    public Mutuelle(int idAdresses, int idCoordonnees) throws SaisieException {
        setAdresses(new Adresses(idAdresses));
        setCoordonnees(new Coordonnees(idCoordonnees));
    }

    public Mutuelle(Adresses adresses){
        setAdresses(new Adresses(adresses.getId()));
    }

    public Mutuelle(Coordonnees coordonnees) throws SaisieException {
        setCoordonnees(new Coordonnees(coordonnees.getId()));
    }

    public Mutuelle(String nom, int tauxDePriseEnCharge, int adresses, int coordonnees) throws SaisieException {
        setNom(nom);
        setTauxDePriseEnCharge(tauxDePriseEnCharge);
        setCoordonnees(new Coordonnees(coordonnees));
        setAdresses(new Adresses(adresses));
    }

    //GETTER ET SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws SaisieException {
        this.nom = nom;
    }

    public int getTauxDePriseEnCharge() {
        return tauxDePriseEnCharge;
    }

    public void setTauxDePriseEnCharge(int tauxDePriseEnCharge) throws SaisieException {
        this.tauxDePriseEnCharge = tauxDePriseEnCharge;
    }

    public Adresses getAdresses() {
        return adresses;
    }

    public void setAdresses(Adresses adresses) {
        this.adresses = adresses;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }



    @Override
    public String toString() {
        return nom;
    }
}
