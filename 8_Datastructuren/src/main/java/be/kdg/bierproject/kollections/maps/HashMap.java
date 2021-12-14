package be.kdg.bierproject.kollections.maps;

import be.kdg.bierproject.kollections.lists.ArrayList;
import be.kdg.bierproject.kollections.lists.List;
import be.kdg.bierproject.kollections.sets.ArraySet;
import be.kdg.bierproject.kollections.sets.Set;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 100;

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int size = 0;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        buckets = new Node[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> node = new Node<K, V>(key, value);
        int hash = hash(key);
        if (buckets[hash] != null) {
            node.next = buckets[hash];
        }
        buckets[hash] = node;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        if (buckets[hash] == null) return null;
        Node<K, V> node = buckets[hash];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>(size);
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        }
        return values;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new ArraySet<>();
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                keySet.add(node.key);
                node = node.next;
            }
        }
        return keySet;
    }


}
