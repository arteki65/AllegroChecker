package pl.aptewicz.allegrochecker.gui.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OfferGui {

	private final DoubleProperty priceProperty;

	private final StringProperty titleProperty;

	private final StringProperty descriptionProperty;

	private final StringProperty cityProperty;

	public OfferGui(double price, String title, String description, String city) {
		this.priceProperty = new SimpleDoubleProperty(price);
		this.titleProperty = new SimpleStringProperty(title);
		this.descriptionProperty = new SimpleStringProperty(description);
		this.cityProperty = new SimpleStringProperty(city);
	}

	public DoubleProperty getPriceProperty() {
		return priceProperty;
	}

	public StringProperty getTitleProperty() {
		return titleProperty;
	}

	public StringProperty getDescriptionProperty() {
		return descriptionProperty;
	}

	public StringProperty getCityProperty() {
		return cityProperty;
	}

	@Override
	public String toString() {
		return "Offer title: " + titleProperty.get() + "\n price: " + priceProperty.get() + "\n description: "
				+ descriptionProperty.get();
	}
}
