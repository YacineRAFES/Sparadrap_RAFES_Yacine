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

    public static String String(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXSTRING)) {
            Fenetre.Fenetre("La saisie ne corresponds pas, seuls les caractères sont acceptés");
            throw new SaisieException();
        }
        return saisie;
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
            Fenetre.Fenetre("la date de naissance ne corresponds pas");
            throw new SaisieException();
        }
        return saisie;
    }

    public static String SecuSocial(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXNUMSECU)){
            Fenetre.Fenetre("Vérifier le numéro de la sécurité social si il est bien composé de 15 chiffres");
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

    public static String Prix(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXPRIX)) {
            Fenetre.Fenetre("Le prix ne corresponds pas");
            throw new SaisieException();
        }
        return saisie;
    }

    public static int Quantite(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXQUANTITE)) {
            Fenetre.Fenetre("Le quantite ne corresponds pas, seuls les entiers sont acceptés");
            throw new SaisieException();
        }
        try {
            return Integer.parseInt(saisie);
        } catch (NumberFormatException e) {
            Fenetre.Fenetre("La quantité ne doit pas être supérieure à 2000000000");
            throw new SaisieException();
        }
    }


}
