package pl.aptewicz.allegrochecker.gui.controller;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import pl.aptewicz.allegrochecker.gui.model.ProfileGui;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

/**
 * Created by Arkadiusz Aptewicz on 5/9/16.
 */

class ProfileSelectController extends AbstractGuiComponentController {

	private Label profileDetailsLabel;

	@Override
	protected AbstractGuiComponentController[] getDependentGuiControllers() {
		return new AbstractGuiComponentController[0];
	}

	@Override
	protected void putGuiDependencies() {
		//no gui dependencies to put
	}

	@Override
	protected void initDependencies() {
		super.initDependencies();
		profileDetailsLabel = guiDependenciesHolder.getDependency(GuiDependencyKind.PROFILE_DETAILS_LABEL);
	}

	void handleProfileSelect(ObservableValue<? extends ProfileGui> observable, ProfileGui oldValue,
			ProfileGui newValue) {
		showProfileDetails(newValue);
	}

	private void showProfileDetails(ProfileGui profileGui) {
		profileDetailsLabel.setText(profileGui.getDescription().get());
	}
}
