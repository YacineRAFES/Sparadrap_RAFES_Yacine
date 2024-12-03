package fr.afpa.dev.pompey.Modele;

import java.io.Serializable;

public class Ville<T> extends Region implements Serializable {
    private int id;
    private String nom;
    private String cp;
    private Region region;

    public Ville(int id, String nom, String cp, int idRegion) {
        setId(id);
        setNom(nom);
        setCp(cp);
        setRegion(new Region(idRegion));
    }

    public Ville(int id){
        setId(id);
    }

    public Ville(){

    }

    public Ville(String nom){
        setNom(nom);
    }

    public Ville(String nom, String cp, int idRegion){
        setNom(nom);
        setCp(cp);
        setRegion(new Region(idRegion));
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

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
