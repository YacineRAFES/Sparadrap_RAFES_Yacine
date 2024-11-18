package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.AchatDirect;
import fr.afpa.dev.pompey.Modele.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AchatDirectTest {
    private AchatDirect achatDirectUnderTest;

    @BeforeEach
    void setUp() {
        achatDirectUnderTest = new AchatDirect();
    }

    @Test
    void getClient() {
    }

    @ParameterizedTest
    @NullSource
    void setClientNull(Client client) {
        Exception e =  assertThrows(Exception.class, () -> {
            achatDirectUnderTest.setClient(client);
        });
        assertEquals("Le client ne doit pas être vide", e.getMessage());
    }

    @Test
    void setClientEmpty() {
        Exception e =  assertThrows(Exception.class, () -> {
            achatDirectUnderTest.setClient(null);
        });
        assertEquals("Le client ne doit pas être vide", e.getMessage());
    }

    @Test
    void getDate() {
    }

    @ParameterizedTest
    @NullSource
    void setDateNull(Date date) {
        Exception e =  assertThrows(Exception.class, () -> {
            achatDirectUnderTest.setDate(date);
        });
        assertEquals("La date ne doit pas être vide", e.getMessage());
    }

    @AfterEach
    void tearDown() {
    }
}