package be.kdg.bierproject;

import be.kdg.bierproject.database.BierDao;
import be.kdg.bierproject.database.BierDbDao;
import be.kdg.bierproject.service.BierenService;
import be.kdg.bierproject.service.BierenServiceImpl;
import be.kdg.bierproject.view.BierenPresenter;
import be.kdg.bierproject.view.BierenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * @author Maxim Derboven
 * @version 1.0 24/12/2021 20:13
 */
public class Main extends Application {
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		logger.info("Starting Bieren System on thread: " + Thread.currentThread().getName());
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.info("Running start methode on thread: " + Thread.currentThread().getName());
		BierDao bierDbDao = BierDbDao.getInstance("db/bierendb.db");
		BierenView bierenView = new BierenView();
		BierenService bierenService = new BierenServiceImpl(bierDbDao);
		new BierenPresenter(bierenView, bierenService);

		primaryStage.setScene(new Scene(bierenView));
		primaryStage.show();
	}
}

