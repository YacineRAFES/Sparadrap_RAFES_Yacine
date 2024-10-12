package fr.afpa.dev.pompey.Main;

import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Vue.ControllerAccueil;
import fr.afpa.dev.pompey.Exception.SaisieException;

public class Sparadrap {
    //TODO enlever les exceptions inutiles, sauf les saisies
    //TODO Faire les placeholders pour les champs de saisie
    //TODO Remplacer les alertes de fenetres en showlabel

    // Main method
    public static void main(String[] args) throws SaisieException {
        Sparadrap sparadrap = new Sparadrap();
        sparadrap.start();

        //Tests :
        Medecin medecin = new Medecin("Dr. Dupont", "Jean", "1 rue de la paix", "54000", "Nancy", "0372000000", "dupont@gmail.com", "123456", "Cardiologue");
        Medecin medecin1 = new Medecin("Dr. Durand", "Pierre", "10 rue des champs", "75000", "Paris", "0172000000", "durand@mail.com", "654321", "Dentiste");
        Medecin medecin2 = new Medecin("Dr. Martin", "Paul", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "martin@gmail.com", "789456", "Ophtalmologue");

        Mutuelle mutuelle1 = new Mutuelle("MGEL", "1 rue de la paix", "54000", "Nancy", "0372000000", "email@mail.com", "54", "80");
        Mutuelle mutuelle2 = new Mutuelle("MGEN", "10 rue des champs", "75000", "Paris", "0172000000", "contact@mgen.fr", "75", "75");
        Mutuelle mutuelle3 = new Mutuelle("Harmonie Mutuelle", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "info@harmonie.fr", "69", "70");
        Mutuelle mutuelle4 = new Mutuelle("MMA", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "mma@gmail.com", "69", "70");
        Mutuelle mutuelle5 = new Mutuelle("AXA", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "axa@gmail.com", "69", "70");

        Medicament medicament = new Medicament("Doliprane", "Analgésiques", "2.5", "30/09/2024", 100);
        Medicament medicament1 = new Medicament("Nurofen", "Anti-inflammatoires", "3.5", "30/09/2024", 50);
        Medicament medicament2 = new Medicament("Amoxicilline", "Antibiotiques", "4.5", "30/09/2024", 30);
        Medicament medicament3 = new Medicament("Paracetamol", "Analgésiques", "2.5", "30/09/2024", 100);
        Medicament medicament4 = new Medicament("Ibuprofene", "Anti-inflammatoires", "3.5", "30/09/2024", 50);
        Medicament medicament5 = new Medicament("Azithromycine", "Antibiotiques", "4.5", "30/09/2024", 30);

        Client client = new Client("Dupont", "Jean", "1 rue de la paix", "54000", "Nancy", "0372000000", "dupont@mail.com", "123456789012345", "13/01/1920", mutuelle2, medecin1.getNomMedecin(), medecin1.getPrenomMedecin());
        Client client1 = new Client("Marc", "Pierre", "10 rue des champs", "75000", "Paris", "0172000000", "durand@mail.com", "543216789012345", "13/01/1920", mutuelle1, medecin2.getNomMedecin(), medecin2.getPrenomMedecin());
        Client client2 = new Client("Yohan", "Paul", "5 avenue de l'Europe", "69000", "Lyon", "0472000000", "paul@gmail.Com", "987654321012345", "13/01/1920", mutuelle3, medecin2.getNomMedecin(), medecin2.getPrenomMedecin());
        Client client3 = new Client("Semiremorque", "Leroutier", "1 rue de la paix", "54000", "Nancy", "0372000000", "semiremorque@gmail.com", "123456789012345", "13/01/1920", mutuelle2, medecin1.getNomMedecin(), medecin1.getPrenomMedecin());
        Client client4 = new Client("Vié", "Maxime", "10 rue des champs", "75000", "Paris", "0172000000", "maximevie@mail.com", "543216789012345", "13/01/1920", mutuelle1, medecin1.getNomMedecin(), medecin1.getPrenomMedecin());

        GestionListe.addClient(client);
        GestionListe.addClient(client2);
        GestionListe.addClient(client3);
        GestionListe.addClient(client4);
        GestionListe.addClient(client1);


        GestionListe.addMedecin(medecin);
        GestionListe.addMedecin(medecin1);
        GestionListe.addMedecin(medecin2);

        GestionListe.addMedicament(medicament);
        GestionListe.addMedicament(medicament1);
        GestionListe.addMedicament(medicament2);
        GestionListe.addMedicament(medicament3);
        GestionListe.addMedicament(medicament4);
        GestionListe.addMedicament(medicament5);

        GestionListe.addMutuelle(mutuelle1);
        GestionListe.addMutuelle(mutuelle2);
        GestionListe.addMutuelle(mutuelle3);
        GestionListe.addMutuelle(mutuelle4);
        GestionListe.addMutuelle(mutuelle5);

    }

    // Start the application
    public void start() throws SaisieException {
        ControllerAccueil accueil = new ControllerAccueil();
        accueil.setVisible(true);

    }
}
