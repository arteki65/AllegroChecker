package pl.aptewicz.allegrochecker.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import pl.aptewicz.allegrochecker.AllegroCheckerConfiguration;
import pl.aptewicz.allegrochecker.gui.controller.ProfilesOverviewController;
import pl.aptewicz.allegrochecker.gui.controller.RootViewController;
import pl.aptewicz.allegrochecker.service.GuiDependenciesHolder;
import pl.aptewicz.allegrochecker.statics.GuiDependencyKind;

import java.io.IOException;

@Component
public class Application extends javafx.application.Application {

	private Stage primaryStage;

	private BorderPane rootLayout;

	// TODO: add wait cursor where its required
	// TODO: think about some generic way to handle showing wait cursor

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start()");

		this.primaryStage = primaryStage;
		primaryStage.setTitle("AllegroChecker");

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				AllegroCheckerConfiguration.class);

		applicationContext.getBean(GuiDependenciesHolder.class)
				.putDependency(GuiDependencyKind.ROOT_VIEW_STAGE, primaryStage);

		initRootLayout(applicationContext);

		showMainView(applicationContext);
	}

	private void showMainView(ApplicationContext context) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/ProfilesOverview.fxml"));

		AnchorPane anchorPane = fxmlLoader.load();

		rootLayout.setCenter(anchorPane);

		ProfilesOverviewController controller = fxmlLoader.getController();

		controller.setApplication(this);
		controller.setApplicationContext(context);
		controller.initDependencies();

		context.getBean(GuiDependenciesHolder.class)
				.putDependency(GuiDependencyKind.PROFILES_OVERVIEW_CONTROLLER, controller);
		// TODO: add this hostServices to gui dependency and handle listView onClick with this to open url in browser
		//getHostServices().showDocument("www.google.com");
		controller.loadProfiles();
		controller.translateGui();
	}

	private void initRootLayout(ApplicationContext context) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/RootView.fxml"));

		rootLayout = fxmlLoader.load();

		RootViewController rootViewController = fxmlLoader.getController();
		rootViewController.setApplicationContext(context);
		rootViewController.initDependencies();

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
