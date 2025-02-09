package controllers;

import Model.Booking;
import Model.Guest;
import com.hotel.Main2;
import Model.Employee;
import View.*;
import dao.BookingDao;
import dao.GuestDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NewGuestController {
   
    private NewGuestPage newGuestPage;
    private GuestDao guestDao=new GuestDao();


    NewGuestController(Guest guest) {
        
        this.newGuestPage = new NewGuestPage(guest);
  
        this.newGuestPage.getTableView().setItems(guestDao.getAll());
        newGuestPage.getLogoutItem().setOnAction(e -> logout());


    }

    private void logout() {
        Stage oldStage = (Stage) newGuestPage.getScene().getWindow();
        oldStage.close();
        Main2 main = new Main2();
        Stage stage = new Stage();
        main.start(stage);

    }



    public NewGuestPage getGuestPage() {
        return newGuestPage;
    }
}