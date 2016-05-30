package pl.aptewicz.allegrochecker.gui.i18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.aptewicz.allegrochecker.api.RepositoryApiInterface;
import pl.aptewicz.allegrochecker.gui.controller.ProfilesOverviewController;
import pl.aptewicz.allegrochecker.gui.model.Language;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Arkadiusz Aptewicz on 4/23/16.
 */

@Component
public class I18NHelper {

	private static final Language DEFAULT_LANGUAGE = new Language("Polski", "pl");
	private static final String RESOURCE_BUNDLE_BASE_NAME = "/messages_";
	private static final String RESOURCE_BUNDLE_SUFFIX = ".properties";
	private static final String RESOURCE_BUNDLE_CHARSET = "UTF-8";
	private Locale currentLocale;

	private Properties currentResourceBundle;

	private Logger logger = Logger.getLogger(getClass().getName());
	private ProfilesOverviewController profilesOverviewController;
	private boolean guiMode;

	private final RepositoryApiInterface repositoryApi;

	@Autowired
	public I18NHelper(RepositoryApiInterface repositoryApi) {
		this.repositoryApi = repositoryApi;
		determineLanguage();
	}

	public void setLanguage(Language language) {
		currentLocale = new Locale(language.getLanguageKey());
		createCurrentResourceBundle();
		if (guiMode)
			profilesOverviewController.translateGui();
	}

	private void createCurrentResourceBundle() {
		currentResourceBundle = new Properties();
		try {
			currentResourceBundle.load(new InputStreamReader(getClass().getResourceAsStream(
					RESOURCE_BUNDLE_BASE_NAME + currentLocale.getLanguage() + RESOURCE_BUNDLE_SUFFIX),
					Charset.forName(RESOURCE_BUNDLE_CHARSET)));
		} catch (IOException e) {
			logger.log(Level.parse("ERROR"), Arrays.toString(e.getStackTrace()));
		}
	}

	public String getMessage(String messageKey) {
		return currentResourceBundle.getProperty(messageKey);
	}

	public void setProfilesOverviewController(ProfilesOverviewController profilesOverviewController) {
		this.profilesOverviewController = profilesOverviewController;
	}

	public void setGuiMode(boolean guiMode) {
		this.guiMode = guiMode;
	}

	private void determineLanguage() {
		Language currentLanguage = repositoryApi.getLanguage().orElse(DEFAULT_LANGUAGE);
		setLanguage(currentLanguage);
	}
}
