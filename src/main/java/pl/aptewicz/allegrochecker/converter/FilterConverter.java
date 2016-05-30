package pl.aptewicz.allegrochecker.converter;

import org.springframework.stereotype.Service;

import pl.allegro.webapi.FiltersListType;
import pl.aptewicz.allegrochecker.model.FilterInterface;
import pl.aptewicz.allegrochecker.model.PriceFilter;

@Service
public class FilterConverter implements FilterConverterInterface {

	@Override
	public FilterInterface convert(FiltersListType filter) {
		if ("price".equals(filter.getFilterId()))
			return new PriceFilter("price", 0, 0);

		return null;
	}

}
