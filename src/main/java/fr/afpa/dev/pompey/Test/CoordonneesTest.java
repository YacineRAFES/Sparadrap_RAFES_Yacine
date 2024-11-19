package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.Coordonnees;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class CoordonneesTest {
    private Coordonnees coordonneesUnderTest;

    @BeforeEach
    void setUp() {
        coordonneesUnderTest = new Coordonnees();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setTelephone() {

    }

    @ParameterizedTest
    @NullSource
    void setTelephoneNullSource(String telephone) {
        Exception e = assertThrows(Exception.class, () -> {
            coordonneesUnderTest.setTelephone(telephone);
        });
        assertEquals("Le téléphone ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setTelephoneEmptySource(String telephone) {
        Exception e = assertThrows(Exception.class, () -> {
            coordonneesUnderTest.setTelephone(telephone);
        });
        assertEquals("Le téléphone ne doit pas être vide", e.getMessage());
    }

    @Test
    void getTelephone() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void setEmail() {
    }

    @ParameterizedTest
    @NullSource
    void setEmailNullSource(String email) {
        Exception e = assertThrows(Exception.class, () -> {
            coordonneesUnderTest.setEmail(email);
        });
        assertEquals("L'email ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setEmailEmptySource(String email) {
        Exception e = assertThrows(Exception.class, () -> {
            coordonneesUnderTest.setEmail(email);
        });
        assertEquals("L'email ne doit pas être vide", e.getMessage());
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @ParameterizedTest
    @NullSource
    void setIdNullSource(int id) {
        Exception e = assertThrows(Exception.class, () -> {
            coordonneesUnderTest.setId(id);
        });
        assertEquals("L'id doit être supérieur à 0", e.getMessage());
    }

}