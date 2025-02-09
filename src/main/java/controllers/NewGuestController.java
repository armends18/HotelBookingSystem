package controllers;

import Model.*;
import com.hotel.Main2;
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
    private BookingDao bookingDao=new BookingDao();
    private Guest guest;


    NewGuestController(Guest guest) {
        
        this.newGuestPage = new NewGuestPage(guest);
        setEditListeners();
        this.newGuestPage.getTableView().setItems(roomsDao.getAll());
        newGuestPage.getLogoutItem().setOnAction(e -> logout());
        newGuestPage.getShowRoomsBt().setOnAction(e-> showRoomsAfterFilter());
        newGuestPage.getBookingBt().setOnAction(e->declareBooking());
        this.guest=guest;
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
    private void loadSingleRooms() {
        RoomsDao roomsDao = new RoomsDao(); // Assuming this class fetches all rooms
        newGuestPage.getTableView().getItems().clear(); // Clear previous data

        for (Room room : roomsDao.getAll()) {
            if ((room.getType() == RoomType.SINGLE) && (room.isAvailable(newGuestPage.getDateFrom().getValue(),newGuestPage.getDateTo().getValue()))) { // Filter only SINGLE rooms
               newGuestPage.getTableView().getItems().add(room);
            }
        }
    }
    private void loadDoubleRooms() {
        RoomsDao roomsDao = new RoomsDao(); // Assuming this class fetches all rooms
        newGuestPage.getTableView().getItems().clear(); // Clear previous data

        for (Room room : roomsDao.getAll()) {
            if ((room.getType() == RoomType.DOUBLE) && (room.isAvailable(newGuestPage.getDateFrom().getValue(),newGuestPage.getDateTo().getValue()))) { // Filter only SINGLE rooms
                newGuestPage.getTableView().getItems().add(room);
            }
        }
    }
    private void loadTwinRooms(){
        RoomsDao roomsDao = new RoomsDao(); // Assuming this class fetches all rooms
        newGuestPage.getTableView().getItems().clear(); // Clear previous data

        for (Room room : roomsDao.getAll()) {
            if ((room.getType() == RoomType.TWIN) && (room.isAvailable(newGuestPage.getDateFrom().getValue(),newGuestPage.getDateTo().getValue()))) { // Filter only SINGLE rooms
                newGuestPage.getTableView().getItems().add(room);
            }
        }
    }
    private void showRoomsAfterFilter(){
        if(newGuestPage.getDateTo()!=null && newGuestPage.getDateFrom()!=null){
        if(newGuestPage.getSingleBt().isSelected()){
            loadSingleRooms();

        }
        else if(newGuestPage.getDoubleBt().isSelected()){
            loadDoubleRooms();
        }
        else if(newGuestPage.getTwinBt().isSelected()){
            loadTwinRooms();
        }
        else{
            newGuestPage.getErrorLabel().setText("Enter what Type of room you want");
        }
        }
        else newGuestPage.getErrorLabel().setText("Enter the dates of the rooms you want");
    }
    public void declareBooking(){


        Room selectedRoom = (Room) newGuestPage.getTableView().getSelectionModel().getSelectedItem();
        if(selectedRoom==null){
            newGuestPage.getErrorLabel().setText("Please select a room first and enter the details");
        }
        else{
            guest.setDate1(newGuestPage.getDateFrom().getValue());
            guest.setDate2(newGuestPage.getDateTo().getValue());
            if(guestDao.createGuest(guest)){
                System.out.println("yes");
            }
        bookingDao.createBooking(new Booking(selectedRoom,guest));
        logout();}
    }


    public NewGuestPage getGuestPage() {
        return newGuestPage;
    }
}