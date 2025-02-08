package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class LoginPage extends VBox {
    private TextField usernameField = new TextField();
    private TextField unmaskedPasswordField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Button loginButton = new Button("Login");
    private Button registerButton = new Button("Register");
    Label errorLabel = new Label();

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getUnmaskedPasswordField() {
        return unmaskedPasswordField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public Button getRegisterButton() {
        return registerButton;
    }



    public LoginPage() {
        this.setStyle("-fx-background-color: linear-gradient(to bottom, #072c70, #0a4f88); -fx-alignment: center;");
        this.setPrefSize(800, 600);

        // Add the logo
        //Image logo = new Image("file:src/main/resources/images/jupiterLogo.png");
       // ImageView logoView = new ImageView(logo);
        //logoView.setFitWidth(120); // Small size
        //logoView.setPreserveRatio(true);

        // Add welcome text below the logo
        ImageView imageView = new ImageView("file:src/main/resources/images/appIcon.jpg");
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        Label welcomeLabel = new Label("Welcome to 3A Hotel");
        welcomeLabel.setStyle("-fx-text-fill: #e6c232; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px;");

        // Create a VBox for the login box
        VBox registerBox = new VBox(5);
        registerBox.setStyle("-fx-background-color: linear-gradient(to bottom, #58a7df, #92cce5); " +
                "-fx-padding: 30px; -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-border-color: #ffcc71; " +
                "-fx-border-width: 2px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0.5, 0, 0);");

        registerBox.setAlignment(Pos.CENTER);
        registerBox.setMaxWidth(350);


        usernameField.setStyle("-fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #C88B3A; " +
                "-fx-border-width: 1px; -fx-padding: 5px;");
        unmaskedPasswordField.setStyle("-fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #C88B3A; " +
                "-fx-border-width: 1px; -fx-padding: 5px;");
        passwordField.setStyle("-fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #C88B3A; " +
                "-fx-border-width: 1px; -fx-padding: 5px;");

        //add a hint to the txt fields
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        Label loginLabel = new Label("Log-In");
        loginLabel.setStyle("-fx-text-fill: #050507; -fx-font-size: 20px; -fx-font-weight: bold;");

        errorLabel.setFont(new Font("Roboto", 16));
        errorLabel.setStyle("-fx-text-fill: #e30707; -fx-font-weight: bold;");
        errorLabel.setVisible(false);

        //the following (from here to end) modified from an answer in
        //https://stackoverflow.com/questions/17014012/how-to-unmask-a-javafx-passwordfield-or-properly-mask-a-textfield

        // Set initial state
        unmaskedPasswordField.setManaged(false);
        unmaskedPasswordField.setVisible(false);

        CheckBox checkBox = new CheckBox();

        // Bind properties. Toggle textField and passwordField
        // visibility and manageability properties mutually when checkbox's state is changed.
        unmaskedPasswordField.managedProperty().bind(checkBox.selectedProperty());
        unmaskedPasswordField.visibleProperty().bind(checkBox.selectedProperty());

        passwordField.managedProperty().bind(checkBox.selectedProperty().not());
        passwordField.visibleProperty().bind(checkBox.selectedProperty().not());

        // Bind the textField and passwordField text values bidirectionally.
        unmaskedPasswordField.textProperty().bindBidirectional(passwordField.textProperty());
        //end



        HBox passwordBox = new HBox(10, passwordField, unmaskedPasswordField, checkBox);
        passwordBox.setAlignment(Pos.CENTER);

        //make all tf the same size
        VBox loginVBox = new VBox(20, usernameField, passwordBox);
        loginVBox.setAlignment(Pos.CENTER_LEFT);
        loginVBox.setMaxWidth(150);

        loginButton.setStyle("-fx-background-color: #001bde; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 10px 20px;");
        //disable loginButton until user enters text
        loginButton.disableProperty().bind(usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty()));
        registerButton.setStyle("-fx-background-color: #dec000; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 4px 8px;");
        registerBox.getChildren().addAll(errorLabel,imageView, loginLabel, loginVBox, loginButton,registerButton);

        // Add footer text below the login box
        Label footerLabel = new Label("3A Hotel ©");
        footerLabel.setStyle("-fx-text-fill: #916449; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 20px;");

        // Center the login box and footer in the scene
        this.getChildren().addAll( welcomeLabel, registerBox, footerLabel);

    }
}