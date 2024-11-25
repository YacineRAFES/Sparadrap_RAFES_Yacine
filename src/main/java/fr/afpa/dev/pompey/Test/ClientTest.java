//package fr.afpa.dev.pompey.Test;
//
//import fr.afpa.dev.pompey.Exception.SaisieException;
//import fr.afpa.dev.pompey.Modele.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EmptySource;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.junit.jupiter.params.provider.NullSource;
//
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ClientTest {
//    private Client clientUnderTest;
//
//    @BeforeEach
//    void setUp() {
//        clientUnderTest = new Client();
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void setId() {
//
//    }
//
//    @ParameterizedTest
//    @NullSource
//    void setNomNullSource(String nom) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setNom(nom);
//        });
//        assertEquals("Le nom ne doit pas être vide", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    void setNomEmptySource(String nom) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setNom(nom);
//        });
//        assertEquals("Le nom ne doit pas être vide", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @NullSource
//    void setPrenomNullSource(String prenom) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setPrenom(prenom);
//        });
//        assertEquals("Le prénom ne doit pas être vide", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    void setPrenomEmptySource(String prenom) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setPrenom(prenom);
//        });
//        assertEquals("Le prénom ne doit pas être vide", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @NullSource
//    void setNumeroSecuClient(String numeroSecuClient) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setNumeroSecuClient(numeroSecuClient);
//        });
//        assertEquals("Le numéro de sécurité sociale ne doit pas être vide", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    void setNumeroSecuClientEmptySource(String numeroSecuClient) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setNumeroSecuClient(numeroSecuClient);
//        });
//        assertEquals("Le numéro de sécurité sociale ne doit pas être vide", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @NullSource
//    void setDateNaissanceNullSource(String dateNaissance) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setDateNaissance(dateNaissance);
//        });
//        assertEquals("La date de naissance ne doit pas être vide", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    void setDateNaissanceEmptySource(String dateNaissance) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setDateNaissance(dateNaissance);
//        });
//        assertEquals("La date de naissance ne doit pas être vide", e.getMessage());
//    }
//
//
//
//    @ParameterizedTest
//    @NullSource
//    void setCoordonneesNullSource(Coordonnees coordonnees) {
//        Exception e = assertThrows(Exception.class, () -> {
//            clientUnderTest.setCoordonnees(coordonnees);
//        });
//        assertEquals("Les coordonnées ne doivent pas être vide", e.getMessage());
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideEmptyCoordonnees")
//    void setCoordonneesShouldThrowException(Coordonnees coordonnees) {
//        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
//            clientUnderTest.setCoordonnees(coordonnees);
//        });
//        assertEquals("Les coordonnées ne doivent pas être vide", e.getMessage());
//    }
//
//    static Stream<Coordonnees> provideEmptyCoordonnees() throws SaisieException {
//        return Stream.of(
//                null,
//                new Coordonnees(null, null),
//                new Coordonnees("", "")
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideEmptyAdresses")
//    void setAdressesShouldThrowException(Adresses adresses) {
//        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
//            clientUnderTest.setAdresses(adresses);
//        });
//        assertEquals("L'adresse ne doit pas être vide", e.getMessage());
//    }
//
//    static Stream<Adresses> provideEmptyAdresses() {
//        return Stream.of(
//                null,
//                new Adresses( null),
//                new Adresses("")
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideEmptyMutuelle")
//    void setMutuelleShouldThrowException(Mutuelle mutuelle) {
//        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
//            clientUnderTest.setMutuelle(mutuelle);
//        });
//        assertEquals("La mutuelle ne doit pas être vide", e.getMessage());
//    }
//
//    static Stream<Mutuelle> provideEmptyMutuelle() throws SaisieException {
//        return Stream.of(
//                null,
//                new Mutuelle(null, 0),
//                new Mutuelle("", 0)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideEmptyMedecin")
//    void setMedecinShouldThrowException(Mutuelle mutuelle) {
//        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
//            clientUnderTest.setMutuelle(mutuelle);
//        });
//        assertEquals("La medecin ne doit pas être vide", e.getMessage());
//    }
//
//    static Stream<Medecin> provideEmptyMedecin() throws SaisieException {
//        return Stream.of(
//                null,
//                new Medecin(null, null),
//                new Medecin("", "")
//        );
//    }
//}