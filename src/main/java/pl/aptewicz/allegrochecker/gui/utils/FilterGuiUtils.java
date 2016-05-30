package pl.aptewicz.allegrochecker.gui.utils;

import java.util.List;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiConverterInterface;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiInterface;
import pl.aptewicz.allegrochecker.model.FilterInterface;

public class FilterGuiUtils {

	public static boolean findFilterNodes(Node node) {
		if (node.getId() == null)
			return false;
		return Integer.valueOf(node.getId()) > 100
				&& Integer.valueOf(node.getId()) < 1000;
	}

	public static void addFilters(Node node, List<FilterInterface> filters,
			FilterGuiConverterInterface filterGuiConverter) {
		@SuppressWarnings("unchecked")
		ChoiceBox<FilterGuiInterface> filterChoiceBox = (ChoiceBox<FilterGuiInterface>) node;

		Optional<FilterGuiInterface> selectedFilterOptional = Optional
				.ofNullable(
						filterChoiceBox.getSelectionModel().getSelectedItem());

		selectedFilterOptional.ifPresent(selectedFilter -> {
			selectedFilter.updateState();
			filters.add(filterGuiConverter.convert(selectedFilter));
		});
	}

}
