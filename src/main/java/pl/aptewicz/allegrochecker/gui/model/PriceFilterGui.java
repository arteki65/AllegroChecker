package pl.aptewicz.allegrochecker.gui.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiInterface;
import pl.aptewicz.allegrochecker.model.FilterInterface;
import pl.aptewicz.allegrochecker.model.PriceFilter;

public class PriceFilterGui implements FilterGuiInterface {

	private final StringProperty name;

	private final DoubleProperty minPrice;

	private final DoubleProperty maxPrice;

	private Label minPriceLabel;

	private TextField minPriceTextField;

	private TextField maxPriceTextField;

	public PriceFilterGui(StringProperty name, DoubleProperty minPrice,
			DoubleProperty maxPrice) {
		super();
		this.name = name;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public StringProperty getName() {
		return name;
	}

	public DoubleProperty getMinPrice() {
		return minPrice;
	}

	public DoubleProperty getMaxPrice() {
		return maxPrice;
	}

	@Override
	public String toString() {
		return name.get();
	}

	@Override
	public Node createFilterGuiNode(double x, double y, String sourceId) {
		VBox vbox = new VBox();
		vbox.setId(sourceId + "0");

		minPriceLabel = new Label("Min Price: ");
		minPriceLabel.setPadding(new Insets(5));

		Label maxPriceLabel = new Label("Max Price: ");
		maxPriceLabel.setPadding(new Insets(5));

		minPriceTextField = new TextField(String.valueOf(minPrice.get()));
		minPriceTextField.setPrefColumnCount(5);
		minPriceTextField.setOnAction(a -> System.out.println("ACTION"));

		maxPriceTextField = new TextField(String.valueOf(maxPrice.get()));
		maxPriceTextField.setPrefColumnCount(5);

		vbox.setPadding(new Insets(5));

		vbox.setLayoutX(x);
		vbox.setLayoutY(y);

		vbox.getChildren().add(minPriceLabel);
		vbox.getChildren().add(minPriceTextField);
		vbox.getChildren().add(maxPriceLabel);
		vbox.getChildren().add(maxPriceTextField);

		// TODO: add update state method which should be invoked before test request to allegro
		return vbox;
	}

	@Override
	public String getDescription() {
		return "price";
	}

	@Override
	public FilterInterface getFilterInterface() {
		return new PriceFilter(name.get(), minPrice.get(), maxPrice.get());
	}

	@Override
	public void updateState() {
		minPrice.set(Double.valueOf(minPriceTextField.getText()));
		maxPrice.set(Double.valueOf(maxPriceTextField.getText()));
	}

}
