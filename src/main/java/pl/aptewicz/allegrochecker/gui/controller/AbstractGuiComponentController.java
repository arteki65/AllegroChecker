package pl.aptewicz.allegrochecker.gui.controller;
import org.springframework.context.ApplicationContext;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;

import java.util.Arrays;
/**
 * Created by Arkadiusz Aptewicz on 5/9/16.
 */
public abstract class AbstractGuiComponentController {

	protected ApplicationContext context;

	protected GuiDependenciesHolder guiDependenciesHolder;

	protected abstract AbstractGuiComponentController[] getDependentGuiControllers();

	protected abstract void putGuiDependencies();

	protected void setApplicationContext(ApplicationContext context) {
		this.context = context;
		Arrays.asList(getDependentGuiControllers()).forEach(
				abstractGuiComponentController -> abstractGuiComponentController.setApplicationContext(context));
	}

	protected void initDependencies() {
		guiDependenciesHolder = context.getBean(GuiDependenciesHolder.class);
		putGuiDependencies();
		Arrays.asList(getDependentGuiControllers()).forEach(AbstractGuiComponentController::initDependencies);
	}
}
