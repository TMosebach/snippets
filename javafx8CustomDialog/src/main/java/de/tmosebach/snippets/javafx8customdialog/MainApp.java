package de.tmosebach.snippets.javafx8customdialog;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        Button codedDialogbutton = new Button("Open coded dialog...");
        codedDialogbutton.setOnAction((ActionEvent e) -> {
        	openCodedDialog();
        });
        
        Button fxmlDialogbutton = new Button("Open fxml-dialog...");
        fxmlDialogbutton.setOnAction((ActionEvent e) -> {
        	openFxmlDialog();
        });
        
        GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(10, 10, 10, 10));
    	grid.add(codedDialogbutton, 0, 0);
    	grid.add(fxmlDialogbutton, 0, 1);
    	
        Scene scene = new Scene(grid, 200, 100);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    
    private void openFxmlDialog() {
    	FxmlLoginDialog codedDialog = new FxmlLoginDialog();
    	Optional<Login> result = codedDialog.showAndWait();
    	result.ifPresent(login -> System.out.println(login));
	}

	private void openCodedDialog() {
    	
    	CodedLoginDialog codedDialog = new CodedLoginDialog();
    	Optional<Login> result = codedDialog.showAndWait();
    	result.ifPresent(login -> System.out.println(login));
    }
}
