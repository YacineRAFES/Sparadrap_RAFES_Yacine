package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

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

    public void setNom(String nom) throws SaisieException {
        if (nom == null || nom.isEmpty()) {
            throw new SaisieException("le nom de médicament ne doit pas être vide");
        }
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) throws SaisieException {
        if (prix == null || prix.isEmpty()) {
            throw new SaisieException("le prix ne doit pas être vide");
        }
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws SaisieException {
        if (quantite < 0) {
            throw new SaisieException("la quantité ne doit pas être vide");
        }
        this.quantite = quantite;
    }
}
