import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import be.kdg.bierproject.model.Gisting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Maxim Derboven
 * @version 1.0 22/09/2021 14:38
 */
public class Demo_1 {
    public static void main(String[] args) {
        //System.out.println("Gradle working!");
        Bieren bieren = new Bieren();
        List<Bier> bierenLijst = new ArrayList<>(Data.getData());
        for (Iterator<Bier> iterator = bierenLijst.iterator(); iterator.hasNext(); ) {
            Bier next = iterator.next();
            bieren.add(next);
        }

        System.out.println("Search methode:");
        System.out.println(bieren.search("Duvel"));
        System.out.println("\nGet size:");
        System.out.print(bieren.getSize());

        //remove
        bieren.remove("Jupiler");

        System.out.println("\nGet size:");
        System.out.print(bieren.getSize());


        System.out.println("\n\nBieren gesorteerd op datum:");
        List<Bier> datum = new ArrayList<>(bieren.sortedOnGebrouwenSinds());
        for (Bier bier : datum) {
            System.out.println(bier.toString());
        }

        System.out.println("\n\nBieren gesorteerd op naam:");
        List<Bier> naam = new ArrayList<>(bieren.sortedOnName());
        for (Bier bier : naam) {
            System.out.println(bier.toString());
        }

        System.out.println("\n\nBieren gesorteerd op alcoholpercentage:");
        List<Bier> alcoholpercentage = new ArrayList<>(bieren.sortedOnAlcoholpercentage());
        for (Bier bier : alcoholpercentage) {
            System.out.println(bier.toString());
        }

        System.out.println("\n\n Leeg bier:");
        System.out.println(new Bier().toString());
        System.out.println("\n\n Falend bier:");
        try {
            System.out.println(new Bier("", Gisting.ONBEKEND, LocalDate.of(2050, 1, 1), 30, -5, true).toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}
