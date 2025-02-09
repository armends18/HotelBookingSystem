package controllers;

import Model.Booking;
import com.hotel.Main2;
import Model.Employee;
import View.*;
import dao.BookingDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class HomePageController {
    private Employee employee;
    private HomePage homePage;
    private BookingDao bookingDao=new BookingDao();


    HomePageController(Employee employee) {
        this.employee = employee;
        this.homePage = new HomePage(employee);
        setEditListeners();
        this.homePage.getTableView().setItems(bookingDao.getAll());
        this.homePage.getDeleteBookingBt().setOnAction(e->onBookingDelete(e));
        homePage.getLogoutItem().setOnAction(e -> logout());


    }

    private void logout() {
        Stage oldStage = (Stage) homePage.getScene().getWindow();
        oldStage.close();
        Main2 main = new Main2();
        Stage stage = new Stage();
        main.start(stage);

    }


    private void setEditListeners() {
        this.homePage.getRoomNumber().setOnEditCommit(e -> {
            bookingDao.getAll().get(e.getTablePosition().getRow()).getRoom().setRoomNumber(e.getNewValue());
        });
        this.homePage.getGuestsName().setOnEditCommit(e -> {
            bookingDao.getAll().get(e.getTablePosition().getRow()).getGuest().setName(e.getNewValue());
        });
        this.homePage.getNumberOfNights().setOnEditCommit(e -> {
            bookingDao.getAll().get(e.getTablePosition().getRow()).setNrOfNights(e.getNewValue());
        });
        this.homePage.getStartingDate().setOnEditCommit(e -> {
            bookingDao.getAll().get(e.getTablePosition().getRow()).getGuest().setDate1(e.getNewValue());
        });
        this.homePage.getEndDate().setOnEditCommit(e -> {
            bookingDao.getAll().get(e.getTablePosition().getRow()).getGuest().setDate2(e.getNewValue());
        });
    }

    private void onBookingDelete(ActionEvent event) {
        ObservableList<Booking> selectedBookings = this.homePage.getTableView().getSelectionModel().getSelectedItems();
        Alert alert;
        if(bookingDao.deleteAll(selectedBookings)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deleted successfully");
            System.out.println("Deleted successfully");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Deletion failed");
        }
        alert.setTitle("Delete result");
        alert.show();
    }

    public HomePage getHomePage() {
        return homePage;
    }
}