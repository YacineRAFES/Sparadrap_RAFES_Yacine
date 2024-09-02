package fr.afpa.dev.pompey.Modele;

import java.time.LocalDate;

public class Medecin {
    private String nom;
    private String prenom;
    private String adresse;
    private int codePostal;
    private String ville;
    private int telephone;
    private String email;
    private int numAgreement;
    private Client client;
    private String specialite;

    //CONSTRUCTEURS


    //GETTER ET SETTER
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumAgreement() {
        return numAgreement;
    }

    public void setNumAgreement(int numAgreement) {
        this.numAgreement = numAgreement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
