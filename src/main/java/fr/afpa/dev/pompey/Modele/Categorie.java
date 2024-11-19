package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.io.Serializable;

public class Categorie<T> implements Serializable {
    private int id;
    private String nom;


    public Categorie(){

    }

    public Categorie(int id){
        setId(id);
    }


    public Categorie(String nom) throws SaisieException {
        setNom(nom);
    }

    public Categorie(int id, String nom) throws SaisieException {
        setId(id);
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

    /**
     * Permet de définir le nom
     * @param nom Le nom
     * @throws SaisieException Si le nom est vide
     */
    public void setNom(String nom) throws SaisieException {
        if(nom == null || nom.isEmpty()){
            throw new SaisieException("Le nom ne doit pas être vide");
        }
        this.nom = nom;
    }
}
