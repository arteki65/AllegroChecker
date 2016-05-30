package pl.aptewicz.allegrochecker.converter;

import pl.allegro.webapi.ItemsListType;
import pl.aptewicz.allegrochecker.model.Offer;

public interface OfferConverterInterface {
	public Offer convert(ItemsListType item);
}
