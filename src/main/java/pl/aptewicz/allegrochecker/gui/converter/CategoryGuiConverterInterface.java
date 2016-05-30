package pl.aptewicz.allegrochecker.gui.converter;

import pl.aptewicz.allegrochecker.gui.model.CategoryGui;
import pl.aptewicz.allegrochecker.model.Category;

public interface CategoryGuiConverterInterface {
	public CategoryGui convert(Category category);
}