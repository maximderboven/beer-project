import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import be.kdg.bierproject.util.BierFunctions;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Maxim Derboven
 * @version 1.0 22/09/2021 14:38
 */
public class Demo_4 {
    public static void main(String[] args) {
        //System.out.println("Gradle working!");
        Bieren bieren = new Bieren();
        List<Bier> bierenLijst = new ArrayList<>(Data.getData());
        bierenLijst.forEach(bieren::add);
        System.out.println("\n\nBieren gesorteerd op datum:");
        bieren.sortedBy(Bier::getGebrouwenSinds).stream().map(Bier::toString).forEach(System.out::println);

        System.out.println("\n\nBieren gesorteerd op naam:");
        bieren.sortedBy(Bier::getNaam).stream().map(Bier::toString).forEach(System.out::println);

        System.out.println("\n\nBieren gesorteerd op alcoholpercentage:");
        bieren.sortedBy(Bier::getAlcoholPercentage).stream().map(Bier::toString).forEach(System.out::println);

        System.out.println("Bier met alcohol > 6:");
        System.out.println(BierFunctions.filteredList(bierenLijst, bier -> bier.getAlcoholPercentage() > 6));
        System.out.println("Bier met du");
        System.out.println(BierFunctions.filteredList(bierenLijst, bier -> bier.getNaam().toLowerCase().contains("du")));
        System.out.println("Bier gebrouwen in 2008");
        System.out.println(BierFunctions.filteredList(bierenLijst, bier -> bier.getGebrouwenSinds().getYear() == 2008));

        System.out.printf("Gemiddelde bitterheid: %1f EBU\n", BierFunctions.average(bierenLijst, Bier::getBitterheidsgraad));
        System.out.printf("Gemiddelde alcoholpercentage: %1f mln\n", BierFunctions.average(bierenLijst, Bier::getAlcoholPercentage));

        System.out.printf("Aantal bieren met alcohol > 6: %d\n", BierFunctions.countIf(bierenLijst, bier -> bier.getAlcoholPercentage() > 6));
        System.out.printf("Aantal bieren gebrouwen in 2008: %d\n", BierFunctions.countIf(bierenLijst, bier -> bier.getGebrouwenSinds().getYear() == 2008));

        List<Bier> bierenLijst2 = new ArrayList<>(Data.getData());
        System.out.printf("Aantal bieren gebrouwen na 1950: %d\n", bierenLijst2.stream().filter(b -> b.getGebrouwenSinds().getYear() > 1950).count());
        System.out.println("Alle bieren gesorteerd op brouwing en nadien op alcoholpercentage");
        bierenLijst2.stream().sorted(Comparator.comparing(Bier::getGisting).thenComparing(Bier::getAlcoholPercentage)).forEach(System.out::println);
        System.out.println("Alle biernamen in hoofdletters, omgekeerd gesorteerd en zonder dubbels");
        System.out.println(bierenLijst2.stream().map(Bier::getNaam).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.joining(", ")).toUpperCase());
        System.out.printf("Willekeurig biertje met meer dan 7%% aclohol: %s\n", bierenLijst2.stream().filter(b -> b.getAlcoholPercentage() > 7).findAny().get().getNaam());
        System.out.printf("Het bitterste biertje: %s\n", bierenLijst2.stream().max(Comparator.comparingInt(Bier::getBitterheidsgraad)).get().getNaam());
        System.out.printf("Meest alcoholische biertje: %s\n", bierenLijst2.stream().max(Comparator.comparingDouble(Bier::getAlcoholPercentage)).get().getNaam());
        System.out.printf("lijst met gesorteerde biertjes die beginnen met de letter 'C': %s\n", bierenLijst2.stream().map(Bier::getNaam).filter(n -> n.startsWith("C")).sorted().collect(Collectors.toUnmodifiableList()));

        Map<Boolean, List<Bier>> list = bierenLijst2.stream().collect(Collectors.partitioningBy(b -> b.getGebrouwenSinds().getYear() < 2000));
        System.out.println("Bieren gebrouwen na 2000");
        list.get(false).forEach(System.out::println);
        System.out.println("Bieren gebrouwen voor 2000:");
        list.get(true).forEach(System.out::println);
        System.out.println("Alle bieren gegroepeerd per Gisting: ");
        System.out.println(bierenLijst2.stream().collect(Collectors.groupingBy(Bier::getGisting)));



    }
}
