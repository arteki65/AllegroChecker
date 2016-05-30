package pl.aptewicz.allegrochecker.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.io.IOException;

public class RootViewController {

	private static final String PROFILE_EDIT_DIALOG_FXML_RESOURCE_PATH = "/ProfileEditDialog.fxml";

	private Stage rootViewStage;
	private ApplicationContext context;
	private GuiDependenciesHolder guiDependenciesHolder;
	private Scene profileEditScene;
	private Stage profileEditStage;

	@FXML public void handleAddNewProfile() throws IOException {
		FXMLLoader loader = new FXMLLoader();

		initializeProfileEditGui(loader);

		putGuiDependenciesToHolder();

		initializeProfileEditController(loader);

		profileEditStage.showAndWait();

		refreshProfiles();
	}

	private void initializeProfileEditGui(FXMLLoader loader)
			throws IOException {
		initializeProfileEditScene(loader);
		initializeProfileEditStage();
	}

	private void initializeProfileEditScene(FXMLLoader loader)
			throws IOException {
		AnchorPane profileEditPane = createProfileEditPane(loader);
		profileEditScene = new Scene(profileEditPane);
	}

	private AnchorPane createProfileEditPane(FXMLLoader loader)
			throws IOException {
		loader.setLocation(
				getClass().getResource(PROFILE_EDIT_DIALOG_FXML_RESOURCE_PATH));
		return loader.load();
	}

	private void initializeProfileEditStage() {
		profileEditStage = createProfileEditStage(profileEditScene);
	}

	private Stage createProfileEditStage(Scene profileEditScene) {
		Stage profileEditStage = new Stage();
		profileEditStage.setTitle("Edit profile");
		profileEditStage.initModality(Modality.WINDOW_MODAL);
		profileEditStage.initOwner(rootViewStage);
		profileEditStage.setScene(profileEditScene);
		return profileEditStage;
	}

	private void putGuiDependenciesToHolder() {
		guiDependenciesHolder
				.putDependency(GuiDependencyKind.PROFILE_EDIT_SCENE,
						profileEditScene);

		guiDependenciesHolder
				.putDependency(GuiDependencyKind.PROFILE_EDIT_STAGE,
						profileEditStage);
	}

	private void initializeProfileEditController(FXMLLoader loader) {
		ProfileEditController controller = loader.getController();
		controller.setApplicationContext(context);
		controller.initDependencies();
		controller.loadMainCategories();
	}

	private void refreshProfiles() {
		ProfilesOverviewController profilesOverviewController = getProfilesOverviewController();
		profilesOverviewController.loadProfiles();
	}
	private ProfilesOverviewController getProfilesOverviewController() {
		return guiDependenciesHolder
				.getDependency(GuiDependencyKind.PROFILES_OVERVIEW_CONTROLLER);
	}

	public void setApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	public void initDependencies() {
		guiDependenciesHolder = context.getBean(GuiDependenciesHolder.class);
		rootViewStage = guiDependenciesHolder
				.getDependency(GuiDependencyKind.ROOT_VIEW_STAGE);
	}
}
