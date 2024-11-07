package fr.afpa.dev.pompey.Modele;

import java.io.Serializable;

public class Commande implements Serializable {
    private Medicament medicament;
    private AchatDirect achatDirect;
    private int quantite;

    public Commande(Medicament medicament, AchatDirect achatDirect) {
        setMedicament(medicament);
        setAchatDirect(achatDirect);
        setQuantite(quantite);
    }

    public AchatDirect getAchatDirect() {
        return achatDirect;
    }

    public void setAchatDirect(AchatDirect achatDirect) {
        this.achatDirect = achatDirect;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
