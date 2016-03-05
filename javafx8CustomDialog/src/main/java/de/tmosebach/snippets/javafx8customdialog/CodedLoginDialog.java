package de.tmosebach.snippets.javafx8customdialog;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class CodedLoginDialog extends Dialog<Login> {

	private GridPane grid;
    private Label usernameLabel;
    private TextField usernameField;
    private Label passwordLabel;
    private PasswordField passwordField;
    
    public CodedLoginDialog() {
        final DialogPane dialogPane = getDialogPane();
        
        setTitle("Login dialog (programmatic)");

        // Setting the headertext, moves graphic to the right of the text
        // @see javafx.scene.control.Dialog
//        dialogPane.setHeaderText("a coded login dialog");
        
        // you may add an icon. The icons position depends on header 
        // respectively headertext
        dialogPane.setGraphic(createImageView());
        
        // TODO add style
        dialogPane.setId("login-dialog");
        
        // the main pane
        dialogPane.setContent(createContentGrid());
        
        // configure buttons: you may cancel or finish the dialog
        dialogPane.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        
        // configure a standard button ...
        Button okButton = (Button)dialogPane.lookupButton(ButtonType.OK);
        okButton.setDisable(true);
        okButton.setText("Login");
        
        // ... and add some rules
        usernameField.textProperty().addListener(
    		(event, oldValue, newValue) 
    			-> okButton.setDisable(newValue.trim().isEmpty()));
        
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
    }
    
    private Node createContentGrid() {
        this.usernameField = new TextField();
        this.usernameField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(usernameField, Priority.ALWAYS);
        GridPane.setFillWidth(usernameField, true);
        
        usernameLabel = new Label("Username"); 
        usernameLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);

        this.passwordField = new PasswordField();
        this.passwordField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(passwordField, Priority.ALWAYS);
        GridPane.setFillWidth(passwordField, true);
        
        passwordLabel = new Label("Password"); 
        passwordLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
  
        this.grid = new GridPane();
        this.grid.setHgap(10);
        this.grid.setPrefWidth(300.0);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER_LEFT);
        
        grid.getChildren().clear();
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        
		return grid;
	}

	private Node createImageView() {
		return new ImageView(new Image(
				getClass().getResourceAsStream("/images/login.png")));
	}
}
