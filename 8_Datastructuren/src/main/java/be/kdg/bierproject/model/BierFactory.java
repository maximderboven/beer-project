package be.kdg.bierproject.model;

import java.time.LocalDate;
import java.util.Random;

/**
 * Maxim Derboven
 * 23/11/2021
 */
public class BierFactory {
    
    private BierFactory() {
    }
    
    public static Bier  newEmptyBier() {
        return new Bier();
    }
    
    public static Bier newFilledBier(String naam, Gisting gisting, LocalDate gebrouwenSinds, double alcoholPercentage, int bitterheidsgraad, boolean trappist) {
        return new Bier(naam, gisting, gebrouwenSinds, alcoholPercentage, bitterheidsgraad, trappist);
    }
    
    public static Bier newRandomBier() {
        Random r = new Random();
        Gisting[] gisting = Gisting.values();
        String naam = generateString(r.nextInt(10), 1, true);
        LocalDate gebrouwenSinds = LocalDate.of((r.nextInt(100)+1900), (r.nextInt(12) + 1), (r.nextInt(27) + 1));
        double alcoholpercentage = r.nextDouble() + 0.1;
        int bitterheidsgraad = r.nextInt(40)+10;
        boolean trappist = r.nextBoolean();
        return new Bier(naam, gisting[r.nextInt(5)],gebrouwenSinds,alcoholpercentage,bitterheidsgraad,trappist);
    }

    private static String generateString(int maxWordLength, int wordCount, boolean camelCase) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < wordCount; i++) {
            sb.append((char) (r.nextInt(26) + 'A'));
            for(int j = 0; j < maxWordLength-1; j++) {
                sb.append((char) (r.nextInt(26) + 'a'));
            }
            if (wordCount>1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }


}
