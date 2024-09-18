package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Utilitaires.Regex;

import java.time.LocalDate;
import java.util.List;

public class Medecin {
    private String nom;
    private String prenom;
    private String rue;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;
    private String numAgreement;
    private String specialite;

    //CONSTRUCTEURS
    public Medecin(){

    }


    public Medecin(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }

    public Medecin(String nom, String prenom, String rue, String codePostal, String ville, String telephone, String email, String numAgreement, String specialite){
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
        this.numAgreement = numAgreement;
        this.specialite = specialite;
    }

    //GETTER ET SETTER
    public List<Ordonnance> getOrdonnances() {
        return Ordonnance.getOrdonnancesParMedecin(this);
    }

    public List<Client> getClients() {
        return Client.getClientsParMedecin(this);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws SaisieException {
        if (nom == null || nom.isEmpty()) {
            throw new SaisieException("Le nom ne doit pas être vide");
        } else if (!nom.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le nom ne corresponds pas");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws SaisieException {
        if (prenom == null || prenom.isEmpty()) {
            throw new SaisieException("Le prénom ne doit pas être vide");
        }else if (!prenom.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le prenom ne corresponds pas");
        }
        this.prenom = prenom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) throws SaisieException {
        if (rue == null || rue.isEmpty()) {
            throw new SaisieException("L'adresse ne doit pas être vide");
        }
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) throws SaisieException {
        if (codePostal == null || codePostal.isEmpty()) {
            throw new SaisieException("le code postal ne doit pas être vide");
        } else if (!codePostal.matches(Regex.REGEXCODEPOSTAL)) {
            throw new SaisieException("le code postal ne corresponds pas");
        }
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) throws SaisieException {
        if (ville == null || ville.isEmpty()) {
            throw new SaisieException("la ville ne doit pas être vide");
        } else if (!ville.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("la ville ne corresponds pas");
        }
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws SaisieException {
        if (telephone == null || telephone.isEmpty()) {
            throw new SaisieException("le numéro de téléphone ne doit pas être vide.");
        } else if (!telephone.matches(Regex.REGEXNUMTEL)) {
            throw new SaisieException("le telephone ne corresponds pas");
        }
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws SaisieException {
        if (email == null || email.isEmpty()) {
            throw new SaisieException("l'email ne doit pas être vide");
        } else if (!email.matches(Regex.REGEXEMAIL)) {
            throw new SaisieException("l'email ne corresponds pas");
        }
        this.email = email;
    }

    public String getNumAgreement() {
        return numAgreement;
    }

    public void setNumAgreement(String numAgreement) throws SaisieException {
        if(numAgreement == null || numAgreement.isEmpty()){
            throw new SaisieException("Le numéro d'agreement ne doit pas être vide");
        }
        this.numAgreement = numAgreement;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) throws SaisieException {
        if (specialite == null || specialite.isEmpty()) {
            throw new SaisieException("La specialité ne doit pas être vide.");
        } else if (!specialite.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le nom de spécialité ne corresponds pas");
        }
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return getNom() + " " + getPrenom();
    }


}
