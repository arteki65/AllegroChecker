package pl.aptewicz.allegrochecker.gui.converter;

import pl.aptewicz.allegrochecker.gui.model.ProfileGui;
import pl.aptewicz.allegrochecker.model.Profile;

public interface ProfileGuiConverter {
	ProfileGui convert(Profile profile);

	Profile convert(ProfileGui profileGui);
}
