package pl.aptewicz.allegrochecker.model;

import pl.allegro.webapi.FilterOptionsType;
import pl.allegro.webapi.RangeValueType;

public class PriceFilter implements FilterInterface {

	private static final long serialVersionUID = 1L;

	private static final String PRICE_FILTER_ID = "price";

	private final String name;

	private final double minPrice;

	private final double maxPrice;

	public PriceFilter(String name, double minPrice, double maxPrice) {
		this.name = name;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	@Override
	public FilterOptionsType createWsFilter() {
		FilterOptionsType filterOption = new FilterOptionsType();
		filterOption.setFilterId(PRICE_FILTER_ID);

		RangeValueType rangeValue = new RangeValueType();
		rangeValue.setRangeValueMin(String.valueOf(minPrice));
		rangeValue.setRangeValueMax(String.valueOf(maxPrice));

		filterOption.setFilterValueRange(rangeValue);

		return filterOption;
	}

	@Override
	public String getDescription() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
