package pl.aptewicz.allegrochecker.gui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class CategoryGui {
	private final StringProperty name;

	private final IntegerProperty id;

	private final IntegerProperty parentId;

	private final IntegerProperty itemsCount;

	public CategoryGui(StringProperty name, IntegerProperty id,
			IntegerProperty parentId, IntegerProperty itemsCount) {
		super();
		this.name = name;
		this.id = id;
		this.parentId = parentId;
		this.itemsCount = itemsCount;
	}

	public StringProperty getName() {
		return name;
	}

	public IntegerProperty getId() {
		return id;
	}

	public IntegerProperty getParentId() {
		return parentId;
	}

	public IntegerProperty getItemsCount() {
		return itemsCount;
	}

	@Override
	public String toString() {
		return name.get();
	}
}
