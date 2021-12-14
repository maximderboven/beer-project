package be.kdg.bierproject.kollections.sets;

import be.kdg.bierproject.kollections.Collection;
import be.kdg.bierproject.kollections.lists.List;

public interface Set<E> extends Collection<E> {
    List<E> toList();
}
