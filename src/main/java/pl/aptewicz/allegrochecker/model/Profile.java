package pl.aptewicz.allegrochecker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final String description;

	private final List<FilterInterface> filters;

	private final List<Offer> offers;

	public Profile(String name, String description, List<FilterInterface> filters, List<Offer> offers) {
		super();
		this.name = name;
		this.description = description;
		this.filters = filters;
		this.offers = offers;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<FilterInterface> getFilters() {
		return new ArrayList<>(filters);
	}

	public List<Offer> getOffers() {
		return offers;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Profile:{ \n");
		sb.append("name: " + name + "\n");
		sb.append("description: " + description + "\n");
		sb.append("filters: " + filters + "\n");
		sb.append("offers: " + offers + "}\n");
		return sb.toString();
	}
}
