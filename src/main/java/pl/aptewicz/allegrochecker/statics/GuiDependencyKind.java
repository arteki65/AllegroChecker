package pl.aptewicz.allegrochecker.statics;

import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.aptewicz.allegrochecker.gui.controller.ProfilesOverviewController;

public enum GuiDependencyKind {
	GUI_TEST_DEPENDENCY(String.class), PROFILE_EDIT_PANE(AnchorPane.class), PROFILE_EDIT_STAGE(
			Stage.class), PROFILE_EDIT_SCENE(Scene.class), PROFILES_OVERVIEW_CONTROLLER(
			ProfilesOverviewController.class), ROOT_VIEW_STAGE(Stage.class), MAIN_CATEGORIES_CHOICE_BOX(
			ChoiceBox.class), OFFER_LIST_VIEW(ListView.class), SAVE_PROFILE_CONFIRMATION_STAGE(
			Stage.class), PROFILE_DETAILS_LABEL(Label.class), PROFILES_TABLE_VIEW(TableView.class);

	private final Class<?> dependencyClass;

	GuiDependencyKind(Class<?> dependencyClass) {
		this.dependencyClass = dependencyClass;
	}

	public Class<?> getDependencyClass() {
		return dependencyClass;
	}
}
