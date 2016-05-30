package pl.aptewicz.allegrochecker.api;

import org.junit.Assert;
import org.junit.Test;
import pl.aptewicz.allegrochecker.gui.model.Language;
import pl.aptewicz.allegrochecker.model.CategoryFilter;
import pl.aptewicz.allegrochecker.model.Offer;
import pl.aptewicz.allegrochecker.model.Profile;
import pl.aptewicz.allegrochecker.service.RepositoryService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class RepositoryApiTest {

	@Test
	public void saveAndGetOffersShouldReturnTheSame()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		// given
		Collection<Profile> profiles = new ArrayList<>();

		Profile profileMoto = new Profile("moto", "moto", Arrays.asList(new CategoryFilter(1)),
				Arrays.asList(new Offer(20.0, "title", "description", "Warszawa", null)));

		Profile profileAgd = new Profile("agd", "agd", Arrays.asList(new CategoryFilter(1)),
				Arrays.asList(new Offer(20.0, "title", "description", "Warszawa", null)));

		profiles.add(profileAgd);
		profiles.add(profileMoto);

		RepositoryService repositoryService = new RepositoryService();
		repositoryService.setFileName("RepositoryTest.ser");
		RepositoryApiInterface repositoryApi = new RepositoryApi(repositoryService);

		// when
		repositoryApi.saveOfferProfile(profileAgd);
		repositoryApi.saveOfferProfile(profileMoto);

		Collection<Profile> profilesFromApi = repositoryApi.getOfferProfiles();

		// then
		Iterator<Profile> iterator = profiles.iterator();
		profilesFromApi.forEach(offerProfile -> Assert.assertEquals(offerProfile.getName(), iterator.next().getName()));
	}

	@Test
	public void saveAndGetLanguageShouldReturnTheSame() throws IOException, ClassNotFoundException {
		// given
		RepositoryService repositoryService = new RepositoryService();
		repositoryService.setLanguageFileName("LanguageTest.ser");
		RepositoryApiInterface repositoryApi = new RepositoryApi(repositoryService);
		Language language = new Language("Polski", "pl");

		// when
		repositoryApi.saveLanguage(language);
		Optional<Language> languageOptional = repositoryApi.getLanguage();

		// then
		@SuppressWarnings("OptionalGetWithoutIsPresent")
		Language languageFromRepository = languageOptional.get();
		Assert.assertEquals(language.getDisplayLanguage(), languageFromRepository.getDisplayLanguage());
		Assert.assertEquals(language.getLanguageKey(), languageFromRepository.getLanguageKey());
	}
}
