package be.kdg.bierproject.generics;

import java.util.*;

/**
 * @author Maxim Derboven
 * @version 1.0 29/09/2021 23:29
 */
public class PriorityQueue<T> implements FIFOQueue<T> {

    private Map<Integer, LinkedList<T>> queue = new TreeMap<>(Comparator.reverseOrder());

    @Override
    public boolean enqueue(T element, int priority) {
        if (search(element) == -1) {
            if (queue.containsKey(priority)) {
                queue.get(priority).add(element);
            } else {
                LinkedList<T> lijst = new LinkedList<T>();
                lijst.add(element);
                queue.put(priority, lijst);
            }
        }
        return false;
    }

    @Override
    public T dequeue() {
        if (queue.size() == 0) return null;
        for (int priority : queue.keySet()) {
            LinkedList<T> list = queue.get(priority);
            T item = list.remove (0);
            if (list.size() == 0) queue.remove (priority);
            return item;
        }
        return null;

//        for (LinkedList<T> value : queue.values()) {
//            if (value.size() > 0) {
//                return value.remove(0);
//                //f (value.size() == 0) {
//                //queue.remove
//                //} //?
//            }
//        }

    }


    @Override
    public int search(T element) {
        int positie = 1;
        for (LinkedList<T> value : queue.values()) {
            if (value.contains(element)) {
                return positie + value.indexOf(element);
            } else {
                positie += value.size();
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Integer integer : queue.keySet()) {
            for (Object next : queue.get(integer)) {
                str.append(integer).append(": ").append(next).append("\n");
            }
        }
        return str.toString();
    }

    @Override
    public int getSize() {
        int size = 0;
        for (LinkedList<T> value : queue.values()) {
            size += value.size();
        }
        return size;
    }
}
