package fr.afpa.dev.pompey.Modele;

public class Mutuelle {
    private String nom;
    private String adresse;
    private int codePostal;
    private String ville;
    private String telephone;
    private String email;
    private String departement;
    private int tauxDePriseEnCharge;

    //CONSTRUCTEURS


    //GETTER ET SETTER
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public int getTauxDePriseEnCharge() {
        return tauxDePriseEnCharge;
    }

    public void setTauxDePriseEnCharge(int tauxDePriseEnCharge) {
        this.tauxDePriseEnCharge = tauxDePriseEnCharge;
    }
}
