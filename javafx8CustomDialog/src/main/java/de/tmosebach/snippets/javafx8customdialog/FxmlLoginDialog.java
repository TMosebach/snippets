package de.tmosebach.snippets.javafx8customdialog;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;

public class FxmlLoginDialog extends Dialog<Login>{

	public FxmlLoginDialog() {
		try {
			DialogPane dialogPane =
				(DialogPane)FXMLLoader.load(
					getClass().getResource("/fxml/DialogPane.fxml"));
			dialogPane.getButtonTypes().addAll(
					ButtonType.CANCEL, ButtonType.OK);
			
			final TextField usernameField = 
					(TextField)dialogPane.lookup("#usernameField");
			final TextField passwordField = 
					(TextField)dialogPane.lookup("#passwordField");
			
			// configure a standard button ...
	        Button okButton = (Button)dialogPane.lookupButton(ButtonType.OK);
	        okButton.setDisable(true);
	        okButton.setText("Login");
	        
	        // ... and add some rules
	        usernameField.textProperty().addListener(
	    		(event, oldValue, newValue) 
	    			-> okButton.setDisable(newValue.trim().isEmpty()));
			
			setDialogPane(dialogPane);
			
			// initial cursor
	        Platform.runLater(() -> usernameField.requestFocus());
	        
	        // IMPORTANT : without converter there is no data to return
			setResultConverter((dialogButton) -> {
	            ButtonData data = 
	        		dialogButton == null ? null : dialogButton.getButtonData();
	            return data == ButtonData.OK_DONE 
            		? new Login(usernameField.getText(), passwordField.getText()) 
    				: null;
	        });
		} catch (IOException e) {
			throw new IllegalStateException("Could not initialize FxmlLoginDialog.", e);
		}
	}
}
