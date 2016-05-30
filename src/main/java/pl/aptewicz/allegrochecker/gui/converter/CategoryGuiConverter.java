package pl.aptewicz.allegrochecker.gui.converter;

import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pl.aptewicz.allegrochecker.gui.model.CategoryGui;
import pl.aptewicz.allegrochecker.model.Category;

@Service
public class CategoryGuiConverter implements CategoryGuiConverterInterface {

	@Override
	public CategoryGui convert(Category category) {
		return new CategoryGui(new SimpleStringProperty(category.getName()),
				new SimpleIntegerProperty(category.getId()),
				new SimpleIntegerProperty(category.getParentId()),
				new SimpleIntegerProperty(category.getItemsCount()));
	}

}
