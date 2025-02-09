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

import java.awt.print.Book;

public class GuestController {

    private GuestPage guestPage;
    private GuestDao guestDao=new GuestDao();
    private RoomsDao roomsDao=new RoomsDao();
    private BookingDao bookingDao=new BookingDao();
    private Guest guest;


    GuestController(Guest guest) {

        this.guestPage = new GuestPage(guest);
        this.guest=guest;
        guestPage.getLogoutItem().setOnAction(e->logout());
        guestPage.getCancelBookingBtn().setOnAction(e->cancelBooking());
        guestPage.getEndBookingBtn().setOnAction(e->endBooking());

    }
    public void cancelBooking(){
       int i=guestPage.getIndex();

       Booking booking=bookingDao.getAll().get(i);
       if(guestDao.deleteGuest(bookingDao.getAll().get(i).getGuest()))
            System.out.println("complete");
       if(bookingDao.deleteBooking(booking))
           System.out.println("done"+booking.getInvoicePrice());

        booking.cancelBooking();
        guestPage.getEndBookingBtn().setDisable(true);
        guestPage.getInvoiceArea().setText(booking.generateCancellationInvoice());
    }
    public void endBooking(){
        int i=guestPage.getIndex();
        Booking booking=bookingDao.getAll().get(i);
        if(guestDao.deleteGuest(bookingDao.getAll().get(i).getGuest())) {
            System.out.println("complete");
        }
        if(bookingDao.deleteBooking(booking))
            System.out.println("done");

        guestPage.getCancelBookingBtn().setDisable(true);
        booking.endBooking();
        guestPage.getInvoiceArea().setText(booking.generateFinalInvoice());
    }
    private void logout() {
        Stage oldStage = (Stage) guestPage.getScene().getWindow();
        oldStage.close();
        Main2 main = new Main2();
        Stage stage = new Stage();
        main.start(stage);

    }




    public GuestPage getGuestPage() {
        return guestPage;
    }
}