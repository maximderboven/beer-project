package be.kdg.bierproject.view;

import be.kdg.bierproject.model.Bier;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * @author Maxim Derboven
 * @version 1.0 24/12/2021 19:53
 */
public class BierenView extends BorderPane {
	private static final Logger logger = Logger.getLogger(BierenView.class.getName());
	private TableView tvBieren;
	private TextField tfNaam;
	private TextField tfAlcoholpercentage;
	private DatePicker dpBrouwdatum;
	private Button btnOpslaan;

	@SuppressWarnings("unchecked")
	public BierenView() {
		logger.info("Opzetten van de view");
		tvBieren = new TableView<>();
		tfNaam = new TextField();
		tfNaam.setPromptText("Naam");
		tfAlcoholpercentage = new TextField();
		tfAlcoholpercentage.setPromptText("Alcohol %");
		dpBrouwdatum = new DatePicker();
		dpBrouwdatum.setPromptText("Brouwdatum");
		btnOpslaan = new Button("Opslaan");
		btnOpslaan.setMinWidth(Button.USE_PREF_SIZE);
		super.setCenter(tvBieren);
		BorderPane.setMargin(tvBieren, new Insets(10));
		TableColumn<String, Bier> column1 = new TableColumn<>("Naam");
		column1.setCellValueFactory(new PropertyValueFactory<>("Naam"));
		TableColumn<LocalDate, Bier> column2 = new TableColumn<>("Brouwdatum");
		column2.setCellValueFactory(new PropertyValueFactory<>("gebrouwenSinds"));
		TableColumn<String, Bier> column3 = new TableColumn<>("Alcohol %");
		column3.setCellValueFactory(new PropertyValueFactory<>("alcoholPercentage"));
		tvBieren.getColumns().addAll(column1, column2, column3);
		HBox hbBottom = new HBox(tfNaam, dpBrouwdatum, tfAlcoholpercentage, btnOpslaan);
		hbBottom.setSpacing(10);
		super.setBottom(hbBottom);
		BorderPane.setMargin(hbBottom, new Insets(10));
	}

	public TableView getTvBieren() {
		return tvBieren;
	}

	public TextField getTfNaam() {
		return tfNaam;
	}

	public TextField getTfAlcoholpercentage() {
		return tfAlcoholpercentage;
	}

	public DatePicker getDpBrouwdatum() {
		return dpBrouwdatum;
	}

	public Button getBtnOpslaan() {
		return btnOpslaan;
	}
}
