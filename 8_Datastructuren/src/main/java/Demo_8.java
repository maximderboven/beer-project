import be.kdg.bierproject.model.BierFactory;
import be.kdg.bierproject.model.Gisting;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * Maxim Derboven
 * 23/11/2021
 */
public class Demo_8 {
    public static void main(String[] args) {
        System.out.println("Bier Factory: ");
        System.out.println("Leeg Bier");
        System.out.println(BierFactory.newEmptyBier());
        System.out.println("Bier Cesar");
        System.out.println(BierFactory.newFilledBier("Bruges", Gisting.LAGE, LocalDate.of(1822,1,1),7.5,35,false));
        System.out.println("Random Bier");
        Stream.generate(BierFactory::newRandomBier).limit(30).forEach(System.out::println);
    }
}