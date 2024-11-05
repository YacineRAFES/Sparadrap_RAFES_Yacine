package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.TableMedicamentTemporaire;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class TableMedicamentTemporaireTest {
    private TableMedicamentTemporaire tableMedicamentTemporaireUnderTest;

    @BeforeEach
    void setUp() {
        tableMedicamentTemporaireUnderTest = new TableMedicamentTemporaire();
    }

    @Test
    void getNom() {
    }

    @ParameterizedTest
    @NullSource
    void setNomNull(String nom) {
        Exception e =  assertThrows(Exception.class, () -> {
            tableMedicamentTemporaireUnderTest.setNom(nom);
        });
        assertEquals("le nom de médicament ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setNomEmpty(String nom) {
        Exception e =  assertThrows(Exception.class, () -> {
            tableMedicamentTemporaireUnderTest.setNom(nom);
        });
        assertEquals("le nom de médicament ne doit pas être vide", e.getMessage());
    }


    @Test
    void getPrix() {
    }

    @ParameterizedTest
    @NullSource
    void setPrixNull(String prix) {
        Exception e =  assertThrows(Exception.class, () -> {
            tableMedicamentTemporaireUnderTest.setPrix(prix);
        });
        assertEquals("le prix ne doit pas être vide", e.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void setPrixEmpty(String prix) {
        Exception e =  assertThrows(Exception.class, () -> {
            tableMedicamentTemporaireUnderTest.setPrix(prix);
        });
        assertEquals("le prix ne doit pas être vide", e.getMessage());
    }

    @Test
    void getQuantite() {
    }

    @Test
    void setQuantiteNegative() {
        Exception e = assertThrows(Exception.class, () -> {
            tableMedicamentTemporaireUnderTest.setQuantite(-1);
        });
        assertEquals("la quantité ne doit pas être vide", e.getMessage());
    }

    @AfterEach
    void tearDown() {
    }
}