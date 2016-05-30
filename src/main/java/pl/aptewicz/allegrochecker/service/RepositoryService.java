package pl.aptewicz.allegrochecker.service;

import org.springframework.stereotype.Service;
import pl.aptewicz.allegrochecker.gui.model.Language;
import pl.aptewicz.allegrochecker.model.Profile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class RepositoryService implements RepositoryServiceInterface {

	private static final String REPOSITORY_FILE_NAME = "Repository.ser";

	private static final String LANGUAGE_FILE_NAME = "Language.ser";

	private Collection<Profile> profiles;

	private Language language;

	private File repositoryFile;
	private File languageFile;

	private boolean isCollectionUpToDate = false;

	private boolean isLanguageUpToDate = false;

	public RepositoryService() {
		profiles = new ArrayList<>();
		repositoryFile = new File(REPOSITORY_FILE_NAME);
		languageFile = new File(LANGUAGE_FILE_NAME);
	}

	@SuppressWarnings("unchecked")
	public Collection<Profile> getProfiles() throws IOException, ClassNotFoundException {
		if (!isCollectionUpToDate) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repositoryFile));
			profiles = (Collection<Profile>) ois.readObject();
			ois.close();
			isCollectionUpToDate = true;
		}
		return profiles;
	}

	@Override
	public Profile getOfferProfileByName(String name) {
		// TODO: add searching profile by name feature
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void saveOfferProfile(Profile profile) throws FileNotFoundException, IOException {
		profiles.add(profile);
		saveRepositoryToFile();
	}
	@Override
	public Optional<Language> getLanguage() throws IOException, ClassNotFoundException {
		if (!isLanguageUpToDate) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(languageFile));
			language = (Language) ois.readObject();
			isLanguageUpToDate = true;
			ois.close();
			return Optional.ofNullable(language);
		}
		return Optional.ofNullable(language);
	}
	@Override
	public void saveLanguage(Language language) throws IOException {
		this.language = language;
		saveLanguageToFile();
	}
	private void saveLanguageToFile() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(languageFile));
		oos.writeObject(language);
		oos.close();
	}

	public void setFileName(String fileName) {
		this.repositoryFile = new File(fileName);
	}

	public void setLanguageFileName(String fileName) {
		this.languageFile = new File(fileName);
	}

	private void saveRepositoryToFile() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repositoryFile));
		oos.writeObject(profiles);
		oos.close();
	}

}
