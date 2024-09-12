package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.Medecin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class MedecinTest {
    private Medecin medecinUnderTest;

    @BeforeEach
    void setUp() {
        medecinUnderTest = new Medecin();
    }

    @Test
    void getNom() {
    }

    @ParameterizedTest
    @EmptySource
    void setNomEmpty(String nom) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setNom(nom);
        });
        assertEquals("Le nom ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setNomNull(String nom) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setNom(nom);
        });
        assertEquals("Le nom ne doit pas être vide", e.getMessage());
    }

    @Test
    void getPrenom() {
    }

    @ParameterizedTest
    @EmptySource
    void setPrenomEmpty(String prenom) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setPrenom(prenom);
        });
        assertEquals("Le prénom ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setPrenomNull(String prenom) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setPrenom(prenom);
        });
        assertEquals("Le prénom ne doit pas être vide", e.getMessage());
    }

    @Test
    void getRue() {
    }

    @ParameterizedTest
    @EmptySource
    void setRueEmpty(String rue) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setRue(rue);
        });
        assertEquals("L'adresse ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setRueNull(String rue) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setRue(rue);
        });
        assertEquals("L'adresse ne doit pas être vide", e.getMessage());
    }

    @Test
    void getCodePostal() {
    }

    @ParameterizedTest
    @EmptySource
    void setCodePostalEmpty(String codePostal) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setCodePostal(codePostal);
        });
        assertEquals("le code postal ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setCodePostalNull(String codePostal) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setCodePostal(codePostal);
        });
        assertEquals("le code postal ne doit pas être vide", e.getMessage());
    }

    @Test
    void getVille() {
    }

    @ParameterizedTest
    @EmptySource
    void setVilleEmpty(String ville) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setVille(ville);
        });
        assertEquals("la ville ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setVilleNull(String ville) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setVille(ville);
        });
        assertEquals("la ville ne doit pas être vide", e.getMessage());
    }

    @Test
    void getTelephone() {
    }

    @ParameterizedTest
    @EmptySource
    void setTelephoneEmpty(String telephone) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setTelephone(telephone);
        });
        assertEquals("le numéro de téléphone ne doit pas être vide.", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setTelephoneNull(String telephone) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setTelephone(telephone);
        });
        assertEquals("le numéro de téléphone ne doit pas être vide.", e.getMessage());
    }

    @Test
    void getEmail() {
    }

    @ParameterizedTest
    @EmptySource
    void setEmailEmpty(String email) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setEmail(email);
        });
        assertEquals("l'email ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setEmailNull(String email) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setEmail(email);
        });
        assertEquals("l'email ne doit pas être vide", e.getMessage());
    }

    @Test
    void getNumAgreement() {
    }

    @ParameterizedTest
    @EmptySource
    void setNumAgreementEmpty(String numAgreement) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setNumAgreement(numAgreement);
        });
        assertEquals("Le numéro d'agreement ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setNumAgreementNull(String numAgreement) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setNumAgreement(numAgreement);
        });
        assertEquals("Le numéro d'agreement ne doit pas être vide", e.getMessage());
    }

    @Test
    void getSpecialite() {
    }

    @ParameterizedTest
    @EmptySource
    void setSpecialiteEmpty(String specialite) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setSpecialite(specialite);
        });
        assertEquals("La specialité ne doit pas être vide.", e.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void setSpecialiteNull(String specialite) {
        Exception e =  assertThrows(Exception.class, () -> {
            medecinUnderTest.setSpecialite(specialite);
        });
        assertEquals("La specialité ne doit pas être vide.", e.getMessage());
    }

    @Test
    void testToString() {
    }

    @AfterEach
    void tearDown() {
    }
}