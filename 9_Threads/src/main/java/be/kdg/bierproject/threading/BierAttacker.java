package be.kdg.bierproject.threading;

import be.kdg.bierproject.model.Bier;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Maxim Derboven
 * @version 1.0 15/12/2021 16:13
 */
public class BierAttacker implements Runnable {
	private Predicate<Bier> filter;
	private List<Bier> bierList;

	public BierAttacker(Predicate<Bier> filter, List<Bier> bierList) {
		this.filter = filter;
		this.bierList = bierList;
	}

	@Override
	public void run() {
		synchronized (bierList) {
			bierList.removeIf(filter);
		}
	}
}
