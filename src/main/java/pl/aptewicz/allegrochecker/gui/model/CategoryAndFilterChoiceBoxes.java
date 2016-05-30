package pl.aptewicz.allegrochecker.gui.model;

import javafx.scene.control.ChoiceBox;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiInterface;

public class CategoryAndFilterChoiceBoxes {

	private final ChoiceBox<CategoryGui> categoryChoiceBox;

	private final ChoiceBox<FilterGuiInterface> filtersChoiceBox;

	public CategoryAndFilterChoiceBoxes(
			ChoiceBox<CategoryGui> categoryChoiceBox,
			ChoiceBox<FilterGuiInterface> filtersChoiceBox) {
		super();
		this.categoryChoiceBox = categoryChoiceBox;
		this.filtersChoiceBox = filtersChoiceBox;
	}

	public ChoiceBox<CategoryGui> getCategoryChoiceBox() {
		return categoryChoiceBox;
	}

	public ChoiceBox<FilterGuiInterface> getFiltersChoiceBoxes() {
		return filtersChoiceBox;
	}

}
