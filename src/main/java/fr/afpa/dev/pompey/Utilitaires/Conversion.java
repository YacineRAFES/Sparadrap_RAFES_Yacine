package fr.afpa.dev.pompey.Utilitaires;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversion {
    public static String convertirDate(String date){
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat toFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateconverti;
        try {
            dateconverti = fromFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
        return toFormat.format(dateconverti);
    }
}
