package pl.aptewicz.allegrochecker.api;

import pl.aptewicz.allegrochecker.gui.model.Language;
import pl.aptewicz.allegrochecker.model.Profile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface RepositoryApiInterface {
	Collection<Profile> getOfferProfiles() throws ClassNotFoundException, IOException;

	Profile getOfferProfileByName(String name);

	void saveOfferProfile(Profile profile) throws IOException;

	Optional<Language> getLanguage();

	void saveLanguage(Language language) throws IOException;
}
