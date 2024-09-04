package fr.afpa.dev.pompey.Modele;

import java.time.LocalDate;

public class AchatSansOrdonnance {
    private Client client;
    private LocalDate date;
    private String[] listeMedicament;

    //CONSTRUCTEURS
    public AchatSansOrdonnance() {

    }

    public AchatSansOrdonnance(Client client, LocalDate date, String[] listeMedicament) {

    }

    //GETTER ET SETTER
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String[] getListeMedicament() {
        return listeMedicament;
    }

    public void setListeMedicament(String[] listeMedicament) {
        this.listeMedicament = listeMedicament;
    }
}
