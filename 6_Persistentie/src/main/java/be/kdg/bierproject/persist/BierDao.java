package be.kdg.bierproject.persist;

import be.kdg.bierproject.model.Bier;

import java.util.List;

/**
 * Maxim Derboven
 * 26/10/2021
 */
public interface BierDao {
    boolean insert(Bier bier);
    boolean delete(String naam);
    boolean update(Bier bier);
    Bier retrieve(String naam);
    List<Bier> sortedOn(String query);
}
