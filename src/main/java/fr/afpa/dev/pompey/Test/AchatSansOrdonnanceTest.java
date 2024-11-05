package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.AchatSansOrdonnance;
import fr.afpa.dev.pompey.Modele.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AchatSansOrdonnanceTest {
    private AchatSansOrdonnance achatSansOrdonnanceUnderTest;

    @BeforeEach
    void setUp() {
        achatSansOrdonnanceUnderTest = new AchatSansOrdonnance();
    }

    @Test
    void getClient() {
    }

    @ParameterizedTest
    @NullSource
    void setClientNull(Client client) {
        Exception e =  assertThrows(Exception.class, () -> {
            achatSansOrdonnanceUnderTest.setClient(client);
        });
        assertEquals("Le client ne doit pas être vide", e.getMessage());
    }

    @Test
    void setClientEmpty() {
        Exception e =  assertThrows(Exception.class, () -> {
            achatSansOrdonnanceUnderTest.setClient(null);
        });
        assertEquals("Le client ne doit pas être vide", e.getMessage());
    }

    @Test
    void getDate() {
    }

    @ParameterizedTest
    @NullSource
    void setDateNull(LocalDate date) {
        Exception e =  assertThrows(Exception.class, () -> {
            achatSansOrdonnanceUnderTest.setDate(date);
        });
        assertEquals("La date ne doit pas être vide", e.getMessage());
    }

    @Test
    void getListeMedicament() {
    }

    @ParameterizedTest
    @NullSource
    void setListeMedicamentNull(String[][] listeMedicament) {
        Exception e =  assertThrows(Exception.class, () -> {
            achatSansOrdonnanceUnderTest.setListeMedicament(listeMedicament);
        });
        assertEquals("La liste des médicaments ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setListeMedicamentEmpty(String[][] listeMedicament) {
        Exception e =  assertThrows(Exception.class, () -> {
            achatSansOrdonnanceUnderTest.setListeMedicament(listeMedicament);
        });
        assertEquals("La liste des médicaments ne doit pas être vide", e.getMessage());
    }

    @Test
    void getPrixTotal() {
    }

    @Test
    void setPrixTotalNull() {
        Exception e =  assertThrows(Exception.class, () -> {
            achatSansOrdonnanceUnderTest.setPrixTotal(0);
        });
        assertEquals("Le prix total ne peut pas être négatif ou vide", e.getMessage());
    }

    @AfterEach
    void tearDown() {
    }
}