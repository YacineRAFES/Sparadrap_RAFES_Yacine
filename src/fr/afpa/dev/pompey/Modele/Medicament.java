package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Utilitaires.Regex;

import java.time.LocalDate;

public class Medicament {
    private String nom;
    private String categorie;
    private String prix;
    private String miseEnService;
    private int quantite;

    //CONSTRUCTEURS
    public Medicament(){

    }

    public Medicament(String nom){
        this.nom = nom;
    }

    public Medicament(String nom, String categorie, String prix, String miseEnService, int quantite){
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.miseEnService = miseEnService;
        this.quantite = quantite;
    }

    //GETTER ET SETTER
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) throws SaisieException {
        if(categorie == null || categorie.isEmpty()){
            throw new SaisieException("le nom de catégorie ne doit pas être vide");
        }else if (categorie.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("le nom de catégorie ne corresponds pas.");
        }
        this.categorie = categorie;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) throws SaisieException {
        if(!prix.matches(Regex.REGEXPRIX)){
            throw new SaisieException("le prix ne corresponds pas.");
        }
        if(prix == null || prix.isEmpty()){
            throw new SaisieException("Le prix ne doit pas être vide");
        }
        this.prix = prix;
    }

    public String getMiseEnService() {
        return miseEnService;
    }

    public void setMiseEnService(String miseEnService) throws SaisieException {
        if(!miseEnService.matches(Regex.REGEXDATE)) {
            throw new SaisieException("la date de mise en service ne corresponds pas.");
        }
        if(LocalDate.parse(miseEnService).isAfter(LocalDate.now())){
            throw new SaisieException("La date de mise en service ne peut pas être supérieure à la date actuelle");
        }
        if(miseEnService == null || miseEnService.isEmpty()){
            throw new SaisieException("La mise en service ne doit pas être vide");
        }

    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws SaisieException {
        if(quantite <= 0){
            throw new SaisieException("La quantité ne peut pas être négative");
        }
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
