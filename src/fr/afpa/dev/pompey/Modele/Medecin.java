package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Utilitaires.Regex;

import java.time.LocalDate;

public class Medecin {
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;
    private String numAgreement;
    private Client client;
    private String specialite;

    //CONSTRUCTEURS


    //GETTER ET SETTER
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) throws SaisieException {
        if (adresse == null || adresse.isEmpty()) {
            throw new SaisieException("L'adresse ne doit pas être vide");
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
        } else if (email.matches(Regex.REGEXEMAIL)) {
            throw new SaisieException("l'email ne corresponds pas");
        }
        this.email = email;
    }

    public String getNumAgreement() {
        return numAgreement;
    }

    public void setNumAgreement(String numAgreement) {
        this.numAgreement = numAgreement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) throws SaisieException {
        if (client == null) {
            throw new SaisieException("le nom du client ne doit pas être vide");
        }
        this.client = client;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) throws SaisieException {
        if (specialite == null || specialite.isEmpty()) {
            throw new SaisieException("la specialité ne doit pas être vide.");
        } else if (!specialite.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("le nom de spécialité ne corresponds pas");
        }
        this.specialite = specialite;
    }
}
