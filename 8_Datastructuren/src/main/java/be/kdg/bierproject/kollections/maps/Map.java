package be.kdg.bierproject.kollections.maps;

import be.kdg.bierproject.kollections.Collection;
import be.kdg.bierproject.kollections.sets.Set;

public interface Map<K, V> {
    void put(K key, V value);
    V get(K key);
    Collection<V> values();
    Set<K> keySet();
    int size();
}
