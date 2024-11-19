package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.AchatDirect;
import fr.afpa.dev.pompey.Modele.Adresses;
import fr.afpa.dev.pompey.Modele.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdressesTest {
    private Adresses adressesUnderTest;

    @BeforeEach
    void setUp() {
        adressesUnderTest = new Adresses();
    }

    @Test
    void getId() {

    }

    @Test
    void setId() {
    }

    @Test
    void getRue() {

    }

    @ParameterizedTest
    @NullSource
    void setRueNullSource(String rue) {
        Exception e = assertThrows(Exception.class, () -> {
            adressesUnderTest.setRue(rue);
        });
        assertEquals("La rue ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setRueEmptySource(String rue) {
        Exception e = assertThrows(Exception.class, () -> {
            adressesUnderTest.setRue(rue);
        });
        assertEquals("La rue ne doit pas être vide", e.getMessage());
    }

    @Test
    void getVille() {
    }

    @ParameterizedTest
    @NullSource
    void setVilleNullSource(Ville ville) {
        Exception e = assertThrows(Exception.class, () -> {
            adressesUnderTest.setVille(ville);
        });
        assertEquals("La ville ne doit pas être vide", e.getMessage());
    }

}