package fr.afpa.dev.pompey.Modele;

public class Region{
    private int id;
    private String nom;

    public Region() {

    }

    public Region(int id, String nom) {
        setId(id);
        setNom(nom);
    }

    public Region(String nom) {
        setNom(nom);
    }

    public Region(int id) {
        setId(id);
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
