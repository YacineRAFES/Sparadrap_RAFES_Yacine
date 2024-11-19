package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Modele.AchatDirect;
import fr.afpa.dev.pompey.Modele.Commande;
import fr.afpa.dev.pompey.Modele.Medicament;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class CommandeTest {
    private Commande commandeUnderTest;

    @BeforeEach
    void setUp() {
        commandeUnderTest = new Commande();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAchatDirect() {
    }

    @ParameterizedTest
    @NullSource
    void setAchatDirectNullSource(AchatDirect achatDirect) {
        Exception e = assertThrows(Exception.class, () -> {
            commandeUnderTest.setAchatDirect(achatDirect);
        });
        assertEquals("L'achat direct ne doit pas être vide", e.getMessage());
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
            commandeUnderTest.setMedicament(medicament);
        });
        assertEquals("Le médicament ne doit pas être vide", e.getMessage());
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
            commandeUnderTest.setQuantite(quantite);
        });
        assertEquals("La quantité doit être supérieure à 0", e.getMessage());
    }
}