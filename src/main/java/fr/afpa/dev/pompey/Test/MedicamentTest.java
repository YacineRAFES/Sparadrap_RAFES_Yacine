package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Medicament;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentTest {
    private Medicament medicamentUnderTest;

    @BeforeEach
    void setUp() {
        medicamentUnderTest = new Medicament();
    }

    @Test
    void getNom() {
    }

    @ParameterizedTest
    @NullSource
    void setNomNull(String nom) {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setNom(nom);
        });
        assertEquals("le nom de médicament ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setNomEmpty(String nom) {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setNom(nom);
        });
        assertEquals("le nom de médicament ne doit pas être vide", e.getMessage());
    }

    @Test
    void getCategorie() {
    }

    @ParameterizedTest
    @NullSource
    void setCategorieNull(String categorie) {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setCategorie(categorie);
        });
        assertEquals("le nom de catégorie ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setCategorieEmpty(String categorie) {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setCategorie(categorie);
        });
        assertEquals("le nom de catégorie ne doit pas être vide", e.getMessage());
    }

    @Test
    void getPrix() {
    }

    @ParameterizedTest
    @EmptySource
    void setPrixEmpty(String prix) {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setPrix(prix);
        });
        assertEquals("le prix ne corresponds pas.", e.getMessage());
    }

    @Test
    void getMiseEnService() {
    }

    @ParameterizedTest
    @EmptySource
    void setMiseEnServiceEmpty(String miseEnService) {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setMiseEnService(miseEnService);
        });
        assertEquals("la date de mise en service ne corresponds pas.", e.getMessage());
    }
    //fais moi un test pour le setMiseEnService si il est supérieur qu'aujourd'hui
    @Test
    void setMiseEnServiceUnJourApres() {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setMiseEnService(LocalDate.now().plusDays(1).toString());
        });
        assertEquals("la date de mise en service ne corresponds pas.", e.getMessage());
    }

    @Test
    void getQuantite() {
    }

    @Test
    void setQuantiteNull() {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setQuantite(0);
        });
        assertEquals("La quantité ne peut pas être négative", e.getMessage());
    }

    @Test
    void setQuantiteEmpty() {
        SaisieException e =  assertThrows(SaisieException.class, () -> {
            medicamentUnderTest.setQuantite(0);
        });
        assertEquals("La quantité ne peut pas être négative", e.getMessage());
    }

    @Test
    void testToString() {
    }

    @AfterEach
    void tearDown() {
    }
}