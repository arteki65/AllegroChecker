package pl.aptewicz.allegrochecker.gui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.aptewicz.allegrochecker.model.FilterInterface;

import java.util.ArrayList;
import java.util.List;

// TODO: add method to adding offers which are not in guiOffers list
public class ProfileGui {

	private final StringProperty name;

	private final StringProperty description;

	private final List<FilterInterface> filters;

	private final List<OfferGui> guiOffers;

	public ProfileGui(String name, String description, List<FilterInterface> filters, List<OfferGui> guiOffers) {
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		this.filters = filters;
		this.guiOffers = guiOffers;
	}

	public StringProperty getName() {
		return name;
	}

	public StringProperty getDescription() {
		return description;
	}

	public List<FilterInterface> getFilters() {
		return new ArrayList<>(filters);
	}

	public List<OfferGui> getGuiOffers() {
		return new ArrayList<>(guiOffers);
	}
}
