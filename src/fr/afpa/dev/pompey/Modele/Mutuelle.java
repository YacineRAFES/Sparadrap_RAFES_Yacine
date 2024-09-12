package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Utilitaires.Regex;

public class Mutuelle {
    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;
    private String departement;
    private String tauxDePriseEnCharge;

    //CONSTRUCTEURS
    public Mutuelle(){

    }
    public Mutuelle(String nom){
        this.nom = nom;
    }

    //GETTER ET SETTER
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws SaisieException {
        if(nom == null || nom.isEmpty()){
            throw new SaisieException("le nom de la mutuelle ne doit pas être vide.");
        }   else if (!nom.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le nom de la mutuelle ne corresponds pas");
        }
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) throws SaisieException {
        if(adresse == null || adresse.isEmpty()){
            throw new SaisieException("l'adresse ne doit pas être vide");
        }
        this.adresse = adresse;
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
            throw new SaisieException("Le numéro de téléphone ne corresponds pas");
        } else if (!telephone.matches(Regex.REGEXNUMTEL)) {
            throw new SaisieException("Le numéro de téléphone ne corresponds pas");
        }
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws SaisieException {
        if (email == null || email.isEmpty()) {
            throw new SaisieException("L'email ne corresponds pas");
        } else if (!email.matches(Regex.REGEXEMAIL)) {
            throw new SaisieException("L'email ne corresponds pas");
        }
        this.email = email;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) throws SaisieException {
        if(departement == null || departement.isEmpty()){
            throw new SaisieException("le departement ne doit pas être vide.");
        } else if (!departement.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("le département ne corresponds pas.");
        }
        this.departement = departement;
    }

    public String getTauxDePriseEnCharge() {
        return tauxDePriseEnCharge;
    }

    public void setTauxDePriseEnCharge(String tauxDePriseEnCharge) throws SaisieException {
        if(tauxDePriseEnCharge == null || tauxDePriseEnCharge.isEmpty()){
            throw new SaisieException("le taux de prise en charge ne doit pas être vide.");
        }
        this.tauxDePriseEnCharge = tauxDePriseEnCharge;
    }
}
