package fr.afpa.dev.pompey.Utilitaires;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Verification {
    public Verification() {}

    public static String NomPrenom(String saisie, String type) throws SaisieException {
        if(!saisie.matches(Regex.REGEXNOMPRENOM)) {
            Fenetre.Fenetre("Le " + type + " ne correspond pas");
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

    /**
     * Vérifie si la saisie correspond à un email
     * @param saisie
     * @return
     * @throws SaisieException
     */
    public static String Email(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXEMAIL)) {
            Fenetre.Fenetre("L'email ne corresponds pas");
            throw new SaisieException();
        }
        return saisie;
    }

    public static String BirthDate(String saisie) throws SaisieException {
        //get the current date
        LocalDate dateActuel = LocalDate.now();
        // On vérifie la regex de la date de naissance
        if (!saisie.matches(Regex.REGEXDATE)) {
            Fenetre.Fenetre("La date de naissance ne correspond pas");
            throw new SaisieException();
        }
        //On accepte les date de naissance ceux qui ont 12 ans
        LocalDate dateNaissance = LocalDate.parse(saisie, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if(dateNaissance.isAfter(dateActuel.minusYears(12))) {
            Fenetre.Fenetre("La date de naissance doit être antérieure à 12 ans");
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
            Fenetre.Fenetre("La quantite ne corresponds pas, seuls les entiers sont acceptés");
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
