package pl.aptewicz.allegrochecker.gui.converter;

import org.springframework.stereotype.Service;

import pl.aptewicz.allegrochecker.gui.model.OfferGui;
import pl.aptewicz.allegrochecker.model.Offer;

@Service
public class OfferGuiConverter implements OfferGuiConverterInterface {

	@Override
	public OfferGui convert(Offer offer) {
		return new OfferGui(offer.getPrice(), offer.getTitle(),
				offer.getDescription(), offer.getCity());
	}

}
