package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.Mutuelle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class MutuelleTest {
    private Mutuelle mutuelleUnderTest;

    @BeforeEach
    void setUp() {
        mutuelleUnderTest = new Mutuelle();
    }

    @Test
    void getNom() {
    }

    //fais moi un test pour le setNom si il est null et empty
    @ParameterizedTest
    @NullSource
    void setNomNull(String nom) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setNom(nom);
        });
        assertEquals("le nom de la mutuelle ne doit pas être vide.", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setNomEmpty(String nom) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setNom(nom);
        });
        assertEquals("le nom de la mutuelle ne doit pas être vide.", e.getMessage());
    }

    @Test
    void getAdresse() {
    }

    @ParameterizedTest
    @NullSource
    void setAdresseNull(String adresse) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setAdresse(adresse);
        });
        assertEquals("l'adresse ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setAdresseEmpty(String adresse) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setAdresse(adresse);
        });
        assertEquals("l'adresse ne doit pas être vide", e.getMessage());
    }

    @Test
    void getCodePostal() {
    }

    @ParameterizedTest
    @NullSource
    void setCodePostalNull(String codePostal) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setCodePostal(codePostal);
        });
        assertEquals("le code postal ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setCodePostalEmpty(String codePostal) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setCodePostal(codePostal);
        });
        assertEquals("le code postal ne doit pas être vide", e.getMessage());
    }

    @Test
    void getVille() {
    }

    @ParameterizedTest
    @NullSource
    void setVilleNull(String ville) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setVille(ville);
        });
        assertEquals("la ville ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setVilleEmpty(String ville) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setVille(ville);
        });
        assertEquals("la ville ne doit pas être vide", e.getMessage());
    }

    @Test
    void getTelephone() {
    }

    @ParameterizedTest
    @NullSource
    void setTelephoneNull(String telephone) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setTelephone(telephone);
        });
        assertEquals("Le numéro de téléphone ne corresponds pas", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setTelephoneEmpty(String telephone) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setTelephone(telephone);
        });
        assertEquals("Le numéro de téléphone ne corresponds pas", e.getMessage());
    }

    @Test
    void getEmail() {
    }

    @ParameterizedTest
    @NullSource
    void setEmailNull(String email) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setEmail(email);
        });
        assertEquals("L'email ne corresponds pas", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setEmailEmpty(String email) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setEmail(email);
        });
        assertEquals("L'email ne corresponds pas", e.getMessage());
    }

    @Test
    void getDepartement() {
    }

    @ParameterizedTest
    @NullSource
    void setDepartementNull(String departement) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setDepartement(departement);
        });
        assertEquals("le departement ne doit pas être vide.", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setDepartementEmpty(String departement) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setDepartement(departement);
        });
        assertEquals("le departement ne doit pas être vide.", e.getMessage());
    }

    @Test
    void getTauxDePriseEnCharge() {
    }

    @ParameterizedTest
    @NullSource
    void setTauxDePriseEnChargeNull(String tauxDePriseEnCharge) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setTauxDePriseEnCharge(tauxDePriseEnCharge);
        });
        assertEquals("le taux de prise en charge ne doit pas être vide.", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setTauxDePriseEnChargeEmpty(String tauxDePriseEnCharge) {
        Exception e =  assertThrows(Exception.class, () -> {
            mutuelleUnderTest.setTauxDePriseEnCharge(tauxDePriseEnCharge);
        });
        assertEquals("le taux de prise en charge ne doit pas être vide.", e.getMessage());
    }

    @AfterEach
    void tearDown() {
    }
}