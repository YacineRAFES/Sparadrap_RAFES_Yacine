package fr.afpa.dev.pompey.Modele;

import java.time.LocalDate;

public class Ordonnance {
    private LocalDate date;
    private String nomMedecinTraitant;
    private String nomPatient;
    private String[] listeMedicament;
    private String nomSpecialiste;

    //CONSTRUCTEURS


    //GETTER ET SETTER
    //TODO A FAIRE
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNomMedecinTraitant() {
        return nomMedecinTraitant;
    }

    public void setNomMedecinTraitant(String nomMedecinTraitant) {
        this.nomMedecinTraitant = nomMedecinTraitant;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String[] getListeMedicament() {
        return listeMedicament;
    }

    public void setListeMedicament(String[] listeMedicament) {
        this.listeMedicament = listeMedicament;
    }

    public String getNomSpecialiste() {
        return nomSpecialiste;
    }

    public void setNomSpecialiste(String nomSpecialiste) {
        this.nomSpecialiste = nomSpecialiste;
    }
}
