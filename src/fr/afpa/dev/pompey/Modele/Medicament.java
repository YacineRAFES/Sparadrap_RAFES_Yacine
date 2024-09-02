package fr.afpa.dev.pompey.Modele;

import java.time.LocalDate;

public class Medicament {
    private String nom;
    private String categorie;
    private int prix;
    private LocalDate miseEnService;
    private int quantite;

    //CONSTRUCTEURS


    //GETTER ET SETTER
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public LocalDate getMiseEnService() {
        return miseEnService;
    }

    public void setMiseEnService(LocalDate miseEnService) {
        this.miseEnService = miseEnService;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
