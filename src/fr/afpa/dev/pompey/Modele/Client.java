package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Utilitaires.Regex;

import java.util.ArrayList;
import java.util.List;

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

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

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

    public Client(){

    };

    //GETTER ET SETTER
    public List<Client> getClients() {
        return GestionListe.getClient();
    }

    public Medecin getMedecin() {
        return new Medecin(super.getNomMedecin(), super.getPrenomMedecin());
    }

    public Medecin setMedecin(Medecin medecin) {
        return new Medecin(medecin.getNomMedecin(), medecin.getPrenomMedecin());
    }

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


    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) throws SaisieException {
        if (mutuelle == null) {
            throw new SaisieException("La mutuelle ne doit pas être vide");
        }
        this.mutuelle = mutuelle;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) throws SaisieException {
        if (ville == null || ville.isEmpty()) {
            throw new SaisieException("la ville ne doit pas être vide");
        } else if (!ville.matches(Regex.REGEXNOMPRENOM)) {
            throw new SaisieException("la ville ne corresponds pas.");
        }
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) throws SaisieException {
        if (codePostal == null || codePostal.isEmpty()) {
            throw new SaisieException("le code postal ne doit pas vide.");
        } else if (!codePostal.matches(Regex.REGEXCODEPOSTAL)) {
            throw new SaisieException("le code postal ne corresponds pas");
        }
        this.codePostal = codePostal;
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

    public String getDateNaissance() {
        return dateNaissance;
    }

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

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
