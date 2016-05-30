package pl.aptewicz.allegrochecker.converter;

import pl.allegro.webapi.FiltersListType;
import pl.aptewicz.allegrochecker.model.FilterInterface;

@FunctionalInterface
public interface FilterConverterInterface {
	public FilterInterface convert(FiltersListType filter);
}
