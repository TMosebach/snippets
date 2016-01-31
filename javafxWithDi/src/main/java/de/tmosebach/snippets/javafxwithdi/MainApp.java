package de.tmosebach.snippets.javafxwithdi;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    
	private static String[] args;
	private ConfigurableApplicationContext applicationContext;
	
	@Autowired
	private SpringFXMLLoader fxmlLoader;

    public static void main(String[] args) throws Exception {
    	MainApp.args = args;
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
    	applicationContext = SpringApplication.run(getClass(), args);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/hello.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        Parent rootNode = 
    		(Parent)fxmlLoader.load(getClass().getResource(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 400, 200);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() throws Exception {
    	applicationContext.stop();
    }
}
