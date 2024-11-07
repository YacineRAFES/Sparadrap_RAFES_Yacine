package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Utilitaires.Regex;

import java.time.LocalDate;

public class Medicament {
    private int id;
    private String nom;
    private String miseEnService;
    private int quantite;
    private String prix;
    private Categorie categorie;

    //CONSTRUCTEURS
    public Medicament(){

    }

    public Medicament(int id){
        setId(id);
    }

    public Medicament(String nom, String miseEnService, int quantite, String prix, Categorie categorie) throws SaisieException {
        setNom(nom);
        setMiseEnService(miseEnService);
        setQuantite(quantite);
        setPrix(prix);
        setCategorie(new Categorie(categorie.getId()));
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

        this.miseEnService = miseEnService;

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
