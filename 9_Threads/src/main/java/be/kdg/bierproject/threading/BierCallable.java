package be.kdg.bierproject.threading;

import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.BierFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Maxim Derboven
 * @version 1.0 15/12/2021 16:42
 */
public class BierCallable implements Callable {
	private Predicate<Bier> filter;

	public BierCallable(Predicate<Bier> filter) {
		this.filter = filter;
	}

	@Override
	public Object call() throws Exception {
		return Stream.generate(BierFactory::newRandomBier).filter(filter).limit(1000).collect(Collectors.toList());
	}
}
