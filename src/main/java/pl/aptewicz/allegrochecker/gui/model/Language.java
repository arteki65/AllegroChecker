package pl.aptewicz.allegrochecker.gui.model;
import java.io.Serializable;
/**
 * Created by Arkadiusz Aptewicz on 5/4/16.
 */
public class Language implements Serializable {

	private final String displayLanguage;

	private final String languageKey;

	public Language(String displayLanguage, String languageKey) {
		this.languageKey = languageKey;
		this.displayLanguage = displayLanguage;
	}

	public String getLanguageKey() {
		return languageKey;
	}

	public String getDisplayLanguage() {
		return displayLanguage;
	}

	public String toString() {
		return displayLanguage;
	}
}
