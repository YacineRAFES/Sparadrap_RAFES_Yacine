package fr.afpa.dev.pompey.Modele;

public class Demande {
    private int quantite;
    private Medicament medicament;
    private Ordonnances ordonnances;

    public Demande(int quantite, Medicament medicament, Ordonnances ordonnances) {
        setQuantite(quantite);
        setMedicament(medicament);
        setOrdonnances(ordonnances);
    }

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
