package fr.afpa.dev.pompey.Modele;

import java.io.Serializable;

public class Adresses<T> implements Serializable {
    private int id;
    private String rue;
    private Ville ville;

    //CONSTRUCTEURS

    public Adresses() {

    }

    public Adresses(int id) {
        setId(id);
    }

    public Adresses(int id, String rue, int idVille) {
        setId(id);
        setRue(rue);
        setVille(new Ville(idVille));
    }

    public Adresses(String rue, int idVille) {
        setRue(rue);
        setVille(new Ville(idVille));
    }

    //GETTER ET SETTER

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
        return new Ville<>();
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

}
