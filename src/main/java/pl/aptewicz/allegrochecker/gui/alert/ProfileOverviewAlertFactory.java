package pl.aptewicz.allegrochecker.gui.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import pl.aptewicz.allegrochecker.app.Application;
import pl.aptewicz.allegrochecker.gui.controller.AbstractGuiComponentController;
import pl.aptewicz.allegrochecker.gui.i18n.I18NHelper;

/**
 * Created by Arkadiusz Aptewicz on 5/10/16.
 */

public class ProfileOverviewAlertFactory extends AbstractGuiComponentController {

	private static final String REPOSITORY_FILE_NOT_FOUND_ALERT_TITLE_KEY = "repositoryFileNotFoundAlertTitle";
	private static final String REPOSITORY_FILE_NOT_FOUND_ALERT_HEADER_KEY = "repositoryFileNotFoundAlertHeader";
	private static final String REPOSITORY_FILE_NOT_FOUND_ALERT_CONTENT_KEY = "repositoryFileNotFoundAlertContent";

	private Application app;
	private I18NHelper i18NHelper;

	@Override
	protected AbstractGuiComponentController[] getDependentGuiControllers() {
		return new AbstractGuiComponentController[0];
	}

	@Override
	protected void putGuiDependencies() {
		//no gui dependencies to put
	}

	@Override
	public void initDependencies() {
		super.initDependencies();
		i18NHelper = context.getBean(I18NHelper.class);
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public void showNoProfileSelectedAlert() {
		Alert noProfileSelectedAlert = new Alert(Alert.AlertType.WARNING);

		noProfileSelectedAlert.initOwner(app.getPrimaryStage());
		noProfileSelectedAlert.setTitle("No selection");
		noProfileSelectedAlert.setHeaderText("No profile selected");
		noProfileSelectedAlert.setContentText("Please select a profile from table");

		noProfileSelectedAlert.showAndWait();
	}

	public void showRepositoryFileNotFound() {
		Alert repositoryFileNotFoundAlert = new Alert(Alert.AlertType.WARNING);
		repositoryFileNotFoundAlert.initOwner(app.getPrimaryStage());
		repositoryFileNotFoundAlert.setTitle(i18NHelper.getMessage(REPOSITORY_FILE_NOT_FOUND_ALERT_TITLE_KEY));
		repositoryFileNotFoundAlert.setHeaderText(i18NHelper.getMessage(REPOSITORY_FILE_NOT_FOUND_ALERT_HEADER_KEY));
		repositoryFileNotFoundAlert.setContentText(i18NHelper.getMessage(REPOSITORY_FILE_NOT_FOUND_ALERT_CONTENT_KEY));
		setSizeToWrapContent(repositoryFileNotFoundAlert);
		repositoryFileNotFoundAlert.showAndWait();
	}

	private void setSizeToWrapContent(Alert alert) {
		alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
				.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
	}
}
