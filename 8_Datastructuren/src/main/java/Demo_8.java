import be.kdg.bierproject.kollections.*;
import be.kdg.bierproject.kollections.lists.List;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.BierFactory;
import be.kdg.bierproject.model.Gisting;

import java.time.LocalDate;
import java.util.stream.IntStream;
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
        System.out.println();

        System.out.println("Bier Cesar");
        System.out.println(BierFactory.newFilledBier("Bruges", Gisting.LAGE, LocalDate.of(1822,1,1),7.5,35,false));
        System.out.println();

        System.out.println("Random Bier");
        Stream.generate(BierFactory::newRandomBier).limit(30).forEach(System.out::println);
        System.out.println();

        System.out.println("PerformanceTester - Compare Array and Linked List");
        PerformanceTester.compareArrayListAndLinkedList(20000);
        System.out.println();

        System.out.println("PerformanceTester - SelectionSort");
        List<Bier> selectionlist = PerformanceTester.randomList(30);
        Kollections.selectionSort(selectionlist);
        for (int i = 0; i < selectionlist.size(); i++){
            System.out.println(selectionlist.get(i));
        }
        System.out.println();

        System.out.println("PerformanceTester - MergeSort");
        List<Bier> mergelist = PerformanceTester.randomList(30);
        Kollections.mergeSort(mergelist);
        for (int i = 0; i < mergelist.size(); i++){
            System.out.println(mergelist.get(i));
        }
        System.out.println();

        System.out.println("PerformanceTester - Test MergeSort");
        //PerformanceTester.testMergeSort();

        System.out.println("PerformanceTester - Test SelectionSort");
        //PerformanceTester.testSelectionSort();


        System.out.println("PerformanceTester - QuickSort");
        List<Bier> quicksortlist = PerformanceTester.randomList(30);
        Kollections.quickSort(quicksortlist);
        for (int i = 0; i < quicksortlist.size(); i++){
            System.out.println(quicksortlist.get(i));
        }
        System.out.println();

        System.out.println("PerformanceTester - Binair & Linear Search");
        List<Bier> searchlist = PerformanceTester.randomList(29);
        Bier tezoeken = BierFactory.newFilledBier("Bruges", Gisting.LAGE, LocalDate.of(1822,1,1),7.5,35,false);
        searchlist.add(tezoeken);

        System.out.println("Linear Search Bruges: ");
        System.out.println(Kollections.lineairSearch(searchlist, tezoeken));

        System.out.println("Binair Search Bruges: ");
        System.out.println(Kollections.binarySearch(searchlist, tezoeken));

        Bier onbekend = BierFactory.newEmptyBier();
        System.out.println("Linear Search Onbekend: ");
        System.out.println(Kollections.lineairSearch(searchlist,onbekend));

        System.out.println("Binair Search Onbekend: ");
        System.out.println(Kollections.binarySearch(searchlist, onbekend));

       System.out.println();

        System.out.println("Get Compare Listmap to Hashmap:");
        PerformanceTester.compareListMapToHashMap(1000);

        System.out.println("Compare ArraySet to TreeSet:");
        PerformanceTester.compareArraySetToTreeSet();
    }
}