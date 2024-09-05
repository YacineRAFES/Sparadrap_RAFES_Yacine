package fr.afpa.dev.pompey.Modele;

public class TableMedicamentTemporaire {
    private String nom;
    private String prix;
    private int quantite;

    public TableMedicamentTemporaire() {

    }

    public TableMedicamentTemporaire(String nom) {
        this.nom = nom;
    }

    public TableMedicamentTemporaire(Medicament medicament) {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
