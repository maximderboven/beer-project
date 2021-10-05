import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.generics.PriorityQueue;
import be.kdg.bierproject.model.Bier;

import java.util.Random;

/**
 * @author Maxim Derboven
 * @version 1.0 29/09/2021 23:25
 */
public class Demo_2 {
    public static void main(String[] args) {
        var myQueue = new PriorityQueue<>();
        myQueue.enqueue("Tokio", 2);
        myQueue.enqueue("Denver", 5);
        myQueue.enqueue("Rio", 2);
        myQueue.enqueue("Oslo", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van Tokio: " + myQueue.search("Tokio"));
        System.out.println("positie van Nairobi: " + myQueue.search("Nairobi"));
        for (var i = 0; i < 4; i++) {
            System.out.println("Dequeue: " + myQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + myQueue.getSize());


        var bierenQueue = new PriorityQueue<Bier>();
        var data = Data.getData();
        var random = new Random();

        for (var bier : data){
            bierenQueue.enqueue(bier, random.nextInt(5)+1);
        }

        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(bierenQueue);
        System.out.println("aantal: " + bierenQueue.getSize());
        System.out.println("positie van Duvel: " + bierenQueue.search(data.get(5)));
        System.out.println("positie van Brugse Zot: " + bierenQueue.search(data.get(6)));
        for (var i = 0; i < 10; i++) {
            System.out.println("Dequeue: " + bierenQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + bierenQueue.getSize());
    }
}
