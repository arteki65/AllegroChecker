package pl.aptewicz.allegrochecker.model;

public class Category {
	private final String name;

	private final int id;

	private final int parentId;

	private final int itemsCount;

	public Category(String name, int id, int parentId, int itemsCount) {
		this.name = name;
		this.id = id;
		this.parentId = parentId;
		this.itemsCount = itemsCount;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getParentId() {
		return parentId;
	}

	public int getItemsCount() {
		return itemsCount;
	}

	@Override
	public String toString() {
		return "Category: " + name + ", id: " + id + ", parentId: " + parentId
				+ ", itemsCount: " + itemsCount;
	}
}
