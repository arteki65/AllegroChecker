package pl.aptewicz.allegrochecker.api;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import pl.allegro.webapi.ArrayOfCategorytreetype;
import pl.allegro.webapi.CategoriesListType;
import pl.allegro.webapi.CategoryTreeType;
import pl.allegro.webapi.DoGetItemsListResponse;
import pl.aptewicz.allegrochecker.converter.CategoryConverter;
import pl.aptewicz.allegrochecker.converter.FilterConverter;
import pl.aptewicz.allegrochecker.converter.OfferConverter;
import pl.aptewicz.allegrochecker.model.Category;
import pl.aptewicz.allegrochecker.model.GetItemsListApiResponse;

public class GetItemsListApiGetMainCategoriesTest {

	@Test
	public void getMainCategoriesTestCase() {
		// given
		Category motoCategory = new Category("Motoryzacja", 1, 2, 25);

		DoGetItemsListResponse response = new DoGetItemsListResponse();
		CategoriesListType categoriesListType = new CategoriesListType();
		ArrayOfCategorytreetype arrayOfCategoryTreeType = new ArrayOfCategorytreetype();

		CategoryTreeType motoCategoryAsCategoryTreeType = new CategoryTreeType();
		motoCategoryAsCategoryTreeType.setCategoryId(motoCategory.getId());
		motoCategoryAsCategoryTreeType.setCategoryName(motoCategory.getName());
		motoCategoryAsCategoryTreeType
				.setCategoryItemsCount(motoCategory.getItemsCount());
		motoCategoryAsCategoryTreeType
				.setCategoryParentId(motoCategory.getParentId());

		arrayOfCategoryTreeType.getItem().add(motoCategoryAsCategoryTreeType);
		categoriesListType.setCategoriesTree(arrayOfCategoryTreeType);
		response.setCategoriesList(categoriesListType);

		GetItemsListApiResponse expectedGetItemsListApiResponse = new GetItemsListApiResponse(
				Arrays.asList(motoCategory), Collections.emptyList(),
				Collections.emptyList());

		GetItemsListApiInterface getItemsListApi = new GetItemsListApi(
				request -> response, new CategoryConverter(),
				new OfferConverter(), new FilterConverter());

		// when
		GetItemsListApiResponse getItemsListApiResponse = getItemsListApi
				.getMainCategories();

		// then
		Assert.assertEquals(
				expectedGetItemsListApiResponse.getCategories().get(0)
						.getName(),
				getItemsListApiResponse.getCategories().get(0).getName());
		Assert.assertEquals(
				expectedGetItemsListApiResponse.getCategories().get(0).getId(),
				getItemsListApiResponse.getCategories().get(0).getId());
		Assert.assertEquals(
				expectedGetItemsListApiResponse.getCategories().get(0)
						.getParentId(),
				getItemsListApiResponse.getCategories().get(0).getParentId());
		Assert.assertEquals(
				expectedGetItemsListApiResponse.getCategories().get(0)
						.getItemsCount(),
				getItemsListApiResponse.getCategories().get(0).getItemsCount());
	}
}
