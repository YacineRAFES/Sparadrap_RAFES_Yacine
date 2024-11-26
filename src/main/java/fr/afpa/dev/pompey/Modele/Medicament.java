package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Utilitaires.Regex;

import java.io.Serializable;
import java.util.Date;

public class Medicament<T> implements Serializable {
    private int id;
    private String nom;
    private Date miseEnService;
    private int quantite;
    private double prix;
    private Categorie categorie;

    //CONSTRUCTEURS
    public Medicament(){

    }

    public Medicament(int id){
        setId(id);
    }

    public Medicament(String nom) throws SaisieException {
        setNom(nom);
    }

    public Medicament(String nom, Date miseEnService, int quantite, double prix, int categorie) throws SaisieException {
        setNom(nom);
        setMiseEnService(miseEnService);
        setQuantite(quantite);
        setPrix(prix);
        setCategorie(new Categorie(categorie));
    }
    public Medicament(String nom, double prix){
        this.nom = nom;
        this.prix = prix;
    }

    public Medicament(String nom, Date miseEnService, double prix) throws SaisieException {
        setNom(nom);
        setMiseEnService(miseEnService);
        setPrix(prix);
    }

    //GETTER ET SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws SaisieException {
        if(nom == null || nom.isEmpty()){
            throw new SaisieException("le nom de médicament ne doit pas être vide");
        } else if (!nom.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("le nom du médicament ne corresponds pas.");
        }
        this.nom = nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }


    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) throws SaisieException {
        if(prix < 0){
            throw new SaisieException("Le prix ne peut pas être négatif");
        }
        this.prix = prix;
    }

    public java.sql.Date getMiseEnService() {
        return (java.sql.Date) miseEnService;
    }

    public void setMiseEnService(Date miseEnService) throws SaisieException {
        this.miseEnService = miseEnService;

    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws SaisieException {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
