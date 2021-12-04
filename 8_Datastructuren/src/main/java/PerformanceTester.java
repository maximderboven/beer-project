import be.kdg.bierproject.kollections.*;
import be.kdg.bierproject.model.Bier;

/**
 * Maxim Derboven
 * 23/11/2021
 */
public class PerformanceTester {
    public static void compareArrayListAndLinkedList(int n) {
        //add at beginning
        List<Bier> aList = new ArrayList<>();
        Bier testbier = new Bier();
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            aList.add(0, testbier);
        }
        long end = System.currentTimeMillis();
        System.out.printf("Adding %d elements in ArrayList at beginning: %d ms\n", n, (end - start));


        List<Bier> lList = new LinkedList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            lList.add(0, testbier);
        }
        end = System.currentTimeMillis();
        System.out.printf("Adding %d elements in LinkedList at beginning: %d ms", n, (end - start));

        //get at end
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            aList.get(aList.size() - 1);
        }
        end = System.currentTimeMillis();
        System.out.printf("Getting %d elements in ArrayList at end: %d ms\n", n, (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            lList.get(lList.size() - 1);
        }
        end = System.currentTimeMillis();
        System.out.printf("Getting %d elements in LinkedList at end: %d ms\n", n, (end - start));
    }
}
