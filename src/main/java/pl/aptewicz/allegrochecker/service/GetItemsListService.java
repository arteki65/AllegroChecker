package pl.aptewicz.allegrochecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.allegro.webapi.DoGetItemsListRequest;
import pl.allegro.webapi.DoGetItemsListResponse;
import pl.allegro.webapi.ServicePort;

@Service
public class GetItemsListService implements GetItemsListServiceInterface {

	@Autowired
	private ServicePort allegroWebApiServicePort;

	@Override
	public DoGetItemsListResponse getItemsList(DoGetItemsListRequest request) {
		return allegroWebApiServicePort.doGetItemsList(request);
	}

}
