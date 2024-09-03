package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.Client;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd/yy/MMM");

    private Client clientUnderTest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        clientUnderTest = new Client();
    }

    @org.junit.jupiter.api.Test
    void getAdresse() {
    }

    @ParameterizedTest
    @NullSource
    void setAdresseNullSource(String adresse) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setAdresse(adresse);
        });
        assertEquals("L'adresse ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setAdresseEmptySource(String adresse) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setAdresse(adresse);
        });
        assertEquals("L'adresse ne doit pas être vide", e.getMessage());
    }

    @org.junit.jupiter.api.Test
    void getNom() {
    }

    @ParameterizedTest
    @NullSource
    void setNomNullSource(String nom) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setNom(nom);
        });
        assertEquals("Le nom ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setNomEmptySource(String nom) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setNom(nom);
        });
        assertEquals("Le nom ne doit pas être vide", e.getMessage());
    }

    @org.junit.jupiter.api.Test
    void getPrenom() {
    }

    @ParameterizedTest
    @NullSource
    void setPrenomNullSource(String prenom) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setPrenom(prenom);
        });
        assertEquals("Le prénom ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setPrenomEmptySource(String prenom) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setPrenom(prenom);
        });
        assertEquals("Le prénom ne doit pas être vide", e.getMessage());
    }

    @org.junit.jupiter.api.Test
    void getTelephone() {
    }

    @ParameterizedTest
    @NullSource
    void setTelephoneNullSource(String telephone) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setTelephone(telephone);
        });
        assertEquals("Le numéro de téléphone ne corresponds pas", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setTelephoneEmptySource(String telephone) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setTelephone(telephone);
        });
        assertEquals("Le numéro de téléphone ne corresponds pas", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "abcde12345", "01234abcd", "", "   ", "1234 5678"})
    void setTelephoneValue(String telephone) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setTelephone(telephone);
        });
        assertEquals("Le numéro de téléphone ne corresponds pas", e.getMessage());
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
    }

    @ParameterizedTest
    @NullSource
    void setEmailNullSource(String email) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setEmail(email);
        });
        assertEquals("L'email ne corresponds pas", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setEmailEmptySource(String email) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setEmail(email);
        });
        assertEquals("L'email ne corresponds pas", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaa.com", "aaaagmail.com", "aaaaad @gmail.com"})
    void setEmailValueSource(String email) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setEmail(email);
        });
        assertEquals("L'email ne corresponds pas", e.getMessage());
    }

    @org.junit.jupiter.api.Test
    void getNumeroSecuClient() {
    }

    @ParameterizedTest
    @NullSource
    void setNumeroSecuClientNullSource(String numeroSecuClient) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setNumeroSecuClient(numeroSecuClient);
        });
        assertEquals("Le numero de la sécurité sociale ne corresponds pas", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setNumeroSecuClientEmptySource(String numeroSecuClient) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setNumeroSecuClient(numeroSecuClient);
        });
        assertEquals("Le numero de la sécurité sociale ne corresponds pas", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"06aaaa6544", "878787878787878787"})
    void setNumeroSecuClientValueSource(String numeroSecuClient) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setNumeroSecuClient(numeroSecuClient);
        });
        assertEquals("Le numero de la sécurité sociale ne corresponds pas", e.getMessage());
    }

    @org.junit.jupiter.api.Test
    void getDateNaissance() {
    }

    @ParameterizedTest
    @NullSource
    void setDateNaissanceNullSource(String dateNaissance) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setDateNaissance(dateNaissance);
        });
        assertEquals("La date de naissance ne corresponds pas", e.getMessage());
    }

    @org.junit.jupiter.api.Test
    void getMutuelle() {
    }

    @ParameterizedTest
    @NullSource
    void setMutuelleNullSource(String mutuelle) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setMutuelle(mutuelle);
        });
        assertEquals("La mutuelle ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setMutuelleEmptySource(String mutuelle) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setMutuelle(mutuelle);
        });
        assertEquals("La mutuelle ne doit pas être vide", e.getMessage());
    }

    @org.junit.jupiter.api.Test
    void getMedecinTraitant() {
    }

    @ParameterizedTest
    @NullSource
    void setMedecinTraitantNullSource(String medecinTraitant) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setMedecinTraitant(medecinTraitant);
        });
        assertEquals("Le medecin traitant ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setMedecinTraitantEmptySource(String medecinTraitant) {
        Exception e =  assertThrows(Exception.class, () -> {
            clientUnderTest.setMedecinTraitant(medecinTraitant);
        });
        assertEquals("Le medecin traitant ne doit pas être vide", e.getMessage());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}