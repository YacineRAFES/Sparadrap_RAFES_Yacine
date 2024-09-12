package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medecin;
import fr.afpa.dev.pompey.Modele.Ordonnance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdonnanceTest {
    private Ordonnance ordonnanceUnderTest;

    @BeforeEach
    void setUp() {
        ordonnanceUnderTest = new Ordonnance();
    }

    @Test
    void getClient() {
    }

    // fais moi un test pour le setClient si il est null et empty

    @ParameterizedTest
    @NullSource
    void setClientNull(Client client) {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setClient(client);
        });
        assertEquals("Le client ne doit pas être vide", e.getMessage());
    }

    @Test
    void setClientEmpty() {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setClient(null);
        });
        assertEquals("Le client ne doit pas être vide", e.getMessage());
    }

    @Test
    void getMedecin() {
    }

    @ParameterizedTest
    @NullSource
    void setMedecinNull(Medecin medecin) {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setMedecin(medecin);
        });
        assertEquals("Le medecin ne doit pas être vide", e.getMessage());
    }

    @Test
    void setMedecinEmpty() {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setMedecin(null);
        });
        assertEquals("Le medecin ne doit pas être vide", e.getMessage());
    }

    @Test
    void getDate() {
    }

    @ParameterizedTest
    @NullSource
    void setDateNull(LocalDate date) {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setDate(date);
        });
        assertEquals("La date n'est pas valide", e.getMessage());
    }

    //fais moi un test sur le setDate si il est inférieur à la date du jour et supérieur à la date du jour
    @Test
    void setDateBeforeNow() {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setDate(LocalDate.now().minusDays(1));
        });
        assertEquals("La date n'est pas valide", e.getMessage());
    }

    @Test
    void setDateAfterNow() {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setDate(LocalDate.now().plusDays(1));
        });
        assertEquals("La date ne doit pas être supérieure à la date du jour", e.getMessage());
    }

    @Test
    void getListeMedicament() {
    }

    @ParameterizedTest
    @NullSource
    void setListeMedicamentNull(String[][] listeMedicament) {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setListeMedicament(listeMedicament);
        });
        assertEquals("La liste des médicaments ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setListeMedicamentEmpty(String[][] listeMedicament) {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setListeMedicament(listeMedicament);
        });
        assertEquals("La liste des médicaments ne doit pas être vide", e.getMessage());
    }

    @Test
    void getPrixTotal() {
    }

    @Test
    void setPrixTotalNull() {
        Exception e =  assertThrows(Exception.class, () -> {
            ordonnanceUnderTest.setPrixTotal(0);
        });
        assertEquals("Le prix total ne peut pas être négatif", e.getMessage());
    }

    @AfterEach
    void tearDown() {
    }
}