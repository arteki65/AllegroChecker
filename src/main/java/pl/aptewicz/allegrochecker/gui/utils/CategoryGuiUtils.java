package pl.aptewicz.allegrochecker.gui.utils;

import java.util.List;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import pl.aptewicz.allegrochecker.gui.model.CategoryGui;

public class CategoryGuiUtils {

	public static Optional<CategoryGui> findCurrentCategory(List<Node> nodes) {
		CategoryGui currentCategory = null;
		for (Node categoryNode : nodes) {
			@SuppressWarnings("unchecked")
			ChoiceBox<CategoryGui> categoryChoiceBox = (ChoiceBox<CategoryGui>) categoryNode;

			int currentId = -1;
			if (categoryChoiceBox.getSelectionModel().getSelectedItem() != null
					&& Integer.valueOf(categoryChoiceBox.getId()) > currentId) {
				currentId = Integer.valueOf(categoryChoiceBox.getId());
				currentCategory = categoryChoiceBox.getSelectionModel()
						.getSelectedItem();
			}
		}
		return Optional.ofNullable(currentCategory);
	}

	public static boolean findCategoryNodes(Node node) {
		if (node.getId() == null)
			return false;
		return Integer.valueOf(node.getId()) >= 0
				&& Integer.valueOf(node.getId()) <= 100;
	}
}
