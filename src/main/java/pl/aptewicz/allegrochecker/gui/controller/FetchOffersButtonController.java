package pl.aptewicz.allegrochecker.gui.controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import pl.aptewicz.allegrochecker.api.GetItemsListApiInterface;
import pl.aptewicz.allegrochecker.gui.alert.ProfileOverviewAlertFactory;
import pl.aptewicz.allegrochecker.gui.converter.OfferGuiConverterInterface;
import pl.aptewicz.allegrochecker.gui.model.OfferGui;
import pl.aptewicz.allegrochecker.gui.model.ProfileGui;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.util.stream.Collectors;

/**
 * Created by Arkadiusz Aptewicz on 5/10/16.
 */
class FetchOffersButtonController extends AbstractGuiComponentController {

	private TableView<ProfileGui> profilesTableView;
	private ListView<OfferGui> offersGuiListView;

	private GetItemsListApiInterface getItemsListApi;
	private OfferGuiConverterInterface offerGuiConverter;

	private ProfileOverviewAlertFactory profileOverviewAlertFactory;

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
		profilesTableView = guiDependenciesHolder.getDependency(GuiDependencyKind.PROFILES_TABLE_VIEW);
		offersGuiListView = guiDependenciesHolder.getDependency(GuiDependencyKind.OFFER_LIST_VIEW);
		getItemsListApi = context.getBean(GetItemsListApiInterface.class);
		offerGuiConverter = context.getBean(OfferGuiConverterInterface.class);
	}

	// TODO: add saving fetched offers in profile
	void handleOnAction(ActionEvent actionEvent) {
		int profilesTableSelectedIndex = profilesTableView.getSelectionModel().getSelectedIndex();
		if (profilesTableSelectedIndex >= 0) {
			offersGuiListView.setItems(FXCollections.observableArrayList(
					getItemsListApi.getItems(profilesTableView.getItems().get(profilesTableSelectedIndex).getFilters())
							.getOffers().stream().map(offerGuiConverter::convert).collect(Collectors.toList())));
		} else {
			profileOverviewAlertFactory.showNoProfileSelectedAlert();
		}
	}

	void setProfileOverviewAlertFactory(ProfileOverviewAlertFactory profileOverviewAlertFactory) {
		this.profileOverviewAlertFactory = profileOverviewAlertFactory;
	}
}
