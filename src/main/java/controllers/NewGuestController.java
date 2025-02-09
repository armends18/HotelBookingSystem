package controllers;

import Model.Booking;
import Model.Guest;
import com.hotel.Main2;
import Model.Employee;
import View.*;
import dao.BookingDao;
import dao.GuestDao;
import dao.RoomsDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NewGuestController {
   
    private NewGuestPage newGuestPage;
    private GuestDao guestDao=new GuestDao();
    private RoomsDao roomsDao=new RoomsDao();


    NewGuestController(Guest guest) {
        
        this.newGuestPage = new NewGuestPage(guest);
        setEditListeners();
        this.newGuestPage.getTableView().setItems(roomsDao.getAll());
        newGuestPage.getLogoutItem().setOnAction(e -> logout());


    }
    private void setEditListeners() {
        this.newGuestPage.getRoomNumberC().setOnEditCommit(e -> {
            roomsDao.getAll().get(e.getTablePosition().getRow()).setRoomNumber(e.getNewValue());
        });
        this.newGuestPage.getRoomType().setOnEditCommit(e -> {
            roomsDao.getAll().get(e.getTablePosition().getRow()).setTypeAsString(e.getNewValue());
        });
        this.newGuestPage.getRoomPrice().setOnEditCommit(e -> {
            roomsDao.getAll().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
        });

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