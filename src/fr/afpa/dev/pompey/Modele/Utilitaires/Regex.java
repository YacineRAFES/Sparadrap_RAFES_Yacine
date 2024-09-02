package fr.afpa.dev.pompey.Modele.Utilitaires;

public class Regex {
    public static final String REGEXNUMSECU = "/^([1-37-8])([0-9]{2})(0[0-9]|[2-35-9][0-9]|[14][0-2])((0[1-9]|[1-8][0-9]|9[0-69]|2[abAB])(00[1-9]|0[1-9][0-9]|[1-8][0-9]{2}|9[0-8][0-9]|990)|(9[78][0-9])(0[1-9]|[1-8][0-9]|90))(00[1-9]|0[1-9][0-9]|[1-9][0-9]{2})(0[1-9]|[1-8][0-9]|9[0-7])$/x";
    public static final String REGEXINT = "^[0-9]+$";
    public static final String REGEXSTRING = "^[a-zA-Z0-9_.-]*$";
    public static final String REGEXEMAIL = "^(?=.{1,64}@)[\\p{L}0-9\\+_-]+(\\.[\\p{L}0-9\\+_-]+)*@"
            + "[^-][\\p{L}0-9\\+-]+(\\.[\\p{L}0-9\\+-]+)*(\\.[\\p{L}]{2,})$";
    public static final String REGEXNUMTEL = "^\\d{8,15}$";
    public static final String REGEXNOMPRENOM = "^([a-zA-Za-zàáâäçèéêëìíîïñòóôöùúûü]+[,.]?[ ]?|[a-zA-Za-zàáâäçèéêëìíîïñòóôöùúûü]+[\\\\'-]?)+$";
    public static final String REGEXCODEPOSTAL = "^\\d{2,5}$";
    public static final String REGEXPRIX = "^\\d+(\\.\\d{1,2})?$";
}
