package fr.afpa.dev.pompey.Modele.Utilitaires;

import fr.afpa.dev.pompey.Modele.*;

import static fr.afpa.dev.pompey.Modele.Utilitaires.Generator.generateRandomDate;

public class CreateTestConstructor {
    public CreateTestConstructor() {


        Mutuelle mutuelle = new Mutuelle("MGEL la pire mutuelle");
        Medecin medecin = new Medecin("Docteur", "House");

        Client client = new Client("Pierre",
                "Jean-Lance",
                "1 rue du panier",
                "10000",
                "Auxerre",
                "0101010101",
                "pierrejeanlance@gmail.com",
                "123456781012345",
                "13/11/1999",
                mutuelle,
                medecin);

        Client client1 = new Client("Unpoint",
                "Jean-Marc",
                "1 rue de la liste",
                "10000",
                "Panier",
                "0101010101",
                "jeanmarcunpoint@gmail.com",
                "123456789012345",
                "13/11/1988",
                mutuelle,
                medecin);

        GestionListe.addClient(client);
        GestionListe.addMutuelle(mutuelle);
        GestionListe.addMedecin(medecin);


        Medicament medicament91 = new Medicament("amikacine","antibiotique", "33", "10/09/2024", 10);
        Medicament medicament92 = new Medicament("dibékacine","antibiotique", "61", "10/09/2024", 10);
        Medicament medicament93 = new Medicament("gentamicine","antibiotique", "6", "10/09/2024", 10);
        Medicament medicament94 = new Medicament("kanamycine","antibiotique", "97", "10/09/2024", 10);
        Medicament medicament95 = new Medicament("néomycine","antibiotique", "54", "10/09/2024", 10);
        GestionListe.addMedicament(medicament91);
        GestionListe.addMedicament(medicament92);
        GestionListe.addMedicament(medicament93);
        GestionListe.addMedicament(medicament94);
        GestionListe.addMedicament(medicament95);

        for (int i = 0; i < 10; i++) {
            Ordonnance ordonnance = new Ordonnance(
                    generateRandomDate(),
                    new String[][] {
                            {"amikacine", "3", "65"},
                            {"dibékacine", "2", "12"},
                            {"gentamicine", "1", "5"},
                            {"kanamycine", "1", "3"},
                            {"néomycine", "3", "7"},
                            {"gentamicine", "1", "5"},
                            {"kanamycine", "1", "3"},
                            {"néomycine", "3", "7"}},
                    client,
                    medecin,
                    1000.0);
            GestionListe.addOrdonnance(ordonnance);
        }

    }


}
