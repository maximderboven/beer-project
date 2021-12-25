package be.kdg.bierproject.service;

import be.kdg.bierproject.database.BierDao;
import be.kdg.bierproject.exceptions.BierException;
import be.kdg.bierproject.model.Bier;

import java.util.List;

/**
 * @author Maxim Derboven
 * @version 1.0 24/12/2021 17:18
 */
public class BierenServiceImpl implements BierenService {
	private BierDao bierenDao;

	public BierenServiceImpl(BierDao bierenDao) {
		this.bierenDao = bierenDao;
	}

	@Override
	public List<Bier> getAllBieren() throws BierException,IllegalArgumentException {
		return bierenDao.getAllBieren();
	}

	@Override
	public void addBier(Bier b) throws BierException,IllegalArgumentException {
		bierenDao.insert(b);
	}
}
