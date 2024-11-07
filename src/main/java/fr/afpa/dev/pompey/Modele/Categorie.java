package fr.afpa.dev.pompey.Modele;

public class Categorie {
    private int id;
    private String nom;

    public Categorie(int id, String nom) {
        setId(id);
        setNom(nom);
    }

    public Categorie(int id){
        setId(id);
    }

    public Categorie(){

    }

    public Categorie(String nom){
        setNom(nom);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
