import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Gisting;
import be.kdg.bierproject.threading.BierCallable;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Maxim Derboven
 * @version 1.0 15/12/2021 16:44
 */
public class Demo_11 {
	private static int TESTCOUNT = 0;
	private static double tijd = 0;

	public static void main(String[] args) {

		while (TESTCOUNT <= 100) {
			//Callable<List<Bier>>
			BierCallable bier1 = new BierCallable(bier -> bier.getGisting() == Gisting.HOGE);
			BierCallable bier2 = new BierCallable(bier -> bier.getBitterheidsgraad() > 10);
			BierCallable bier3 = new BierCallable(Bier::isTrappist);

			ExecutorService pool = Executors.newFixedThreadPool(3);

			long start = System.currentTimeMillis();
			Future f1 = pool.submit(bier1);
			Future f2 = pool.submit(bier2);
			Future f3 = pool.submit(bier3);
			try {
				f1.get();
				f2.get();
				f3.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			pool.shutdown();
			long end = System.currentTimeMillis();

			tijd += (end - start);
			TESTCOUNT++;
		}
		System.out.printf("3 Futures verzamelen elk 1000 bieren (gemiddelde uit 100 runs): %.2f ms", (double) tijd / 100);
	}
}
