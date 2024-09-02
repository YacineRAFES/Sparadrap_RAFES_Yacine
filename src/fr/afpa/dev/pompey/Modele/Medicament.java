package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Utilitaires.Regex;

import java.time.LocalDate;

public class Medicament {
    private String nom;
    private String categorie;
    private String prix;
    private LocalDate miseEnService;
    private int quantite;

    //CONSTRUCTEURS


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
        this.prix = prix;
    }

    public LocalDate getMiseEnService() {
        return miseEnService;
    }

    public void setMiseEnService(LocalDate miseEnService) throws SaisieException {
        try{
            if (miseEnService == null) {
                throw new SaisieException("La mise en service ne doit pas être vide");
            }else{
                this.miseEnService = miseEnService;
            }
        }catch (SaisieException e){
            throw new SaisieException("La date de naissance ne corresponds pas");
        }

    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
