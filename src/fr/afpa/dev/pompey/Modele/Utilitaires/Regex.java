package fr.afpa.dev.pompey.Modele.Utilitaires;

public class Regex {
    public static final String REGEXNUMSECU = "^\\d{15}$";
    public static final String REGEXINT = "^[0-9]+$";
    public static final String REGEXSTRING = "^[a-zA-Z0-9_.-]*$";
    public static final String REGEXEMAIL = "^(?=.{1,64}@)[\\p{L}0-9\\+_-]+(\\.[\\p{L}0-9\\+_-]+)*@"
            + "[^-][\\p{L}0-9\\+-]+(\\.[\\p{L}0-9\\+-]+)*(\\.[\\p{L}]{2,})$";
    public static final String REGEXNUMTEL = "^\\d{8,15}$";
    public static final String REGEXNOMPRENOM = "^([a-zA-Za-zàáâäçèéêëìíîïñòóôöùúûü]+[,.]?[ ]?|[a-zA-Za-zàáâäçèéêëìíîïñòóôöùúûü]+[\\\\'-]?)+$";
    public static final String REGEXCODEPOSTAL = "^\\d{2,5}$";
    public static final String REGEXPRIX = "^\\d+(\\.\\d{1,2})?$";
    public static final String REGEXDATE = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public static final String REGEXQUANTITE = "^\\d+$";
}
