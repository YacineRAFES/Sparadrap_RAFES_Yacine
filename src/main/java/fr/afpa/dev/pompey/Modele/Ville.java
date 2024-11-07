package fr.afpa.dev.pompey.Modele;

public class Ville extends Region {
    private int id;
    private String nom;
    private int cp;
    private Region region;

    public Ville(int id, String nom, int cp, int idRegion) {
        super(idRegion);
        setId(id);
        setNom(nom);
        setCp(cp);
    }

    public Ville(int id){
        setId(id);
    }

    public Ville(){

    }

    public Ville(String nom){
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

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
