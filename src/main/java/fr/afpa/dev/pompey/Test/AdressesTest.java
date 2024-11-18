package fr.afpa.dev.pompey.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdressesTest {

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
            achatDirectUnderTest.setRue(rue);
        });
        assertEquals("La rue ne doit pas être vide", e.getMessage());
    }

    @Test
    void getVille() {
    }

    @Test
    void setVille() {
    }
}