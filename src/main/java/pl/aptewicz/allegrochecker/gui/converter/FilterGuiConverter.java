package pl.aptewicz.allegrochecker.gui.converter;

import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import pl.aptewicz.allegrochecker.gui.model.PriceFilterGui;
import pl.aptewicz.allegrochecker.model.FilterInterface;

@Service
public class FilterGuiConverter implements FilterGuiConverterInterface {

	@Override
	public FilterGuiInterface convert(FilterInterface filterInterface) {
		if ("price".equals(filterInterface.getDescription()))
			return new PriceFilterGui(new SimpleStringProperty("price"),
					new SimpleDoubleProperty(0.0),
					new SimpleDoubleProperty(0.0));
		return null;
	}

	@Override
	public FilterInterface convert(FilterGuiInterface filterGui) {
		return filterGui.getFilterInterface();
	}

}
