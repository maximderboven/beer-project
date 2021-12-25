package be.kdg.bierproject.service;

import be.kdg.bierproject.model.Bier;

import java.util.List;

/**
 * @author Maxim Derboven
 * @version 1.0 24/12/2021 17:18
 */
public interface BierenService {
	List<Bier> getAllBieren();
	void addBier(Bier b);
}
