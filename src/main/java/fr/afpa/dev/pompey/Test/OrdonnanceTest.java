package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Modele.Ordonnances;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrdonnanceTest {

    @Test
    void constructorShouldInitializeFields() throws SaisieException {
        Date date = new Date(System.currentTimeMillis());
        Client client = new Client(1);
        Medecin medecin = new Medecin();
        Ordonnances<Medicament> ordonnances = new Ordonnances<>(date, client, medecin);

        assertEquals(date, ordonnances.getDate());
        assertEquals(client, ordonnances.getClient());
        assertEquals(medecin, ordonnances.getMedecin());
    }

    @Test
    void setDateShouldThrowExceptionWhenDateIsNull() {
        Ordonnances<Medicament> ordonnances = new Ordonnances<>();
        SaisieException exception = assertThrows(SaisieException.class, () -> ordonnances.setDate(null));
        assertEquals("La date ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setDateShouldThrowExceptionWhenDateIsEmpty() {
        Ordonnances<Medicament> ordonnances = new Ordonnances<>();
        SaisieException exception = assertThrows(SaisieException.class, () -> ordonnances.setDate(Date.valueOf("")));
        assertEquals("La date ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setDateShouldThrowExceptionWhenDateIsInFuture() {
        Ordonnances<Medicament> ordonnances = new Ordonnances<>();
        SaisieException exception = assertThrows(SaisieException.class, () -> ordonnances.setDate(new Date(System.currentTimeMillis() + 86400000)));
        assertEquals("La date ne doit pas être dans le futur", exception.getMessage());
    }

    @Test
    void setDateShouldThrowExceptionWhenDateIsBeforeToday() {
        Ordonnances<Medicament> ordonnances = new Ordonnances<>();
        SaisieException exception = assertThrows(SaisieException.class, () -> ordonnances.setDate(new Date(System.currentTimeMillis() - 86400000)));
        assertEquals("La date ne doit pas être avant aujourd'hui", exception.getMessage());
    }

    @Test
    void setDateShouldUpdateDate() throws SaisieException {
        Ordonnances<Medicament> ordonnances = new Ordonnances<>();
        Date date = new Date(System.currentTimeMillis());
        ordonnances.setDate(date);
        assertEquals(date, ordonnances.getDate());
    }

    @Test
    void setMedecinShouldUpdateMedecin() {
        Ordonnances<Medicament> ordonnances = new Ordonnances<>();
        Medecin medecin = new Medecin();
        ordonnances.setMedecin(medecin);
        assertEquals(medecin, ordonnances.getMedecin());
    }

    @Test
    void setClientShouldUpdateClient() {
        Ordonnances<Medicament> ordonnances = new Ordonnances<>();
        Client client = new Client(1);
        ordonnances.setClient(client);
        assertEquals(client, ordonnances.getClient());
    }
}