package pl.aptewicz.allegrochecker.api;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.aptewicz.allegrochecker.gui.model.Language;
import pl.aptewicz.allegrochecker.model.Profile;
import pl.aptewicz.allegrochecker.service.RepositoryServiceInterface;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
class RepositoryApi implements RepositoryApiInterface {

	private final RepositoryServiceInterface repositoryService;
	private final Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	public RepositoryApi(RepositoryServiceInterface repositoryService) {
		super();
		this.repositoryService = repositoryService;
	}

	@Override
	public Collection<Profile> getOfferProfiles() throws ClassNotFoundException, IOException {
		return repositoryService.getProfiles();
	}

	@Override
	public Profile getOfferProfileByName(String name) {
		// TODO: add searching profile by name feature
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void saveOfferProfile(Profile profile) throws IOException {
		repositoryService.saveOfferProfile(profile);
	}
	@Override
	public Optional<Language> getLanguage() {
		try {
			return repositoryService.getLanguage();
		} catch (IOException | ClassNotFoundException e) {
			logger.log(Level.WARNING, ExceptionUtils.getStackTrace(e));
			return Optional.empty();
		}
	}
	@Override
	public void saveLanguage(Language language) throws IOException {
		repositoryService.saveLanguage(language);
	}

}
