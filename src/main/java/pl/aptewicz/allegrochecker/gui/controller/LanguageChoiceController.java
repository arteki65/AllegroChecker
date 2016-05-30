package pl.aptewicz.allegrochecker.gui.controller;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import pl.aptewicz.allegrochecker.api.RepositoryApiInterface;
import pl.aptewicz.allegrochecker.gui.i18n.I18NHelper;
import pl.aptewicz.allegrochecker.gui.model.Language;

import java.io.IOException;

/**
 * Created by Arkadiusz Aptewicz on 5/4/16.
 */

class LanguageChoiceController extends AbstractGuiComponentController {

	private I18NHelper i18NHelper;
	private RepositoryApiInterface repositoryApi;

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
		this.i18NHelper = context.getBean(I18NHelper.class);
		repositoryApi = context.getBean(RepositoryApiInterface.class);
	}

	void handleLanguageChoice(ActionEvent actionEvent) {
		//noinspection unchecked
		Language selectedLanguage = getSelectedLanguage(actionEvent);
		i18NHelper.setLanguage(selectedLanguage);
		try {
			repositoryApi.saveLanguage(selectedLanguage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Language getSelectedLanguage(ActionEvent actionEvent) {
		//noinspection unchecked
		return ((ChoiceBox<Language>) actionEvent.getSource()).getSelectionModel().getSelectedItem();
	}

}
