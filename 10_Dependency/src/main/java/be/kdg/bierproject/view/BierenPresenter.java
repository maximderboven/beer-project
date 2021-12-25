package be.kdg.bierproject.view;

import be.kdg.bierproject.exceptions.BierException;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.service.BierenService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Maxim Derboven
 * @version 1.0 24/12/2021 19:54
 */
public class BierenPresenter {
	private static final Logger logger = Logger.getLogger(BierenPresenter.class.getName());
	private BierenView bierenView;
	private BierenService bierenService;

	public BierenPresenter(BierenView bierenView, BierenService bierenService) {
		this.bierenView = bierenView;
		this.bierenService = bierenService;
		loadBieren();
		bierenView.getBtnOpslaan().setOnAction(event -> {
			try {
				bierenService.addBier(new Bier(bierenView.getTfNaam().getText(),
						bierenView.getDpBrouwdatum().getValue(), Double.parseDouble(bierenView.getTfAlcoholpercentage().getText())));
				loadBieren();
			} catch (BierException | IllegalArgumentException e) {
				new Alert(Alert.AlertType.ERROR, "Unable to load bieren: " + e.getMessage()).showAndWait();
			}
		});
	}

	private void loadBieren() {
		logger.info("Bieren laden");
		try {
			List<Bier> myList = bierenService.getAllBieren();
			bierenView.getTvBieren().setItems(FXCollections.observableList(myList));
		} catch (BierException be) {
			logger.warning("Unable to load bieren: " + be.getMessage());
			new Alert(Alert.AlertType.ERROR, "Unable to load bieren: " + be.getMessage()).showAndWait();
		}
	}

}

