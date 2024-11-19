package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Adresses;
import fr.afpa.dev.pompey.Modele.Coordonnees;
import fr.afpa.dev.pompey.Modele.Mutuelle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MutuelleTest {

    @Test
    void constructorShouldInitializeFields() throws SaisieException {
        Adresses adresses = new Adresses(1);
        Coordonnees coordonnees = new Coordonnees(1);
        Mutuelle<Adresses> mutuelle = new Mutuelle<>("Mutuelle", 80, adresses, coordonnees);

        assertEquals("Mutuelle", mutuelle.getNom());
        assertEquals(80, mutuelle.getTauxDePriseEnCharge());
        assertEquals(adresses, mutuelle.getAdresses());
        assertEquals(coordonnees, mutuelle.getCoordonnees());
    }

    @Test
    void setNomShouldThrowExceptionWhenNomIsInvalid() {
        Mutuelle<Adresses> mutuelle = new Mutuelle<>();
        SaisieException exception = assertThrows(SaisieException.class, () -> mutuelle.setNom(""));
        assertEquals("le nom de la mutuelle ne doit pas être vide.", exception.getMessage());
    }

    @Test
    void setNomShouldUpdateNom() throws SaisieException {
        Mutuelle<Adresses> mutuelle = new Mutuelle<>();
        mutuelle.setNom("ValidName");
        assertEquals("ValidName", mutuelle.getNom());
    }

    @Test
    void setTauxDePriseEnChargeShouldThrowExceptionWhenTauxIsZero() {
        Mutuelle<Adresses> mutuelle = new Mutuelle<>();
        SaisieException exception = assertThrows(SaisieException.class, () -> mutuelle.setTauxDePriseEnCharge(0));
        assertEquals("le taux de prise en charge ne doit pas être vide.", exception.getMessage());
    }

    @Test
    void setTauxDePriseEnChargeShouldUpdateTaux() throws SaisieException {
        Mutuelle<Adresses> mutuelle = new Mutuelle<>();
        mutuelle.setTauxDePriseEnCharge(50);
        assertEquals(50, mutuelle.getTauxDePriseEnCharge());
    }

    @Test
    void setAdressesShouldUpdateAdresses() {
        Mutuelle<Adresses> mutuelle = new Mutuelle<>();
        Adresses adresses = new Adresses(1);
        mutuelle.setAdresses(adresses);
        assertEquals(adresses, mutuelle.getAdresses());
    }

    @Test
    void setCoordonneesShouldUpdateCoordonnees() throws SaisieException {
        Mutuelle<Adresses> mutuelle = new Mutuelle<>();
        Coordonnees coordonnees = new Coordonnees(1);
        mutuelle.setCoordonnees(coordonnees);
        assertEquals(coordonnees, mutuelle.getCoordonnees());
    }
}