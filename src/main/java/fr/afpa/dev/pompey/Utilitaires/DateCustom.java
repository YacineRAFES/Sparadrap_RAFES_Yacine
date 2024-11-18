package fr.afpa.dev.pompey.Utilitaires;

public class DateCustom {
    public DateCustom() {
    }

    public static java.sql.Date convertStringToDate(String date) {
        java.sql.Date dateSQL = null;
        try {
            java.util.Date date1 = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(date);
            dateSQL = new java.sql.Date(date1.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return dateSQL;
    }

    //Convert Localdate to Date
    public static java.sql.Date convertLocalDateToDate(java.time.LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    //Create datesql now
    public static java.sql.Date DateNow(){
        return new java.sql.Date(new java.util.Date().getTime());
    }
}
