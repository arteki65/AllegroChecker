package pl.aptewicz.allegrochecker.gui.converter;

import javafx.scene.Node;
import pl.aptewicz.allegrochecker.model.FilterInterface;

public interface FilterGuiInterface {
	public Node createFilterGuiNode(double x, double y, String sourceId);

	public String getDescription();

	public FilterInterface getFilterInterface();

	public void updateState();
}
