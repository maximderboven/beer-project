package be.kdg.bierproject.generics;

/**
 * @author Maxim Derboven
 * @version 1.0 29/09/2021 23:27
 */
public interface FIFOQueue<T> {
    boolean enqueue(T element, int priority);

    T dequeue();

    int search(T element);

    int getSize();
}
