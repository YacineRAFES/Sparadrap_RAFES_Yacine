package fr.afpa.dev.pompey.Utilitaires;

import java.time.LocalDate;
import java.util.Random;

public class Generator {
    public Generator(){

    }

    public static LocalDate DateNow(){
        return LocalDate.now();
    }

    public static LocalDate generateRandomDate() {
        Random random = new Random();

        // Generate random year between 1900 and 2100
        int year = random.nextInt(2100 - 1900 + 1) + 1900;

        // Generate random month between 1 and 12
        int month = random.nextInt(12) + 1;

        // Generate random day based on the month and year
        int day;
        if (month == 2) {
            // Check for leap year
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                day = random.nextInt(29) + 1;
            } else {
                day = random.nextInt(28) + 1;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = random.nextInt(30) + 1;
        } else {
            day = random.nextInt(31) + 1;
        }

        // Create LocalDate object
        LocalDate randomDate = LocalDate.of(year, month, day);
        return randomDate;
    }


}
