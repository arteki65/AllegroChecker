package pl.aptewicz.allegrochecker.gui.controller;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import pl.aptewicz.allegrochecker.gui.model.OfferGui;

/**
 * Created by Arkadiusz Aptewicz on 5/11/16.
 */

public class OfferCellFactory extends ListCell<OfferGui> {

	@Override
	protected void updateItem(OfferGui item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			setGraphic(new Label(item.toString()));
			//setStyle("-fx-background-color: yellow");
			getStyleClass().add("myCellStyleClass");
		}
	}
}
