package be.kdg.bierproject.util;

import be.kdg.bierproject.model.Bier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * @author Maxim Derboven
 * @version 1.0 19/10/2021 21:11
 */
public class BierFunctions {
    public static <T> List<T> filteredList(List<T> bierList, Predicate<T> predicate) {
        return bierList.stream().filter(predicate).collect(Collectors.toList());
    }
    public static <T> Double average (List<T> bierList, ToDoubleFunction<T> mapper) {
        return bierList.stream().mapToDouble(mapper).sum();
    }

    public static <T> long countIf(List<T> bierList, Predicate<T> predicate) {
        return (int) bierList.stream().filter(predicate).count();
    }
}
