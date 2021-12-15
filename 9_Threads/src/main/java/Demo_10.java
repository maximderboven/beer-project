import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.BierFactory;
import be.kdg.bierproject.model.Gisting;
import be.kdg.bierproject.threading.BierAttacker;
import be.kdg.bierproject.threading.BierRunnable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Maxim Derboven
 * @version 1.0 15/12/2021 16:18
 */
public class Demo_10 {
	public static void main(String[] args) {
		List<Bier> bierlist = Stream.generate(BierFactory::newRandomBier).limit(1000).collect(Collectors.toList());
		BierAttacker bier1 = new BierAttacker(bier -> bier.getGisting() == Gisting.HOGE,bierlist);
		BierAttacker bier2 = new BierAttacker(bier -> bier.getBitterheidsgraad() > 10,bierlist);
		BierAttacker bier3 = new BierAttacker(Bier::isTrappist,bierlist);
		Thread tr1 = new Thread(bier1);
		Thread tr2 = new Thread(bier2);
		Thread tr3 = new Thread(bier3);

		tr1.start();
		tr2.start();
		tr3.start();

		try {
			tr1.join();
			tr2.join();
			tr3.join();
		} catch (InterruptedException e) {
			System.out.println("Error :((");
		}

		long gisting =  bierlist.stream().filter(bier -> bier.getGisting() == Gisting.HOGE).count();;
		long bitterheid = bierlist.stream().filter(bier -> bier.getBitterheidsgraad() > 10).count();
		long trappist = bierlist.stream().filter(Bier::isTrappist).count();

		//of for lus om te checken per item
		//for (Bier b : bierlist) {
		//}

		System.out.println("Na zuivering:");
		System.out.println("Aantal Hoge Gisting: " + gisting);
		System.out.println("Aantal Bitterder dan 10: " + bitterheid);
		System.out.println("Aantal trapisten: " + trappist);
	}
}
