package fr.afpa.dev.pompey.Modele;

// Classe TypeAchat
public class TypeAchat {
    private int id;
    private String label;

    public TypeAchat(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}