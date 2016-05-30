package pl.aptewicz.allegrochecker.converter;

import pl.allegro.webapi.CategoryTreeType;
import pl.aptewicz.allegrochecker.model.Category;

@FunctionalInterface
public interface CategoryConverterInterface {
	public Category convert(CategoryTreeType categoryTreeType);
}
