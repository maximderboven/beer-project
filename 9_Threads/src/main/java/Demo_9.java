import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import be.kdg.bierproject.model.Gisting;
import be.kdg.bierproject.threading.BierRunnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Maxim Derboven
 * @version 1.0 22/09/2021 14:38
 */
public class Demo_9 {
	private static int TESTCOUNT = 0;
	private static double tijd = 0;

	public static void main(String[] args) {

		while (TESTCOUNT <= 100) {
			BierRunnable bier1 = new BierRunnable(bier -> bier.getGisting() == Gisting.HOGE);
			BierRunnable bier2 = new BierRunnable(bier -> bier.getBitterheidsgraad() > 10);
			BierRunnable bier3 = new BierRunnable(Bier::isTrappist);
			Thread tr1 = new Thread(bier1);
			Thread tr2 = new Thread(bier2);
			Thread tr3 = new Thread(bier3);

			long start = System.currentTimeMillis();
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
			long end = System.currentTimeMillis();

			bier1.getBierList().stream().limit(5).forEach(System.out::println);
			bier2.getBierList().stream().limit(5).forEach(System.out::println);
			bier3.getBierList().stream().limit(5).forEach(System.out::println);

			tijd += (end - start);
			TESTCOUNT++;
		}
		System.out.printf("3 threads verzamelen elk 1000 bieren (gemiddelde uit 100 runs): %.2f ms", (double) tijd / 100);
	}
}
