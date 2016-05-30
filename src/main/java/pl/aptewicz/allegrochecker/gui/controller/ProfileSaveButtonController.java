package pl.aptewicz.allegrochecker.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiConverterInterface;
import pl.aptewicz.allegrochecker.gui.utils.CategoryGuiUtils;
import pl.aptewicz.allegrochecker.gui.utils.FilterGuiUtils;
import pl.aptewicz.allegrochecker.model.CategoryFilter;
import pl.aptewicz.allegrochecker.model.FilterInterface;
import pl.aptewicz.allegrochecker.model.Profile;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;
import pl.aptewicz.allegrochecker.service.RepositoryServiceInterface;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 11.05.2016 refactoring and extend AbstractController
public class ProfileSaveButtonController {

	private List<FilterInterface> filters = new ArrayList<>();

	private AnchorPane profileEditPane;

	private Stage profileEditStage;

	private FilterGuiConverterInterface filterGuiConverter;

	private ApplicationContext context;

	private RepositoryServiceInterface repository;
	private GuiDependenciesHolder guiDependenciesHolder;

	public void setApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	public void initDependencies() {
		filterGuiConverter = context.getBean(FilterGuiConverterInterface.class);
		repository = context.getBean(RepositoryServiceInterface.class);
		guiDependenciesHolder = context.getBean(GuiDependenciesHolder.class);
		profileEditPane = guiDependenciesHolder.getDependency(GuiDependencyKind.PROFILE_EDIT_PANE);
		profileEditStage = guiDependenciesHolder.getDependency(GuiDependencyKind.PROFILE_EDIT_STAGE);
	}

	void handleSaveButtonOnAction(ActionEvent actionEvent) {
		System.out.println("handle save button on action");

		addFilters();

		addCategoryFilter();

		try {
			SaveConfirmationWindowController saveConfirmationWindowController = showSaveConfirmationWindow();
			if (saveConfirmationWindowController.isSaved()) {
				//TODO: add saving profile description description
				repository.saveOfferProfile(
						new Profile(saveConfirmationWindowController.getProfileName(), null, filters, null));
				showProfileSavedAlert();
				profileEditStage.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showProfileSavedAlert() {
		Alert profileSavedAlert = new Alert(AlertType.INFORMATION);

		profileSavedAlert.initOwner(profileEditPane.getScene().getWindow());
		profileSavedAlert.setTitle("Profile save confirmation");
		profileSavedAlert.setHeaderText("Profile saved succesfully");
		profileSavedAlert.setContentText("Your profile is stored now");

		profileSavedAlert.showAndWait();
	}

	private void addCategoryFilter() {
		CategoryGuiUtils.findCurrentCategory(
				profileEditPane.getChildren().stream().filter(CategoryGuiUtils::findCategoryNodes)
						.collect(Collectors.toList()))
				.ifPresent(category -> filters.add(new CategoryFilter(category.getId().get())));
	}

	private void addFilters() {
		profileEditPane.getChildren().stream().filter(FilterGuiUtils::findFilterNodes)
				.forEach(filterNode -> FilterGuiUtils.addFilters(filterNode, filters, filterGuiConverter));
	}

	private SaveConfirmationWindowController showSaveConfirmationWindow() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/SaveConfirmationWindow.fxml"));

		AnchorPane saveProfileConfirmationWindow = loader.load();

		Stage saveProfileConfirmationStage = new Stage();
		saveProfileConfirmationStage.setTitle("Profile save");
		saveProfileConfirmationStage.initModality(Modality.WINDOW_MODAL);
		saveProfileConfirmationStage.initOwner(profileEditPane.getScene().getWindow());

		Scene scene = new Scene(saveProfileConfirmationWindow);
		saveProfileConfirmationStage.setScene(scene);

		guiDependenciesHolder
				.putDependency(GuiDependencyKind.SAVE_PROFILE_CONFIRMATION_STAGE, saveProfileConfirmationStage);

		SaveConfirmationWindowController controller = loader.getController();
		controller.setApplicationContext(context);
		controller.initDependencies();

		saveProfileConfirmationStage.showAndWait();
		return controller;
	}
}
