package pl.aptewicz.allegrochecker.gui.async;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.api.GetItemsListApiInterface;
import pl.aptewicz.allegrochecker.gui.converter.CategoryGuiConverterInterface;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiConverterInterface;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiInterface;
import pl.aptewicz.allegrochecker.gui.model.CategoryAndFilterChoiceBoxes;
import pl.aptewicz.allegrochecker.gui.model.CategoryGui;
import pl.aptewicz.allegrochecker.model.CategoryFilter;
import pl.aptewicz.allegrochecker.model.GetItemsListApiResponse;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Arkadiusz Aptewicz on 4/21/16.
 */
public class GetSubcategoriesTaskFactory {

	private static final int CATEGORY_CHOICE_BOX_BOTTOM_MARGIN = 60;
	private static final int FILTERS_ID_INTERVAL = 101;
	private static final int FILTERS_CHOICE_BOX_X_OFFSET = 350;
	private static final int CATEGORIES_CHOICE_BOX_ID_INTERVAL = 1;

	private ApplicationContext context;
	private Scene profileEditScene;
	private GetItemsListApiInterface getItemsListApi;
	private CategoryGuiConverterInterface categoryGuiConverter;
	private FilterGuiConverterInterface filterGuiConverter;
	private Node mainCategoriesChoiceBox;
	private GuiDependenciesHolder guiDependenciesHolder;
	private ChoiceBox<CategoryGui> categoriesChoiceBox = null;
	private ChoiceBox<FilterGuiInterface> filtersChoiceBox = null;
	private ChoiceBox<CategoryGui> sourceChoiceBox = null;
	private CategoryGui selectedCategory;

	public Task<CategoryAndFilterChoiceBoxes> createTask(
			ActionEvent actionEvent) {
		return new Task<CategoryAndFilterChoiceBoxes>() {

			@Override protected CategoryAndFilterChoiceBoxes call()
					throws Exception {
				profileEditScene.setCursor(Cursor.WAIT);

				//noinspection unchecked
				sourceChoiceBox = (ChoiceBox<CategoryGui>) actionEvent
						.getSource();

				selectedCategory = sourceChoiceBox.getSelectionModel()
						.getSelectedItem();

				GetItemsListApiResponse getSubcategoriesResponse = fetchSubCategories();

				List<CategoryGui> subcategories = findSubcategories(
						getSubcategoriesResponse);

				List<FilterGuiInterface> filters = findFilters(
						getSubcategoriesResponse);

				createSubcategoriesChoiceBox(subcategories);

				createFiltersChoiceBox(filters);

				profileEditScene.setCursor(Cursor.DEFAULT);

				return new CategoryAndFilterChoiceBoxes(categoriesChoiceBox,
						filtersChoiceBox);
			}
		};
	}

	private GetItemsListApiResponse fetchSubCategories() {
		return getItemsListApi.getSubcategories(
				new CategoryFilter(selectedCategory.getId().get()));
	}

	private List<CategoryGui> findSubcategories(
			GetItemsListApiResponse getSubcategoriesResponse) {
		return getSubcategoriesResponse.getCategories().stream()
				.map(categoryGuiConverter::convert)
				.collect(Collectors.toList());
	}

	private List<FilterGuiInterface> findFilters(
			GetItemsListApiResponse getSubcategoriesResponse) {
		return getSubcategoriesResponse.getFilters().stream()
				.map(filterGuiConverter::convert).collect(Collectors.toList());
	}

	private void createFiltersChoiceBox(List<FilterGuiInterface> filters) {
		if (!filters.isEmpty()) {
			ObservableList<FilterGuiInterface> filtersObservableArrayList = FXCollections
					.observableArrayList(filters);

			filtersChoiceBox = new ChoiceBox<>(filtersObservableArrayList);

			filtersChoiceBox.setId(String.valueOf(
					Integer.valueOf(sourceChoiceBox.getId())
							+ FILTERS_ID_INTERVAL));

			filtersChoiceBox.setLayoutX(mainCategoriesChoiceBox.getLayoutX()
					+ FILTERS_CHOICE_BOX_X_OFFSET);
			filtersChoiceBox.setLayoutY(mainCategoriesChoiceBox.getLayoutY());
		}
	}
	private void createSubcategoriesChoiceBox(List<CategoryGui> subcategories) {
		if (!subcategories.isEmpty()) {
			ObservableList<CategoryGui> categoryGuiObservableArrayList = FXCollections
					.observableArrayList(subcategories);

			ChoiceBox<CategoryGui> newChoiceBox = new ChoiceBox<>(
					categoryGuiObservableArrayList);

			newChoiceBox.setId(String.valueOf(
					Integer.valueOf(sourceChoiceBox.getId())
							+ CATEGORIES_CHOICE_BOX_ID_INTERVAL));

			newChoiceBox.setLayoutX(sourceChoiceBox.getLayoutX());
			newChoiceBox.setLayoutY(sourceChoiceBox.getLayoutY()
					+ CATEGORY_CHOICE_BOX_BOTTOM_MARGIN);

			categoriesChoiceBox = newChoiceBox;
		}
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public void initDependencies() {
		initSpringDependencies();
		initGuiDependencies();
	}

	private void initSpringDependencies() {
		guiDependenciesHolder = context.getBean(GuiDependenciesHolder.class);
		filterGuiConverter = context.getBean(FilterGuiConverterInterface.class);
		getItemsListApi = context.getBean(GetItemsListApiInterface.class);
	}

	private void initGuiDependencies() {
		profileEditScene = guiDependenciesHolder
				.getDependency(GuiDependencyKind.PROFILE_EDIT_SCENE);
		categoryGuiConverter = context
				.getBean(CategoryGuiConverterInterface.class);
		mainCategoriesChoiceBox = guiDependenciesHolder
				.getDependency(GuiDependencyKind.MAIN_CATEGORIES_CHOICE_BOX);
	}
}
