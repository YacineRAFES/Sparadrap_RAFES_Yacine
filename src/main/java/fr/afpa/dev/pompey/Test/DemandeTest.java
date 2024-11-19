package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.Demande;
import fr.afpa.dev.pompey.Modele.Medicament;
import fr.afpa.dev.pompey.Modele.Ordonnances;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class DemandeTest {
    private Demande demandeUnderTest;

    @BeforeEach
    void setUp() {
        demandeUnderTest = new Demande();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getQuantite() {
    }

    @Test
    void setQuantite() {
    }

    @ParameterizedTest
    @NullSource
    void setQuantiteNullSource(int quantite) {
        Exception e = assertThrows(Exception.class, () -> {
            demandeUnderTest.setQuantite(quantite);
        });
        assertEquals("La quantité ne doit pas être vide", e.getMessage());
    }

    @Test
    void getMedicament() {
    }

    @Test
    void setMedicament() {
    }

    @ParameterizedTest
    @NullSource
    void setMedicamentNullSource(Medicament medicament) {
        Exception e = assertThrows(Exception.class, () -> {
            demandeUnderTest.setMedicament(medicament);
        });
        assertEquals("Le médicament ne doit pas être vide", e.getMessage());
    }

    @Test
    void getOrdonnances() {
    }

    @Test
    void setOrdonnances() {
    }

    @ParameterizedTest
    @NullSource
    void setOrdonnancesNullSource(Ordonnances ordonnances) {
        Exception e = assertThrows(Exception.class, () -> {
            demandeUnderTest.setOrdonnances(ordonnances);
        });
        assertEquals("L'ordonnance ne doit pas être vide", e.getMessage());
    }

}