package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Utilitaires.Regex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Client est le modèle du client
 */
public class Client<T> implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private String numeroSecuClient;
    private String dateNaissance;
    private Coordonnees coordonnees;
    private Adresses adresses;
    private Mutuelle mutuelle;
    private Medecin medecin;

    //CONSTRUCTEURS
    public Client(String nom, String prenom, String numeroSecuClient, String dateNaissance,
                  Medecin medecin, Coordonnees coordonnees, Adresses adresses, Mutuelle mutuelle) throws SaisieException {
        setNom(nom);
        setPrenom(prenom);
        setNumeroSecuClient(numeroSecuClient);
        setDateNaissance(dateNaissance);
        setMedecin(new Medecin(medecin.getId()));
        setCoordonnees(new Coordonnees(coordonnees.getId()));
        setAdresses(new Adresses(adresses.getId()));
        setMutuelle(new Mutuelle(mutuelle.getId()));
    }

    public Client(int id){
        setId(id);
    }

    public Client(){

    }

    //GETTER ET SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir le nom
     *
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de définir le nom
     *
     * @param nom Le nom
     */
    public void setNom(String nom) throws SaisieException {
        if (nom == null || nom.isEmpty()) {
            throw new SaisieException("Le nom ne doit pas être vide");
        } else if (!nom.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le nom ne corresponds pas");
        }
        this.nom = nom;
    }

    /**
     * Permet d'obtenir le prénom
     *
     * @return Le prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Permet de définir le prénom
     *
     * @param prenom Le prénom
     */
    public void setPrenom(String prenom) throws SaisieException {
        if (prenom == null || prenom.isEmpty()) {
            throw new SaisieException("Le prénom ne doit pas être vide");
        }else if (!prenom.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("Le prenom ne corresponds pas");
        }
        this.prenom = prenom;
    }

    /**
     * Permet d'obtenir le numéro de sécurité sociale du client
     *
     * @return Le numéro de sécurité sociale du client
     */
    public String getNumeroSecuClient() {
        return numeroSecuClient;
    }

    /**
     * Permet de définir le numéro de sécurité sociale du client
     *
     * @param numeroSecuClient Le numéro de sécurité sociale du client
     */
    public void setNumeroSecuClient(String numeroSecuClient) throws SaisieException {
        if(numeroSecuClient == null || numeroSecuClient.isEmpty()) {
            throw new SaisieException("Le numero de la sécurité sociale ne corresponds pas");
        } else if (!numeroSecuClient.matches(Regex.REGEXNUMSECU)) {
            throw new SaisieException("Le numero de la sécurité sociale ne corresponds pas");
        }
        this.numeroSecuClient = numeroSecuClient;
    }

    /**
     * Permet d'obtenir la date de naissance
     *
     * @return La date de naissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Permet de définir la date de naissance
     *
     * @param dateNaissance La date de naissance
     */
    public void setDateNaissance(String dateNaissance) throws SaisieException {
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

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Adresses getAdresses() {
        return adresses;
    }

    public void setAdresses(Adresses adresses) {
        this.adresses = adresses;
    }

    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) {
        this.mutuelle = mutuelle;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }



    /**
     * Permet d'obtenir le nom complet
     *
     * @return Le nom complet
     */
    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
