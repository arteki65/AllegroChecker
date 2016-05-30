package pl.aptewicz.allegrochecker.i18n;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.aptewicz.allegrochecker.AllegroCheckerConfiguration;
import pl.aptewicz.allegrochecker.gui.i18n.I18NHelper;
import pl.aptewicz.allegrochecker.gui.model.Language;
/**
 * Created by Arkadiusz Aptewicz on 4/23/16.
 */
@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration(classes = AllegroCheckerConfiguration.class) public class I18NTest {

	@Autowired
	private I18NHelper i18NHelper;

	@Test public void testI18NHelperReturnsProperMessage() {
		//given
		Language language = new Language("Polski", "pl");

		//when
		i18NHelper.setLanguage(language);
		String message = i18NHelper.getMessage("testMessage");

		//then
		Assert.assertEquals(message, "Polska wiadomość");
	}
}
