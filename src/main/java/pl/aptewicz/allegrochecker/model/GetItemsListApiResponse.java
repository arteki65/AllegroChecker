package pl.aptewicz.allegrochecker.model;

import java.util.ArrayList;
import java.util.List;

public class GetItemsListApiResponse {
	private final List<Category> categories;

	private final List<Offer> offers;

	private final List<FilterInterface> filters;

	public GetItemsListApiResponse(List<Category> categories,
			List<Offer> offers, List<FilterInterface> filters) {
		this.categories = categories;
		this.offers = offers;
		this.filters = filters;
	}

	public List<Category> getCategories() {
		return new ArrayList<>(categories);
	}

	public List<Offer> getOffers() {
		return new ArrayList<>(offers);
	}

	public List<FilterInterface> getFilters() {
		return filters;
	}
}
