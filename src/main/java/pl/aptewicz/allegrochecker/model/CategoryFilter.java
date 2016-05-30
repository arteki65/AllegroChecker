package pl.aptewicz.allegrochecker.model;

import pl.allegro.webapi.ArrayOfString;
import pl.allegro.webapi.FilterOptionsType;

public class CategoryFilter implements FilterInterface {

	private static final long serialVersionUID = 1L;

	private static final String CATEGORY_FILTER_ID = "category";

	private final String categoryId;

	private final String name = "Category filter";

	public CategoryFilter(String categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryFilter(int categoryId) {
		this.categoryId = String.valueOf(categoryId);
	}

	@Override
	public FilterOptionsType createWsFilter() {
		ArrayOfString filterValue = new ArrayOfString();
		filterValue.getItem().add(String.valueOf(categoryId));

		FilterOptionsType filterOption = new FilterOptionsType();
		filterOption.setFilterId(CATEGORY_FILTER_ID);
		filterOption.setFilterValueId(filterValue);

		return filterOption;
	}

	public int getCategoryId() {
		return Integer.valueOf(categoryId);
	}

	@Override
	public String getDescription() {
		return name + "with category id = " + categoryId;
	}

}
