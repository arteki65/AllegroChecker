package pl.aptewicz.allegrochecker.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

public class SaveConfirmationWindowController {

	@FXML private TextField profileNameTextField;
	@FXML private Label insertProfileNameLabel;
	@FXML private Label insertProfileDescriptionLabe;
	@FXML private TextField insertProfileDescritptionTextField;

	private boolean isSaved = false;

	private Stage saveProfileConfirmationStage;
	private ApplicationContext context;

	boolean isSaved() {
		return isSaved;
	}

	@FXML public void handleSaveButton() {
		isSaved = true;
		saveProfileConfirmationStage.close();
	}

	String getProfileName() {
		return profileNameTextField.getText();
	}

	void setApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	void initDependencies() {
		saveProfileConfirmationStage = context
				.getBean(GuiDependenciesHolder.class).getDependency(
						GuiDependencyKind.SAVE_PROFILE_CONFIRMATION_STAGE);
	}

}
