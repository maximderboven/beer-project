import be.kdg.bierproject.kollections.*;
import be.kdg.bierproject.kollections.lists.ArrayList;
import be.kdg.bierproject.kollections.lists.LinkedList;
import be.kdg.bierproject.kollections.lists.List;
import be.kdg.bierproject.kollections.maps.HashMap;
import be.kdg.bierproject.kollections.maps.ListMap;
import be.kdg.bierproject.kollections.maps.Map;
import be.kdg.bierproject.kollections.sets.ArraySet;
import be.kdg.bierproject.kollections.sets.TreeSet;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.BierFactory;

import java.util.Random;

/**
 * Maxim Derboven
 * 23/11/2021
 */
public class PerformanceTester {

    public static List<Bier> randomList(int n) {
        List<Bier> myList = new LinkedList<>();
        new Random().ints(n).forEach(i -> myList.add(BierFactory.newRandomBier()));
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
        /* VOORBEELD UIT DE LES
            //add at beginning
    List<Student> aList = new ArrayList<>();
    Student testStudent = new Student(1, "Dummy");
    long start = System.currentTimeMillis();
    for(int i = 0; i < n; i++) {
        aList.add(0, testStudent);
    }
    long end = System.currentTimeMillis();
    System.out.printf("Adding %d elements in ArrayList at beginning: %d ms\n", n, (end - start));
    List<Student> lList = new LinkedList<>();
    start = System.currentTimeMillis();
    for(int i = 0; i < n; i++) {
        lList.add(0, testStudent);
    }
    end = System.currentTimeMillis();
    System.out.printf("Adding %d elements in LinkedList at beginning: %d ms\n", n, (end - start));
    //get at end
    start = System.currentTimeMillis();
    for(int i = 0; i < n; i++) {
        aList.get(aList.size() - 1);
    }
    end = System.currentTimeMillis();
    System.out.printf("Getting %d elements in ArrayList at end: %d ms\n", n, (end - start));
    start = System.currentTimeMillis();
    for(int i = 0; i < n; i++) {
        lList.get(lList.size() - 1);
    }
    end = System.currentTimeMillis();
    System.out.printf("Getting %d elements in LinkedList at end: %d ms\n", n, (end - start));
         */
        List<Bier> aList = new ArrayList<>();
        List<Bier> lList = new LinkedList<>();
        //add at beginning
        long startAddArrayList = System.currentTimeMillis();
        new Random().ints(n).forEach(i -> aList.add(0, BierFactory.newRandomBier()));
        long endAddArrayList = System.currentTimeMillis();
        System.out.println("Adding " + n + " to Arraylist: " + (endAddArrayList - startAddArrayList) + "ms.");

        long startAddLinkedList = System.currentTimeMillis();
        new Random().ints(n).forEach(i -> lList.add(0, BierFactory.newRandomBier()));
        long endAddLinkedList = System.currentTimeMillis();
        System.out.println("Adding " + n + " to LinkedList: " + (endAddLinkedList-startAddLinkedList) + "ms");

        //get at end
        long startGetArrayList = System.currentTimeMillis();
        new Random().ints(n).forEach(i -> aList.get(n-1));
        long endGetArrayList = System.currentTimeMillis();
        System.out.println("Getting " + n + " Bieren from ArrayList: " + (endGetArrayList-startGetArrayList) + "ms");

        long startGetLinkedList = System.currentTimeMillis();
        new Random().ints(n).forEach(i -> lList.get(n-1));
        long endGetLinkedList = System.currentTimeMillis();
        System.out.println("Getting " + n + " Bieren from LinkedList: " + (endGetLinkedList-startGetLinkedList) + "ms");
    }

    public static void testSelectionSort() {
        for (int n = 1000; n < 20000; n += 1000) {
            Kollections.selectionSort(randomList(n));
            System.out.println(n + ";" + Bier.compareCounter);
            Bier.compareCounter = 0;
        }
    }

    public static void testMergeSort() {
        for (int n = 1000; n < 20000; n += 1000) {
            Kollections.mergeSort(randomList(n));
            System.out.println(n + ";" + Bier.compareCounter);
            Bier.compareCounter = 0;
        }
    }

    private static Map<Bier, String> fillMap(int n, String type) {
        Map<Bier, String> map;
        if (type.equals("h")) {
            map = new HashMap<>();
        } else {
            map = new ListMap<>();
        }
        for (int i = 0; i < n; i++) {
            Bier b = BierFactory.newEmptyBier();
            b.setNaam("Bier" + i);
            map.put(b, "Ik ben de waarde " + b.getNaam());
        }
        return map;
    }

    public static void compareListMapToHashMap(int n) {
        Map<Bier, String> hmap = fillMap(n, "h");
        Map<Bier, String> lmap = fillMap(n, "l");
        Bier.equalsCounter = 0;

        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Bier b = BierFactory.newEmptyBier();
            b.setNaam("Bier" + i);
            hmap.get(b);
        }
        long end = System.nanoTime();
        System.out.println("Hashmap: n = 1000, equalcount = " + Bier.equalsCounter + " , nanosec = " + (end-start));

        Bier.equalsCounter = 0;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Bier b = BierFactory.newEmptyBier();
            b.setNaam("Pr" + i);
            lmap.get(b);
        }
        end = System.nanoTime();
        System.out.println("Listmap: n = 1000, equalcount = " + Bier.equalsCounter + " , nanosec = " + (end-start));
    }

    public static void compareArraySetToTreeSet() {
        Bier.equalsCounter = 0;
        Bier.compareCounter = 0;
        ArraySet<Bier> as = new ArraySet();
        TreeSet<Bier> ts = new TreeSet<>();
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            as.add(BierFactory.newEmptyBier());
        }
        long end = System.nanoTime();
        System.out.println("ArraySet, n = 1000: equalscount: " + Bier.equalsCounter);
        System.out.println("ArraySet, n = 1000: comparecount: " + Bier.compareCounter);
        System.out.println("ArraySet, n = 1000: nano:  " + (end - start));

        Bier.equalsCounter = 0;
        Bier.compareCounter = 0;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            ts.add(BierFactory.newEmptyBier());
        }
        end = System.nanoTime();
        System.out.println("TreeSet, n = 1000: equalscount: " + Bier.equalsCounter);
        System.out.println("TreeSet, n = 1000: comparecount: " + Bier.compareCounter);
        System.out.println("TreeSet, n = 1000: nano:  " + (end - start));
        Bier.equalsCounter = 0;
        Bier.compareCounter = 0;
    }
}

