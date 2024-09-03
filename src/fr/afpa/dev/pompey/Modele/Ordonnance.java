package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;

public class Ordonnance {
    private LocalDate date;
    private String nomMedecinTraitant;
    private String nomPatient;
    private String[] listeMedicament;
    private Client client;
    private Medecin medecin;

    //CONSTRUCTEURS


    //GETTER ET SETTER

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws SaisieException {
        if(date == null || date.isBefore(LocalDate.now())){
            throw new SaisieException("La date n'est pas valide");
        }
        this.date = date;
    }

    public String getNomMedecinTraitant() {
        return nomMedecinTraitant;
    }

    public void setNomMedecinTraitant(String nomMedecinTraitant) throws SaisieException {
        if(nomMedecinTraitant == null || nomMedecinTraitant.isEmpty()){
            throw new SaisieException("Le nom de médecin ne doit pas être vide.");
        }
        this.nomMedecinTraitant = nomMedecinTraitant;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) throws SaisieException {
        if(nomPatient == null || nomPatient.isEmpty()){
            throw new SaisieException("Le nom du patient ne doit pas être vide.");
        }
        this.nomPatient = nomPatient;
    }

    public String[] getListeMedicament() {
        return listeMedicament;
    }

    public void setListeMedicament(String[] listeMedicament) {
        this.listeMedicament = listeMedicament;
    }

}
