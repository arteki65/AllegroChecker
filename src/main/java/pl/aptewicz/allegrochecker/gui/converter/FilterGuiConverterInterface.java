package pl.aptewicz.allegrochecker.gui.converter;

import pl.aptewicz.allegrochecker.model.FilterInterface;

public interface FilterGuiConverterInterface {
	public FilterGuiInterface convert(FilterInterface filterInterface);

	public FilterInterface convert(FilterGuiInterface filterGui);
}
