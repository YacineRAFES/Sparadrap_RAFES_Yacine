package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Utilitaires.Regex;

import java.io.Serializable;
import java.util.List;

public class Medecin<T> implements Serializable {
    private int id;
    private String numAgreement;
    private String specialite;
    private String nom;
    private String prenom;
    private Adresses adresses;
    private Coordonnees coordonnees;

    //CONSTRUCTEURS
    public Medecin(){

    }

    public Medecin(int id){
        setId(id);
    }


    public Medecin(String nom, String prenom, String numAgreement, String specialite,
                   int adresses, int coordonnees) throws SaisieException {
        setNom(nom);
        setPrenom(prenom);
        setNumAgreement(numAgreement);
        setSpecialite(specialite);
        setAdresses(new Adresses(adresses));
        setCoordonnees(new Coordonnees(coordonnees));
    }

    public Medecin(int id, String nom, String prenom, String numAgreement, String specialite,
                   int adresses, int coordonnees) throws SaisieException {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setNumAgreement(numAgreement);
        setSpecialite(specialite);
        setAdresses(new Adresses(adresses));
        setCoordonnees(new Coordonnees(coordonnees));
    }

    public Medecin(int id, String nom, String prenom, String numAgreement, String specialite) throws SaisieException {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setNumAgreement(numAgreement);
        setSpecialite(specialite);
    }

    public Medecin(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }

    public Medecin(Adresses adresses){
        setAdresses(new Adresses(adresses.getId()));
    }

    public Medecin(Coordonnees coordonnees) throws SaisieException {
        setCoordonnees(new Coordonnees(coordonnees.getId()));
    }


    //GETTER ET SETTER


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws SaisieException {
        if (nom == null || nom.isEmpty()) {
            throw new SaisieException("Le nom ne doit pas être vide");
        } else if (!nom.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le nom ne corresponds pas");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws SaisieException {
        if (prenom == null || prenom.isEmpty()) {
            throw new SaisieException("Le prénom ne doit pas être vide");
        }else if (!prenom.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le prenom ne corresponds pas");
        }
        this.prenom = prenom;
    }

    public String getNumAgreement() {
        return numAgreement;
    }

    public void setNumAgreement(String numAgreement) throws SaisieException {
        this.numAgreement = numAgreement;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) throws SaisieException {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return getNom() + " " + getPrenom();
    }


}
