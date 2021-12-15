package be.kdg.bierproject.threading;

import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.BierFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Maxim Derboven
 * @version 1.0 15/12/2021 15:17
 */
public class BierRunnable implements Runnable {
	private Predicate<Bier> filter;
	private List<Bier> bierList = new ArrayList<>();

	public BierRunnable(Predicate<Bier> filter) {
		this.filter = filter;
	}

	@Override
	public void run() {
		bierList = Stream.generate(BierFactory::newRandomBier).filter(filter).limit(1000).collect(Collectors.toList());
	}

	public List<Bier> getBierList() {
		return bierList;
	}
}
