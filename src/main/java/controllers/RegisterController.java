package controllers;

import Model.Employee;
import Model.Exceptions.AlreadyExists;
import Model.Exceptions.WrongPassword;
import Model.Exceptions.WrongUsername;
import Model.Guest;
import View.LoginPage;
import View.RegisterPage;
import dao.EmployeeDao;
import dao.GuestDao;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.math.BigInteger;

import static com.hotel.Main2.VISUAL_BOUNDS;

public class RegisterController {
    private EmployeeDao employeeDAO;
    private GuestDao guestDAO;
    private RegisterPage registerPage;
    private LoginController loginController;

    public RegisterPage getRegisterPage() {
        return registerPage;
    }
    public RegisterController() {
        registerPage = new RegisterPage();
        employeeDAO = new EmployeeDao();
        guestDAO=new GuestDao();
        registerPage.getPasswordField().setOnKeyPressed(e->onRegisterEnter(e));
        registerPage.getUsernameField().setOnKeyPressed(e->onRegisterEnter(e));
        registerPage.getUnmaskedPasswordField().setOnKeyPressed(e->onRegisterEnter(e));
        registerPage.getRegisterButton().setOnAction(e ->onRegisterButton(e) );
        registerPage.getGoToLoginButton().setOnAction(e->onLoginButton(e));

    }

    private void onLoginButton(ActionEvent e) {
        Stage oldStage = (Stage) registerPage.getScene().getWindow();
        oldStage.close();
        Stage primaryStage = new Stage();
        loginController = new LoginController();
        Scene scene=new Scene(loginController.getLoginPage());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("3A");
        primaryStage.getIcons().add(new Image("file:src/main/resources/images/appIcon.jpg"));
        primaryStage.show();

    }

    private void onRegisterButton(ActionEvent e) {
        register();

    }


    private void onRegisterEnter(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER) {
            register();
        }
    }

    private void register() {
        String name=registerPage.getNameField().getText();
        String email=registerPage.getEmailField().getText();
        String phone=registerPage.getPhoneField().getText();
        try {
            new BigInteger(phone);
        }
        catch (Exception e){
            registerPage.getErrorLabel().setVisible(true);
            registerPage.getErrorLabel().setText("Phone number should be a number");
        }

        String username = registerPage.getUsernameField().getText();
        String password = registerPage.getPasswordField().getText();
        Guest gue=new Guest(name,phone,email,username,password);
        try {
            boolean ok =guestDAO.guestExists(username);
            System.out.println("Registery Successful");
            Scene scene = new Scene(new NewGuestController(gue).getGuestPage());
            Stage oldStage = (Stage) registerPage.getScene().getWindow();
            oldStage.close();
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("3A Hotel");
            primaryStage.setX(VISUAL_BOUNDS.getMinX());
            primaryStage.setY(VISUAL_BOUNDS.getMinY());
            primaryStage.setWidth(VISUAL_BOUNDS.getWidth());
            primaryStage.setHeight(VISUAL_BOUNDS.getHeight());
            primaryStage.getIcons().add(new Image("file:src/main/resources/images/appIcon.jpg"));
            primaryStage.show();


        }
         catch (AlreadyExists e) {
             registerPage.getErrorLabel().setVisible(true);
             registerPage.getErrorLabel().setText(e.getMessage());
        }
    }
}
