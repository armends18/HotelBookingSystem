package controllers;

import Model.Guest;
import View.RegisterPage;
import dao.EmployeeDao;
import Model.Exceptions.WrongPassword;
import Model.Exceptions.WrongUsername;
import Model.Employee;
import View.LoginPage;
import dao.GuestDao;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static com.hotel.Main2.VISUAL_BOUNDS;

public class LoginController {
    private EmployeeDao employeeDAO;
    private GuestDao guestDAO;
    private LoginPage loginPage;
    private RegisterController registerController;


    public LoginPage getLoginPage() {
        return loginPage;
    }

    public LoginController() {
        loginPage = new LoginPage();
        employeeDAO = new EmployeeDao();
        guestDAO=new GuestDao();
        loginPage.getPasswordField().setOnKeyPressed(e->onLoginEnter(e));
        loginPage.getUsernameField().setOnKeyPressed(e->onLoginEnter(e));
        loginPage.getUnmaskedPasswordField().setOnKeyPressed(e->onLoginEnter(e));
        loginPage.getLoginButton().setOnAction(e -> onLoginButton(e));
        loginPage.getRegisterButton().setOnAction(e -> onRegisterButton(e));
    }

    private void onLoginButton(ActionEvent e) {
        login();

    }
    public void onRegisterButton(ActionEvent e) {
        Stage oldStage = (Stage) loginPage.getScene().getWindow();
        oldStage.close();
        Stage primaryStage = new Stage();
        registerController = new RegisterController();
        Scene scene=new Scene(registerController.getRegisterPage());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("3A");
        primaryStage.getIcons().add(new Image("file:src/main/resources/images/appIcon.jpg"));
        primaryStage.show();

    }


    private void onLoginEnter(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    private void login() {
        String username = loginPage.getUsernameField().getText();
        String password = loginPage.getPasswordField().getText();
        Employee emp;
        Guest guest;

        try {
            emp = employeeDAO.authLogin(username, password);
            System.out.println("Login Successful");
            Scene homeScene = new Scene(new HomePageController(emp).getHomePage());
            Stage oldStage = (Stage) loginPage.getScene().getWindow();
            oldStage.close();
            Stage primaryStage = new Stage();
            primaryStage.setScene(homeScene);
            primaryStage.setTitle("3A Hotel");
            primaryStage.setX(VISUAL_BOUNDS.getMinX());
            primaryStage.setY(VISUAL_BOUNDS.getMinY());
            primaryStage.setWidth(VISUAL_BOUNDS.getWidth());
            primaryStage.setHeight(VISUAL_BOUNDS.getHeight());
            primaryStage.getIcons().add(new Image("file:src/main/resources/images/appIcon.jpg"));
            primaryStage.show();

        }
            catch (WrongUsername | WrongPassword e1) {
            try{
                guest=guestDAO.authLogin(username,password);
                System.out.println("Login Successful");
                Scene guestScene = new Scene(new GuestController(guest).getGuestPage());
                Stage oldStage = (Stage) loginPage.getScene().getWindow();
                oldStage.close();
                Stage primaryStage = new Stage();
                primaryStage.setScene(guestScene);
                primaryStage.setTitle("3A Hotel");
                primaryStage.setX(VISUAL_BOUNDS.getMinX());
                primaryStage.setY(VISUAL_BOUNDS.getMinY());
                primaryStage.setWidth(VISUAL_BOUNDS.getWidth());
                primaryStage.setHeight(VISUAL_BOUNDS.getHeight());
                primaryStage.getIcons().add(new Image("file:src/main/resources/images/appIcon.jpg"));
                primaryStage.show();
            } catch (WrongUsername | WrongPassword e) {
                loginPage.getErrorLabel().setVisible(true);
                loginPage.getErrorLabel().setText(e.getMessage());

            }

            }
    }


}