package fr.afpa.dev.pompey.Modele.Utilitaires;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;

public class Verification {
    public Verification() {}

    public static String NomPrenom(String saisie, String type) throws SaisieException {
        if(!saisie.matches(Regex.REGEXNOMPRENOM)) {
            Fenetre.Fenetre("Le " + type + " ne corresponds pas");
            throw new SaisieException();
        }
        return saisie.toUpperCase();
    }

    public static String Email(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXEMAIL)) {
            Fenetre.Fenetre("L'email ne corresponds pas");
            throw new SaisieException();
        }
        return saisie;
    }

    public static String BirthDate(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXDATE)) {
            Fenetre.Fenetre("la date ne corresponds pas");
            throw new SaisieException();
        }
        return saisie;
    }

    public static String SecuSocial(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXNUMSECU)){
            Fenetre.Fenetre("Le numéro de la sécurité social");
            throw new SaisieException();
        }
        return saisie;
    }

    public static String CodePostal(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXCODEPOSTAL)) {
            Fenetre.Fenetre("Le code postal ne corresponds pas");
            throw new SaisieException();
        }
        return saisie;
    }

    public static String Telephone(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXNUMTEL)){
            Fenetre.Fenetre("Le telephone ne corresponds pas");
            throw new SaisieException();
        }
        return saisie;
    }


}
