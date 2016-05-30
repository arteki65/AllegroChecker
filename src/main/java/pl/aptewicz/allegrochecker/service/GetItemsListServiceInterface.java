package pl.aptewicz.allegrochecker.service;

import pl.allegro.webapi.DoGetItemsListRequest;
import pl.allegro.webapi.DoGetItemsListResponse;

@FunctionalInterface
public interface GetItemsListServiceInterface {

	public DoGetItemsListResponse getItemsList(DoGetItemsListRequest request);
}
