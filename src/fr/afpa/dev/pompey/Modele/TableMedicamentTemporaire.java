package fr.afpa.dev.pompey.Modele;

public class TableMedicamentTemporaire {
    private String nom;
    private String prix;
    private int quantite = 0;

    public TableMedicamentTemporaire() {

    }

    public TableMedicamentTemporaire(String nom) {
        this.nom = nom;
    }

    public TableMedicamentTemporaire(Medicament medicament, int quantite, String prix) {
        this.nom = medicament.getNom();
        this.quantite = quantite;
        this.prix = prix;
    }

    public TableMedicamentTemporaire(Medicament medicament) {
        this.nom = medicament.getNom();
        this.prix = medicament.getPrix();
        this.quantite = medicament.getQuantite();
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
