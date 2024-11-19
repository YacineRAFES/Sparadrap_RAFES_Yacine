package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.Adresses;
import fr.afpa.dev.pompey.Modele.Categorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class CategorieTest {
    private Categorie categorieUnderTest;

    @BeforeEach
    void setUp() {
        categorieUnderTest = new Categorie();
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getNom() {
    }

    @ParameterizedTest
    @NullSource
    void setNomNullSource(String nom) {
        Exception e = assertThrows(Exception.class, () -> {
            categorieUnderTest.setNom(nom);
        });
        assertEquals("Le nom ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setNomEmptySource(String nom) {
        Exception e = assertThrows(Exception.class, () -> {
            categorieUnderTest.setNom(nom);
        });
        assertEquals("Le nom ne doit pas être vide", e.getMessage());
    }
}