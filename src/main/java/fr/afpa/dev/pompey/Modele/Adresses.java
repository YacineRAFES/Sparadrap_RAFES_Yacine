package fr.afpa.dev.pompey.Modele;

import java.io.Serializable;

public class Adresses<T> extends Ville implements Serializable {
    private int id;
    private String rue;
    private Ville ville;
    private int codePostal;

    public Adresses() {

    }

    public Adresses(int id) {
        setId(id);
    }

    public Adresses(int id, String rue, int idVille) {
        super(idVille);
        setRue(rue);
        setId(id);
    }

    public Adresses(String rue, int idVille) {
        super(idVille);
        setRue(rue);
    }

    public Adresses(String rue){
        setRue(rue);
    }

    public Adresses(String rue, String ville) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public int getCodePostal() {
        return codePostal;
    }
}
