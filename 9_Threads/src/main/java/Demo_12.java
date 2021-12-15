import be.kdg.bierproject.model.BierFactory;
import be.kdg.bierproject.model.Bieren;

import java.util.stream.Stream;

/**
 * @author Maxim Derboven
 * @version 1.0 15/12/2021 16:53
 */
public class Demo_12 {
	public static void main(String[] args) {
		Bieren bieren = new Bieren();
		Runnable runnable = () -> Stream.generate(BierFactory::newRandomBier).limit(5000).forEach(bieren::add);

		Thread tr1 = new Thread(runnable);
		Thread tr2 = new Thread(runnable);
		tr1.start();
		tr2.start();

		try {
			tr1.join();
			tr2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Na het toevoegen door 2 threads met elk 5000 objecten: bieren " + bieren.getSize());
	}
}
