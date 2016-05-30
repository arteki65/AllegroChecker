package pl.aptewicz.allegrochecker.gui.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.gui.async.GetSubcategoriesTaskFactory;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiInterface;
import pl.aptewicz.allegrochecker.gui.model.CategoryAndFilterChoiceBoxes;
import pl.aptewicz.allegrochecker.gui.model.CategoryGui;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Arkadiusz Aptewicz on 4/21/16.
 */
// TODO: refactoring!!!
class CategoryChoiceController extends AbstractGuiComponentController {

	private static final String GET_SUBCATEGORIES_THREAD_NAME = "getSubcategoriesThread";

	private ApplicationContext context;

	private AnchorPane profileEditPane;

	private GetSubcategoriesTaskFactory getSubcategoriesTaskFactory = new GetSubcategoriesTaskFactory();

	@Override
	protected AbstractGuiComponentController[] getDependentGuiControllers() {
		return new AbstractGuiComponentController[0];
	}

	@Override
	protected void putGuiDependencies() {
		//no gui dependencies to put
	}

	@Override
	public void setApplicationContext(ApplicationContext context) {
		super.setApplicationContext(context);
		getSubcategoriesTaskFactory.setContext(context);
	}

	@Override
	public void initDependencies() {
		super.initDependencies();
		profileEditPane = guiDependenciesHolder.getDependency(GuiDependencyKind.PROFILE_EDIT_PANE);
		getSubcategoriesTaskFactory.initDependencies();
	}

	@SuppressWarnings("unchecked")
	void handleCategoryChoiceEvent(ActionEvent actionEvent) {
		Task<CategoryAndFilterChoiceBoxes> getSubCategoriesTask = getSubcategoriesTaskFactory.createTask(actionEvent);

		getSubCategoriesTask.setOnSucceeded(event -> {
			CategoryAndFilterChoiceBoxes categoryAndFilterChoiceBoxes = (CategoryAndFilterChoiceBoxes) event.getSource()
					.getValue();

			ChoiceBox<CategoryGui> categoryChoiceBoxFromTask = categoryAndFilterChoiceBoxes.getCategoryChoiceBox();
			ChoiceBox<FilterGuiInterface> filtersChoiceBox = categoryAndFilterChoiceBoxes.getFiltersChoiceBoxes();

			if (categoryChoiceBoxFromTask != null) {
				categoryChoiceBoxFromTask.setOnAction(this::handleCategoryChoiceEvent);

				List<Node> choiceBoxesToRemove = getChoiceBoxesToRemove(categoryChoiceBoxFromTask);

				choiceBoxesToRemove.stream().forEach(profileEditPane.getChildren()::remove);
				profileEditPane.getChildren().add(categoryChoiceBoxFromTask);
			}

			if (filtersChoiceBox != null) {
				filtersChoiceBox.setOnAction(actionEventLambda -> {
					ChoiceBox<FilterGuiInterface> filterSourceChoiceBox = (ChoiceBox<FilterGuiInterface>) actionEventLambda
							.getSource();

					FilterGuiInterface filterGuiInterface = filterSourceChoiceBox.getSelectionModel().getSelectedItem();

					profileEditPane.getChildren().add(filterGuiInterface
							.createFilterGuiNode(filterSourceChoiceBox.getLayoutX(),
									filterSourceChoiceBox.getLayoutY() + 30, filterSourceChoiceBox.getId()));
				});
				profileEditPane.getChildren().add(filtersChoiceBox);
			}
		});

		Thread thread = new Thread(getSubCategoriesTask, GET_SUBCATEGORIES_THREAD_NAME);
		thread.setDaemon(true);
		thread.start();
	}

	private List<Node> getChoiceBoxesToRemove(ChoiceBox<CategoryGui> choiceBoxFromTask) {
		return profileEditPane.getChildren().stream()
				.filter(node -> node.getId() != null && Integer.valueOf(node.getId()) >= Integer
						.valueOf(choiceBoxFromTask.getId())).collect(Collectors.toList());
	}
}
