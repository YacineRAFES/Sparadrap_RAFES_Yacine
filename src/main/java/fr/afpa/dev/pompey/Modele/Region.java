package fr.afpa.dev.pompey.Modele;

import java.io.Serializable;

public class Region<T> implements Serializable {
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
        if(nom == null || nom.isEmpty()){
            throw new IllegalArgumentException("Le nom de la région ne doit pas être vide");
        }
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom; // ou tout autre champ descriptif
    }
}
