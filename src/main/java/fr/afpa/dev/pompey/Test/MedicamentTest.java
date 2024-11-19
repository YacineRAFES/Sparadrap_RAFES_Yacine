package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Categorie;
import fr.afpa.dev.pompey.Modele.Medicament;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentTest {

    @Test
    void constructorShouldInitializeFields() throws SaisieException {
        Categorie categorie = new Categorie();
        Medicament medicament = new Medicament("Aspirin", new java.util.Date(), 10, 5.0, categorie);

        assertEquals("Aspirin", medicament.getNom());
        assertNotNull(medicament.getMiseEnService());
        assertEquals(10, medicament.getQuantite());
        assertEquals(5.0, medicament.getPrix());
        assertEquals(categorie, medicament.getCategorie());
    }

    @Test
    void setNomShouldThrowExceptionWhenNomIsNull() {
        Medicament medicament = new Medicament();
        SaisieException exception = assertThrows(SaisieException.class, () -> medicament.setNom(null));
        assertEquals("le nom de médicament ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setNomShouldThrowExceptionWhenNomIsEmpty() {
        Medicament medicament = new Medicament();
        SaisieException exception = assertThrows(SaisieException.class, () -> medicament.setNom(""));
        assertEquals("le nom de médicament ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setNomShouldUpdateNom() throws SaisieException {
        Medicament medicament = new Medicament();
        medicament.setNom("Ibuprofen");
        assertEquals("Ibuprofen", medicament.getNom());
    }

    @Test
    void setMiseEnServiceShouldThrowExceptionWhenMiseEnServiceIsNull() {
        Medicament medicament = new Medicament();
        SaisieException exception = assertThrows(SaisieException.class, () -> medicament.setMiseEnService(null));
        assertEquals("La date de mise en service ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setMiseEnServiceShouldThrowExceptionWhenMiseEnServiceIsInFuture() {
        Medicament medicament = new Medicament();
        SaisieException exception = assertThrows(SaisieException.class, () -> medicament.setMiseEnService(new Date(System.currentTimeMillis() + 86400000)));
        assertEquals("La date de mise en service ne doit pas être dans le futur", exception.getMessage());
    }

    @Test
    void setMiseEnServiceShouldUpdateMiseEnService() throws SaisieException {
        Medicament medicament = new Medicament();
        java.util.Date date = new java.util.Date();
        medicament.setMiseEnService(date);
        assertEquals(date, medicament.getMiseEnService());
    }

    @Test
    void setQuantiteShouldThrowExceptionWhenQuantiteIsNegative() {
        Medicament medicament = new Medicament();
        SaisieException exception = assertThrows(SaisieException.class, () -> medicament.setQuantite(-1));
        assertEquals("La quantité ne peut pas être négative", exception.getMessage());
    }

    @Test
    void setQuantiteShouldUpdateQuantite() throws SaisieException {
        Medicament medicament = new Medicament();
        medicament.setQuantite(5);
        assertEquals(5, medicament.getQuantite());
    }

    @Test
    void setPrixShouldThrowExceptionWhenPrixIsNegative() {
        Medicament medicament = new Medicament();
        SaisieException exception = assertThrows(SaisieException.class, () -> medicament.setPrix(-1.0));
        assertEquals("Le prix ne peut pas être négatif", exception.getMessage());
    }

    @Test
    void setPrixShouldUpdatePrix() throws SaisieException {
        Medicament medicament = new Medicament();
        medicament.setPrix(10.0);
        assertEquals(10.0, medicament.getPrix());
    }

    @Test
    void setCategorieShouldUpdateCategorie() {
        Medicament medicament = new Medicament();
        Categorie categorie = new Categorie();
        medicament.setCategorie(categorie);
        assertEquals(categorie, medicament.getCategorie());
    }
}