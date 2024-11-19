package fr.afpa.dev.pompey.Test;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Medecin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedecinTest {

    @Test
    void constructorShouldInitializeFields() throws SaisieException {
        Medecin medecin = new Medecin("John", "Doe", "12345", "Cardiology", 1, 1);

        assertEquals("John", medecin.getNom());
        assertEquals("Doe", medecin.getPrenom());
        assertEquals("12345", medecin.getNumAgreement());
        assertEquals("Cardiology", medecin.getSpecialite());
        assertNotNull(medecin.getAdresses());
        assertNotNull(medecin.getCoordonnees());
    }

    @Test
    void setNomShouldThrowExceptionWhenNomIsNull() {
        Medecin medecin = new Medecin();
        SaisieException exception = assertThrows(SaisieException.class, () -> medecin.setNom(null));
        assertEquals("Le nom ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setNomShouldThrowExceptionWhenNomIsEmpty() {
        Medecin medecin = new Medecin();
        SaisieException exception = assertThrows(SaisieException.class, () -> medecin.setNom(""));
        assertEquals("Le nom ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setPrenomShouldThrowExceptionWhenPrenomIsNull() {
        Medecin medecin = new Medecin();
        SaisieException exception = assertThrows(SaisieException.class, () -> medecin.setPrenom(null));
        assertEquals("Le prénom ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setPrenomShouldThrowExceptionWhenPrenomIsEmpty() {
        Medecin medecin = new Medecin();
        SaisieException exception = assertThrows(SaisieException.class, () -> medecin.setPrenom(""));
        assertEquals("Le prénom ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setNumAgreementShouldThrowExceptionWhenNumAgreementIsNull() {
        Medecin medecin = new Medecin();
        SaisieException exception = assertThrows(SaisieException.class, () -> medecin.setNumAgreement(null));
        assertEquals("Le numéro d'agreement ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setNumAgreementShouldThrowExceptionWhenNumAgreementIsEmpty() {
        Medecin medecin = new Medecin();
        SaisieException exception = assertThrows(SaisieException.class, () -> medecin.setNumAgreement(""));
        assertEquals("Le numéro d'agreement ne doit pas être vide", exception.getMessage());
    }

    @Test
    void setSpecialiteShouldThrowExceptionWhenSpecialiteIsNull() {
        Medecin medecin = new Medecin();
        SaisieException exception = assertThrows(SaisieException.class, () -> medecin.setSpecialite(null));
        assertEquals("La specialité ne doit pas être vide.", exception.getMessage());
    }

    @Test
    void setSpecialiteShouldThrowExceptionWhenSpecialiteIsEmpty() {
        Medecin medecin = new Medecin();
        SaisieException exception = assertThrows(SaisieException.class, () -> medecin.setSpecialite(""));
        assertEquals("La specialité ne doit pas être vide.", exception.getMessage());
    }

    @Test
    void setNomShouldUpdateNom() throws SaisieException {
        Medecin medecin = new Medecin();
        medecin.setNom("Smith");
        assertEquals("Smith", medecin.getNom());
    }

    @Test
    void setPrenomShouldUpdatePrenom() throws SaisieException {
        Medecin medecin = new Medecin();
        medecin.setPrenom("John");
        assertEquals("John", medecin.getPrenom());
    }

    @Test
    void setNumAgreementShouldUpdateNumAgreement() throws SaisieException {
        Medecin medecin = new Medecin();
        medecin.setNumAgreement("67890");
        assertEquals("67890", medecin.getNumAgreement());
    }

    @Test
    void setSpecialiteShouldUpdateSpecialite() throws SaisieException {
        Medecin medecin = new Medecin();
        medecin.setSpecialite("Neurology");
        assertEquals("Neurology", medecin.getSpecialite());
    }
}