package pl.aptewicz.allegrochecker.converter;

import org.springframework.stereotype.Service;

import pl.allegro.webapi.ItemsListType;
import pl.aptewicz.allegrochecker.model.Offer;

@Service
public class OfferConverter implements OfferConverterInterface {

	@Override
	public Offer convert(ItemsListType item) {
		return new Offer(item.getPriceInfo().getItem().get(0).getPriceValue(),
				item.getItemTitle(), null, null,
				/*
				 * LocalDateTime.of(item.getEndingTime().getYear(),
				 * item.getEndingTime().getMonth(),
				 * item.getEndingTime().getDay(),
				 * item.getEndingTime().getHour(),
				 * item.getEndingTime().getMinute())
				 */null);
	}

}
