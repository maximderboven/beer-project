import be.kdg.bierproject.kollections.*;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.BierFactory;

import java.util.Random;

/**
 * Maxim Derboven
 * 23/11/2021
 */
public class PerformanceTester {

    //TODO: change this method for own use
    public static List<Bier> randomList(int n) {
        List<Bier> myList = new LinkedList<>();
        new Random().ints(n).forEach(i -> myList.add(BierFactory.newRandomBier()));
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
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
}

