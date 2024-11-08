package fr.afpa.dev.pompey.Modele;

import java.io.Serializable;

public class Demande<T> implements Serializable {
    private int quantite;
    private Medicament medicament;
    private Ordonnances ordonnances;

    public Demande() {
    }

    public Demande(int quantite, Medicament medicament, Ordonnances ordonnances) {
        setQuantite(quantite);
        setMedicament(medicament);
        setOrdonnances(ordonnances);
    }

    //GETTER ET SETTER



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public Ordonnances getOrdonnances() {
        return ordonnances;
    }

    public void setOrdonnances(Ordonnances ordonnances) {
        this.ordonnances = ordonnances;
    }
}
