package fr.afpa.dev.pompey.Modele;

import java.time.LocalDate;
import java.util.Map;

public class AchatSansOrdonnance {
    private Client client;
    private LocalDate date;
    private Map<String, Integer> listeMedicament;

    //CONSTRUCTEURS
    public AchatSansOrdonnance() {

    }

    public AchatSansOrdonnance(Client client, LocalDate date, Map<String, Integer> listeMedicament) {
        this.client = client;
        this.date = date;
        this.listeMedicament = listeMedicament;
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

    public Map<String, Integer> getListeMedicament() {
        return listeMedicament;
    }

    public void setListeMedicament(Map<String, Integer> listeMedicament) {
        this.listeMedicament = listeMedicament;
    }

}
