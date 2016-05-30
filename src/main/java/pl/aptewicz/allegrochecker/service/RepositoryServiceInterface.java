package pl.aptewicz.allegrochecker.service;

import pl.aptewicz.allegrochecker.gui.model.Language;
import pl.aptewicz.allegrochecker.model.Profile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface RepositoryServiceInterface {
	Collection<Profile> getProfiles() throws IOException, ClassNotFoundException;

	Profile getOfferProfileByName(String name);

	void saveOfferProfile(Profile profile) throws IOException;

	Optional<Language> getLanguage() throws IOException, ClassNotFoundException;

	void saveLanguage(Language language) throws IOException;
}
