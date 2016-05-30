package pl.aptewicz.allegrochecker.gui.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.api.RepositoryApiInterface;
import pl.aptewicz.allegrochecker.app.Application;
import pl.aptewicz.allegrochecker.gui.alert.ProfileOverviewAlertFactory;
import pl.aptewicz.allegrochecker.gui.converter.ProfileGuiConverter;
import pl.aptewicz.allegrochecker.gui.i18n.I18NHelper;
import pl.aptewicz.allegrochecker.gui.model.Language;
import pl.aptewicz.allegrochecker.gui.model.OfferGui;
import pl.aptewicz.allegrochecker.gui.model.ProfileGui;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProfilesOverviewController extends AbstractGuiComponentController {

	private static final String PROFILE_DETAILS_TITLE_LABEL_I18N_KEY = "profileDetailsTitleLabel";
	private static final String PROFILE_NAME_COLUMN_HEADER_I18N_KEY = "profileNameColumnHeader";
	private static final String PROFILE_DESCRIPTION_COLUMN_HEADER_I18N_KEY = "profileDescriptionColumnHeader";
	private static final String FETCH_OFFERS_BUTTON_I118N_KEY = "fetchOffersButton";
	private static final String SELECT_LANGUAGE_LABEL_I18N_KEY = "selectLanguageLabel";
	@SuppressWarnings("SpellCheckingInspection")
	private static final Language[] AVAILABLE_LANGUAGES = new Language[]{new Language("Polski", "pl"),
			new Language("English", "en")};

	private final Logger logger = Logger.getLogger(getClass().getName());

	@FXML
	private TableView<ProfileGui> profilesTableView;

	@FXML
	private TableColumn<ProfileGui, String> nameColumn;

	@FXML
	private TableColumn<ProfileGui, String> descriptionColumn;

	@FXML
	private Label profileDetailsLabel;

	@FXML
	private Label profileDetailsTitleLabel;

	@FXML
	private ListView<OfferGui> offerGuiListView;

	@FXML
	private Button fetchOffersButton;

	@FXML
	private Label selectLanguageLabel;

	@FXML
	private ChoiceBox<Language> languageChoiceBox;

	private RepositoryApiInterface repositoryApi;
	private ProfileGuiConverter profileGuiConverter;
	private I18NHelper i18NHelper;

	private LanguageChoiceController languageChoiceController = new LanguageChoiceController();
	private ProfileSelectController profileSelectController = new ProfileSelectController();
	private FetchOffersButtonController fetchOffersButtonController = new FetchOffersButtonController();
	private ProfileOverviewAlertFactory profileOverviewAlertFactory = new ProfileOverviewAlertFactory();

	@FXML
	private void initialize() {
		setCellValueFactoriesForColumns();

		profilesTableView.getSelectionModel().selectedItemProperty()
				.addListener(profileSelectController::handleProfileSelect);

		fetchOffersButton.setOnAction(fetchOffersButtonController::handleOnAction);

		initOfferGuiListView();

		initLanguageChoiceBox();
	}

	public void translateGui() {
		profileDetailsTitleLabel.setText(i18NHelper.getMessage(PROFILE_DETAILS_TITLE_LABEL_I18N_KEY));
		nameColumn.setText(i18NHelper.getMessage(PROFILE_NAME_COLUMN_HEADER_I18N_KEY));
		descriptionColumn.setText(i18NHelper.getMessage(PROFILE_DESCRIPTION_COLUMN_HEADER_I18N_KEY));
		fetchOffersButton.setText(i18NHelper.getMessage(FETCH_OFFERS_BUTTON_I118N_KEY));
		selectLanguageLabel.setText(i18NHelper.getMessage(SELECT_LANGUAGE_LABEL_I18N_KEY));
	}

	public void setApplication(Application app) {
		profileOverviewAlertFactory.setApp(app);
	}

	@Override
	protected AbstractGuiComponentController[] getDependentGuiControllers() {
		return new AbstractGuiComponentController[]{fetchOffersButtonController, profileSelectController,
				languageChoiceController, profileOverviewAlertFactory};
	}

	@Override
	protected void putGuiDependencies() {
		guiDependenciesHolder.putDependency(GuiDependencyKind.PROFILE_DETAILS_LABEL, profileDetailsLabel);
		guiDependenciesHolder.putDependency(GuiDependencyKind.OFFER_LIST_VIEW, offerGuiListView);
		guiDependenciesHolder.putDependency(GuiDependencyKind.PROFILES_TABLE_VIEW, profilesTableView);
	}

	@Override
	public void setApplicationContext(ApplicationContext context) {
		super.setApplicationContext(context);
	}

	@Override
	public void initDependencies() {
		super.initDependencies();
		repositoryApi = context.getBean(RepositoryApiInterface.class);
		profileGuiConverter = context.getBean(ProfileGuiConverter.class);
		guiDependenciesHolder = context.getBean(GuiDependenciesHolder.class);
		initI18NHelper();
		fetchOffersButtonController.setProfileOverviewAlertFactory(profileOverviewAlertFactory);
	}

	public void loadProfiles() {
		try {
			profilesTableView.setItems(FXCollections.observableArrayList(getGuiProfilesFromRepository()));
		} catch (FileNotFoundException fileNotFoundException) {
			logger.log(Level.WARNING, ExceptionUtils.getStackTrace(fileNotFoundException));
			profileOverviewAlertFactory.showRepositoryFileNotFound();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	private Collection<? extends ProfileGui> getGuiProfilesFromRepository() throws IOException, ClassNotFoundException {
		return repositoryApi.getOfferProfiles().stream().map(profileGuiConverter::convert).collect(Collectors.toList());
	}

	private void setCellValueFactoriesForColumns() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
	}

	private void initOfferGuiListView() {
		offerGuiListView.setCellFactory(param -> new OfferCellFactory());
		offerGuiListView.getStylesheets().add(getClass().getResource("/OfferGuiListView.css").toExternalForm());
	}

	private void initLanguageChoiceBox() {
		languageChoiceBox.setItems(FXCollections.observableArrayList(AVAILABLE_LANGUAGES));
		languageChoiceBox.setOnAction(languageChoiceController::handleLanguageChoice);
	}

	private void initI18NHelper() {
		i18NHelper = context.getBean(I18NHelper.class);
		i18NHelper.setProfilesOverviewController(this);
		i18NHelper.setGuiMode(true);
	}
}
