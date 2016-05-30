package pl.aptewicz.allegrochecker.api;

import java.util.List;

import pl.aptewicz.allegrochecker.model.CategoryFilter;
import pl.aptewicz.allegrochecker.model.FilterInterface;
import pl.aptewicz.allegrochecker.model.GetItemsListApiResponse;

public interface GetItemsListApiInterface {
	public GetItemsListApiResponse getMainCategories();

	public GetItemsListApiResponse getSubcategories(
			CategoryFilter categoryFilter);

	public GetItemsListApiResponse getItems(List<FilterInterface> filters);
}
