package pl.aptewicz.allegrochecker.gui.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.api.GetItemsListApiInterface;
import pl.aptewicz.allegrochecker.gui.converter.FilterGuiConverterInterface;
import pl.aptewicz.allegrochecker.gui.converter.OfferGuiConverterInterface;
import pl.aptewicz.allegrochecker.gui.model.OfferGui;
import pl.aptewicz.allegrochecker.gui.utils.CategoryGuiUtils;
import pl.aptewicz.allegrochecker.gui.utils.FilterGuiUtils;
import pl.aptewicz.allegrochecker.model.CategoryFilter;
import pl.aptewicz.allegrochecker.model.FilterInterface;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ProfileTestButtonController {

	private ApplicationContext context;
	private GetItemsListApiInterface getItemsListApi;
	private AnchorPane profileEditPane;
	private FilterGuiConverterInterface filterGuiConverter;
	private OfferGuiConverterInterface offerGuiConverter;
	private ListView<OfferGui> offersListView;
	private List<FilterInterface> filters = new ArrayList<>();
	private GuiDependenciesHolder guiDependenciesHolder;

	void setApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	void handleTestButtonOnAction(
			@SuppressWarnings("UnusedParameters") ActionEvent actionEvent) {
		findAndAddFilters();
		finAndAddCurrentCategoryToFilters();
		fetchCategoriesAndShow();
	}

	private void findAndAddFilters() {
		profileEditPane.getChildren().stream()
				.filter(FilterGuiUtils::findFilterNodes).forEach(
				filterNode -> FilterGuiUtils
						.addFilters(filterNode, filters, filterGuiConverter));
	}

	private void finAndAddCurrentCategoryToFilters() {
		CategoryGuiUtils.findCurrentCategory(
				profileEditPane.getChildren().stream()
						.filter(CategoryGuiUtils::findCategoryNodes)
						.collect(Collectors.toList())).ifPresent(
				category -> filters
						.add(new CategoryFilter(category.getId().get())));
	}

	private void fetchCategoriesAndShow() {
		offersListView.setItems(FXCollections.observableArrayList(
				getItemsListApi.getItems(filters).getOffers().stream()
						.map(offerGuiConverter::convert)
						.collect(Collectors.toList())));
	}

	void initDependencies() {
		initSpringDependencies();
		initGuiDependencies();
	}

	private void initSpringDependencies() {
		getItemsListApi = context.getBean(GetItemsListApiInterface.class);
		filterGuiConverter = context.getBean(FilterGuiConverterInterface.class);
		offerGuiConverter = context.getBean(OfferGuiConverterInterface.class);
		guiDependenciesHolder = context.getBean(GuiDependenciesHolder.class);
	}

	private void initGuiDependencies() {
		profileEditPane = guiDependenciesHolder
				.getDependency(GuiDependencyKind.PROFILE_EDIT_PANE);
		offersListView = guiDependenciesHolder
				.getDependency(GuiDependencyKind.OFFER_LIST_VIEW);
	}
}
