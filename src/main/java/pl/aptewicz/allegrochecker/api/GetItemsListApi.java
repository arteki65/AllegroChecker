package pl.aptewicz.allegrochecker.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.allegro.webapi.ArrayOfFilteroptionstype;
import pl.allegro.webapi.DoGetItemsListRequest;
import pl.allegro.webapi.DoGetItemsListResponse;
import pl.aptewicz.allegrochecker.converter.CategoryConverterInterface;
import pl.aptewicz.allegrochecker.converter.FilterConverterInterface;
import pl.aptewicz.allegrochecker.converter.OfferConverterInterface;
import pl.aptewicz.allegrochecker.model.Category;
import pl.aptewicz.allegrochecker.model.CategoryFilter;
import pl.aptewicz.allegrochecker.model.FilterInterface;
import pl.aptewicz.allegrochecker.model.GetItemsListApiResponse;
import pl.aptewicz.allegrochecker.service.GetItemsListServiceInterface;
import pl.aptewicz.allegrochecker.statics.StaticValues;

@Component
public class GetItemsListApi implements GetItemsListApiInterface {

	private final GetItemsListServiceInterface getItemsListService;

	private final CategoryConverterInterface categoryConverter;

	private final OfferConverterInterface offerConverter;

	private final FilterConverterInterface filterConverter;

	@Autowired
	public GetItemsListApi(
			GetItemsListServiceInterface getItemsListServiceInterface,
			CategoryConverterInterface categoryConverter,
			OfferConverterInterface itemListTypeConverter,
			FilterConverterInterface filterConverter) {
		this.categoryConverter = categoryConverter;
		this.getItemsListService = getItemsListServiceInterface;
		this.offerConverter = itemListTypeConverter;
		this.filterConverter = filterConverter;
	}

	@Override
	public GetItemsListApiResponse getMainCategories() {
		DoGetItemsListRequest request = new DoGetItemsListRequest();
		request.setCountryId(StaticValues.PL_COUNTRY_INT_VALUE);
		request.setWebapiKey(StaticValues.WEB_API_KEY);

		return executeRequestAndConvertResponse(request);
	}

	private GetItemsListApiResponse executeRequestAndConvertResponse(
			DoGetItemsListRequest request) {
		DoGetItemsListResponse response = getItemsListService
				.getItemsList(request);

		return new GetItemsListApiResponse(
				response.getCategoriesList() != null
						? response.getCategoriesList().getCategoriesTree()
								.getItem().stream()
								.map(categoryConverter::convert)
								.collect(Collectors.toList())
						: null,
				response.getItemsList() != null
						? response.getItemsList().getItem().stream()
								.map(offerConverter::convert)
								.collect(Collectors.toList())
						: null,
				response.getFiltersList() != null
						? response.getFiltersList().getItem().stream()
								.map(filterConverter::convert)
								.filter(filterInterface -> filterInterface != null)
								.collect(Collectors.toList())
						: null);
	}

	@Override
	public GetItemsListApiResponse getItems(List<FilterInterface> filters) {
		DoGetItemsListRequest request = new DoGetItemsListRequest();
		request.setCountryId(StaticValues.PL_COUNTRY_INT_VALUE);
		request.setWebapiKey(StaticValues.WEB_API_KEY);

		ArrayOfFilteroptionstype filterOptions = new ArrayOfFilteroptionstype();
		filterOptions.getItem()
				.addAll(filters.stream()
						.map(filterInterface -> filterInterface
								.createWsFilter())
				.collect(Collectors.toList()));

		request.setFilterOptions(filterOptions);

		return executeRequestAndConvertResponse(request);
	}

	@Override
	public GetItemsListApiResponse getSubcategories(
			CategoryFilter categoryFilter) {
		GetItemsListApiResponse getItemsListApiResponse = getItems(
				Arrays.asList(categoryFilter));

		List<Category> subCategories = getItemsListApiResponse.getCategories()
				.stream().filter(category -> category
						.getParentId() == categoryFilter.getCategoryId())
				.collect(Collectors.toList());

		return new GetItemsListApiResponse(subCategories,
				Collections.emptyList(), getItemsListApiResponse.getFilters());
	}
}
