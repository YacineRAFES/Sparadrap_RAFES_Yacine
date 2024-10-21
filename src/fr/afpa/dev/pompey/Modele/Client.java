package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Utilitaires.Regex;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Client est le modèle du client
 */
public class Client extends Medecin {
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;
    private String numeroSecuClient;
    private String dateNaissance;
    private Mutuelle mutuelle;

    //CONSTRUCTEURS
    /**
     * Constructeur de la classe Client
     *
     * @param client Le client
     * @param nomMedecin Le nom du médecin
     * @param prenomMedecin Le prénom du médecin
     */
    public Client(Client client, String nomMedecin, String prenomMedecin) {
        super(nomMedecin, prenomMedecin);
        this.nom = client.getNom();
        this.prenom = client.getPrenom();
        this.adresse = client.getAdresse();
        this.codePostal = client.getCodePostal();
        this.ville = client.getVille();
        this.telephone = client.getTelephone();
        this.email = client.getEmail();
        this.numeroSecuClient = client.getNumeroSecuClient();
        this.dateNaissance = client.getDateNaissance();
        this.mutuelle = client.getMutuelle();
    }

    /**
     * Constructeur de la classe Client
     *
     * @param nom Le nom
     * @param prenom Le prénom
     */
    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Constructeur de la classe Client
     *
     * @param nom Le nom
     * @param prenom Le prénom
     * @param adresse L'adresse
     * @param codePostal Le code postal
     * @param ville La ville
     * @param telephone Le téléphone
     * @param email L'email
     * @param numeroSecuClient Le numéro de sécurité sociale du client
     * @param dateNaissance La date de naissance
     * @param mutuelle La mutuelle
     * @param nomMedecin Le nom du médecin
     * @param prenomMedecin Le prénom du médecin
     */
    public Client(String nom, String prenom, String adresse, String codePostal, String ville, String telephone, String email,
                  String numeroSecuClient, String dateNaissance, Mutuelle mutuelle, String nomMedecin, String prenomMedecin) {
        super(nomMedecin, prenomMedecin);
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
        this.numeroSecuClient = numeroSecuClient;
        this.dateNaissance = dateNaissance;
        this.mutuelle = mutuelle;
    }

    /**
     * Constructeur de la classe Client
     */
    public Client(){

    }

    //GETTER ET SETTER
    /**
     * Permet d'obtenir la liste des clients
     *
     * @return La liste des clients
     */
    public List<Client> getClients() {
        return GestionListe.getClient();
    }

    /**
     * Permet d'obtenir le médecin
     *
     * @return Le médecin
     */
    public Medecin getMedecin() {
        return new Medecin(super.getNomMedecin(), super.getPrenomMedecin());
    }

    /**
     * Permet de définir le médecin
     *
     * @param medecin Le médecin
     * @return Le médecin
     */
    public Medecin setMedecin(Medecin medecin) {
        return new Medecin(medecin.getNomMedecin(), medecin.getPrenomMedecin());
    }

    /**
     * Permet d'obtenir la liste des clients par médecin
     *
     * @param medecin Le médecin
     * @return La liste des clients par médecin
     */
    public static List<Client> getClientsParMedecin(Medecin medecin) {
        List<Client> touteslesClients = GestionListe.getClient();
        List<Client> clientsParMedecin = new ArrayList<>();

        for (Client client : touteslesClients) {
            if (client.getMedecin().getNomMedecin().equals(medecin.getNomMedecin()) &&
            client.getMedecin().getPrenomMedecin().equals(medecin.getPrenomMedecin())) {
            clientsParMedecin.add(client);
            }
        }
        return clientsParMedecin;
    }

    /**
     * Permet d'obtenir la mutuelle
     *
     * @return La mutuelle
     */
    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    /**
     * Permet de définir la mutuelle
     *
     * @param mutuelle La mutuelle
     */
    public void setMutuelle(Mutuelle mutuelle) throws SaisieException {
        if (mutuelle == null) {
            throw new SaisieException("La mutuelle ne doit pas être vide");
        }
        this.mutuelle = mutuelle;
    }

    /**
     * Permet d'obtenir l'adresse
     *
     * @return L'adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Permet de définir l'adresse
     *
     * @param adresse L'adresse
     */
    public void setAdresse(String adresse) throws SaisieException {
        if (adresse == null || adresse.isEmpty()) {
            throw new SaisieException("L'adresse ne doit pas être vide");
        }
        this.adresse = adresse;
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
     * Permet d'obtenir la ville
     *
     * @return La ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Permet de définir la ville
     *
     * @param ville La ville
     */
    public void setVille(String ville) throws SaisieException {
        if (ville == null || ville.isEmpty()) {
            throw new SaisieException("la ville ne doit pas être vide");
        } else if (!ville.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("la ville ne corresponds pas.");
        }
        this.ville = ville;
    }

    /**
     * Permet d'obtenir le code postal
     *
     * @return Le code postal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Permet de définir le code postal
     *
     * @param codePostal Le code postal
     */
    public void setCodePostal(String codePostal) throws SaisieException {
        if (codePostal == null || codePostal.isEmpty()) {
            throw new SaisieException("le code postal ne doit pas vide.");
        } else if (!codePostal.matches(Regex.REGEXCODEPOSTAL)) {
            throw new SaisieException("le code postal ne corresponds pas");
        }
        this.codePostal = codePostal;
    }

    /**
     * Permet d'obtenir le téléphone
     *
     * @return Le téléphone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Permet de définir le téléphone
     *
     * @param telephone Le téléphone
     */
    public void setTelephone(String telephone) throws SaisieException {
        if (telephone == null || telephone.isEmpty()) {
            throw new SaisieException("Le numéro de téléphone ne corresponds pas");
        } else if (!telephone.matches(Regex.REGEXNUMTEL)) {
            throw new SaisieException("Le numéro de téléphone ne corresponds pas");
        }
        this.telephone = telephone;
    }

    /**
     * Permet d'obtenir l'email
     *
     * @return L'email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Permet de définir l'email
     *
     * @param email L'email
     */
    public void setEmail(String email) throws SaisieException {
        if (email == null || email.isEmpty()) {
            throw new SaisieException("L'email ne corresponds pas");
        } else if (!email.matches(Regex.REGEXEMAIL)) {
            throw new SaisieException("L'email ne corresponds pas");
        }
        this.email = email;
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
