package pl.aptewicz.allegrochecker.gui.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.api.GetItemsListApiInterface;
import pl.aptewicz.allegrochecker.gui.converter.CategoryGuiConverterInterface;
import pl.aptewicz.allegrochecker.gui.model.CategoryGui;
import pl.aptewicz.allegrochecker.gui.model.OfferGui;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.util.stream.Collectors;

public class ProfileEditController {

	private static final String MAIN_CATEGORIES_CHOICE_BOX_ID = "0";
	private static final String TEST_BUTTON_ID = "-1";
	private static final String SAVE_BUTTON_ID = "-2";

	@FXML
	private ChoiceBox<CategoryGui> mainCategoriesChoiceBox;
	@FXML
	private AnchorPane profileEditPane;
	@FXML
	private Button testButton;
	@FXML
	private Button saveButton;
	@FXML
	private ListView<OfferGui> offersListView;

	private ApplicationContext context;
	private CategoryGuiConverterInterface categoryGuiConverter;
	private GetItemsListApiInterface getItemsListApi;

	private ProfileTestButtonController profileTestButtonController = new ProfileTestButtonController();
	private ProfileSaveButtonController profileSaveButtonController = new ProfileSaveButtonController();
	private CategoryChoiceController categoryChoiceController = new CategoryChoiceController();
	private GuiDependenciesHolder guiDependenciesHolder;

	void setApplicationContext(ApplicationContext context) {
		this.context = context;
		profileTestButtonController.setApplicationContext(context);
		profileSaveButtonController.setApplicationContext(context);
		categoryChoiceController.setApplicationContext(context);
	}

	void initDependencies() {
		initSpringDependencies();
		putGuiDependencies();
		initControllersDependencies();
	}

	private void initSpringDependencies() {
		categoryGuiConverter = context.getBean(CategoryGuiConverterInterface.class);
		getItemsListApi = context.getBean(GetItemsListApiInterface.class);
		guiDependenciesHolder = context.getBean(GuiDependenciesHolder.class);
	}

	private void putGuiDependencies() {
		guiDependenciesHolder.putDependency(GuiDependencyKind.PROFILE_EDIT_PANE, profileEditPane);
		guiDependenciesHolder.putDependency(GuiDependencyKind.MAIN_CATEGORIES_CHOICE_BOX, mainCategoriesChoiceBox);
		guiDependenciesHolder.putDependency(GuiDependencyKind.OFFER_LIST_VIEW, offersListView);
	}

	private void initControllersDependencies() {
		profileTestButtonController.initDependencies();
		profileSaveButtonController.initDependencies();
		categoryChoiceController.initDependencies();
	}

	@FXML
	private void initialize() {
		initializeMainCategoriesChoiceBox();
		initializeTestButton();
		initializeSaveButton();
	}

	private void initializeMainCategoriesChoiceBox() {
		mainCategoriesChoiceBox.setOnAction(categoryChoiceController::handleCategoryChoiceEvent);
		mainCategoriesChoiceBox.setId(MAIN_CATEGORIES_CHOICE_BOX_ID);

		profileEditPane.getChildren().add(mainCategoriesChoiceBox);
	}

	private void initializeTestButton() {
		testButton.setId(TEST_BUTTON_ID);
		testButton.setOnAction(profileTestButtonController::handleTestButtonOnAction);
	}

	private void initializeSaveButton() {
		saveButton.setId(SAVE_BUTTON_ID);
		saveButton.setOnAction(profileSaveButtonController::handleSaveButtonOnAction);
	}

	void loadMainCategories() {
		mainCategoriesChoiceBox.setItems(FXCollections.observableArrayList(
				getItemsListApi.getMainCategories().getCategories().stream().map(categoryGuiConverter::convert)
						.collect(Collectors.toList())));
	}
}
