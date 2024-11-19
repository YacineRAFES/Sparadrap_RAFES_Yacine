package fr.afpa.dev.pompey.Modele;

import java.io.Serializable;

public class Commande<T> implements Serializable {
    private Medicament medicament;
    private AchatDirect achatDirect;
    private int quantite;

    public Commande() {
    }

    public Commande(Medicament medicament, AchatDirect achatDirect, int quantite) {
        setMedicament(medicament);
        setAchatDirect(achatDirect);
        setQuantite(quantite);
    }
    
    public Commande(Medicament medicament, AchatDirect achatDirect) {
        setMedicament(medicament);
        setAchatDirect(achatDirect);
    }

    public AchatDirect getAchatDirect() {
        return achatDirect;
    }

    public void setAchatDirect(AchatDirect achatDirect) {
        if(achatDirect == null){
            throw new IllegalArgumentException("L'achat direct ne doit pas être vide");
        }
        this.achatDirect = achatDirect;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        if(medicament == null){
            throw new IllegalArgumentException("Le médicament ne doit pas être vide");
        }
        this.medicament = medicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        if(quantite < 0){
            throw new IllegalArgumentException("La quantité doit être supérieure à 0");
        }
        this.quantite = quantite;
    }
}
