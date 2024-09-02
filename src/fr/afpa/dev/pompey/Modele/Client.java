package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Utilitaires.Regex;

import java.time.LocalDate;

public class Client {
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String numeroSecuClient;
    private LocalDate dateNaissance;
    private String mutuelle;
    private String medecinTraitant;
    private String specialiste;

    //CONSTRUCTEURS


    //GETTER ET SETTER
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) throws SaisieException {
        if (adresse == null || adresse.isEmpty()) {
            throw new SaisieException("L'adresse ne doit pas être vide");
        }
        this.adresse = adresse;
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

    public String getNumeroSecuClient() {
        return numeroSecuClient;
    }

    public void setNumeroSecuClient(String numeroSecuClient) throws SaisieException {
        if(numeroSecuClient == null || numeroSecuClient.isEmpty()) {
            throw new SaisieException("Le numero de la sécurité sociale ne corresponds pas");
        } else if (!numeroSecuClient.matches(Regex.REGEXNUMSECU)) {
            throw new SaisieException("Le numero de la sécurité sociale ne corresponds pas");
        }
        this.numeroSecuClient = numeroSecuClient;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) throws SaisieException {
        try{
            if (dateNaissance == null) {
                throw new SaisieException("La date de naissance ne corresponds pas");
            }else{
                this.dateNaissance = dateNaissance;
            }
        }catch (SaisieException e){
            throw new SaisieException("La date de naissance ne corresponds pas");
        }
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) throws SaisieException {
        if (mutuelle == null || mutuelle.isEmpty()) {
            throw new SaisieException("La mutuelle ne doit pas être vide");
        }
        this.mutuelle = mutuelle;
    }

    public String getMedecinTraitant() {
        return medecinTraitant;
    }

    public void setMedecinTraitant(String medecinTraitant) throws SaisieException {
        if (medecinTraitant == null || medecinTraitant.isEmpty()) {
            throw new SaisieException("Le medecin traitant ne doit pas être vide");
        } else if (medecinTraitant.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le medecin traitant ne corresponds pas");
        }
        this.medecinTraitant = medecinTraitant;
    }

    public String getSpecialiste() {
        return specialiste;
    }

    public void setSpecialiste(String specialiste) throws SaisieException {
        if(specialiste == null || specialiste.isEmpty()) {
            throw new SaisieException("La spécialite ne doit pas être vide");
        } else if (specialiste.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le specialite ne corresponds pas");
        }
        this.specialiste = specialiste;
    }
}
