package pl.aptewicz.allegrochecker.gui.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.aptewicz.allegrochecker.gui.model.OfferGui;
import pl.aptewicz.allegrochecker.gui.model.ProfileGui;
import pl.aptewicz.allegrochecker.model.Profile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileGuiConverterImpl implements ProfileGuiConverter {

	private final OfferGuiConverterInterface offerGuiConverter;

	@Autowired
	public ProfileGuiConverterImpl(OfferGuiConverterInterface offerGuiConverter) {
		this.offerGuiConverter = offerGuiConverter;
	}

	@Override
	public ProfileGui convert(Profile profile) {
		List<OfferGui> guiOffers;
		if (profile.getOffers() != null)
			guiOffers = profile.getOffers().stream().map(offerGuiConverter::convert).collect(Collectors.toList());
		else
			guiOffers = Collections.emptyList();
		return new ProfileGui(profile.getName(), profile.getDescription(), profile.getFilters(), guiOffers);
	}
	@Override
	public Profile convert(ProfileGui profileGui) {
		throw new UnsupportedOperationException("This type of conversion is non implemented yet");
	}

}
