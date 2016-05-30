package pl.aptewicz.allegrochecker.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.aptewicz.allegrochecker.AllegroCheckerConfiguration;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AllegroCheckerConfiguration.class)
public class GuiDependenciesHolderTest {

	private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			AllegroCheckerConfiguration.class);

	@Autowired
	private GuiDependenciesHolder guiDependenciesHolder;

	@Test
	public void contextReturnSingletonInstance() {
		// given
		GuiDependenciesHolder guiDependenciesHolderInstanceOne = applicationContext
				.getBean(GuiDependenciesHolder.class);
		GuiDependenciesHolder guiDependenciesHolderInstanceTwo = applicationContext
				.getBean(GuiDependenciesHolder.class);

		//when

		//then
		Assert.assertEquals(guiDependenciesHolderInstanceOne,
				guiDependenciesHolderInstanceTwo);
	}

	@Test
	public void guiDependenciesHolderReturnsProperDependency() {
		//given

		//when
		String testDependencyPut = "test dependency";
		guiDependenciesHolder.putDependency(
				GuiDependencyKind.GUI_TEST_DEPENDENCY, testDependencyPut);
		String testDependencyGet = guiDependenciesHolder
				.getDependency(GuiDependencyKind.GUI_TEST_DEPENDENCY);

		//then
		Assert.assertEquals(testDependencyPut, testDependencyGet);
	}

	@Test(expected = IllegalStateException.class)
	public void noDependencyOfSpecifiedKind() {
		//given
		GuiDependenciesHolder guiDependenciesHolder = new GuiDependenciesHolderImpl();

		//when
		guiDependenciesHolder
				.getDependency(GuiDependencyKind.GUI_TEST_DEPENDENCY);

		//then
	}

	@Test(expected = IllegalStateException.class)
	public void typeMismatchBetweenDependencyKindAndDependency() {
		//given

		//when
		guiDependenciesHolder.putDependency(
				GuiDependencyKind.GUI_TEST_DEPENDENCY, BigDecimal.ONE);

		//then
	}
}
