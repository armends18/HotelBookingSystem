package Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = -7417220444497167797L;
    private Room room;
    private Guest guest;
    private double invoicePrice;
    private String invoiceId;
    private double refundPrice;
    private int numberOfNights=0;


    public Booking(Room room, Guest guest) {
        this.room = room;
        this.guest = guest;
        this.room.setBookedDates(guest.getDate1(),guest.getDate2());
        this.invoiceId=LocalDate.now().toString();
        this.numberOfNights= (int) ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2());

    }

    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public double getInvoicePrice() {
        return invoicePrice;
    }
    public void setInvoicePrice(double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public String generateFirstInvoice(){
        invoicePrice = room.getPrice()* ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2())*1.2;
        return ("Invoice_"+invoiceId+":")
        +("\nYou have booked Room "+room.getRoomNumber()+" of type: "+room.getType() +" for "+ (ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2()))+" night/s.")
        +("\nThe Dates of your stay are: "+guest.getDate1()+" and "+guest.getDate2())
        +("\nYou have to check out at: "+guest.getDate2()+" at 11.00 am")
        +("\nInvoice Price: "+invoicePrice)
        +("\nRefund Policy:\nFull refund: Cancel at least 3 days before booking date!\n30% refund: at least 1 day before the booking\n");

    }
    public String generateFinalInvoice(){
        invoicePrice = room.getPrice()* ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2())*1.2;
        return ("Invoice_"+invoiceId+":")
        +("\nYou have stayed in Room "+room.getRoomNumber()+" for "+ (ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2()))+" night/s.")
        +("\nThe Dates of your stay were: "+guest.getDate1()+" and "+guest.getDate2())
        +("\nInvoice Price: "+invoicePrice);


    }
    public String  generateCancellationInvoice(){
        return("Invoice_"+invoiceId+":")
        +("\nYou have cancelled your booking of room "+room.getRoomNumber()+" of type: "+room.getType() +" for "+ ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2())+" nights.")
        +("\nThe Dates of your stay were: "+guest.getDate1()+" up to "+guest.getDate2())
        +("\nInvoice Price: "+invoicePrice)
        +("\nRefund Amount: "+refundPrice);

    }

    public void cancelBooking(){
        LocalDate today = LocalDate.now();

        if(guest.getDate1().isAfter(today)){
            if(ChronoUnit.DAYS.between(today,guest.getDate1())>=3){

                this.refundPrice= this.invoicePrice;
                this.invoicePrice=0;

                room.removeBookedDates(guest.getDate1(),guest.getDate2());

            } else if (ChronoUnit.DAYS.between(today,guest.getDate1())>=1) {
                this.refundPrice= this.invoicePrice*0.3;
                this.invoicePrice=this.invoicePrice-this.refundPrice;

                room.removeBookedDates(guest.getDate1(),guest.getDate2());
            }
            else {
                this.refundPrice= 0;

            }

        }
    }
    public void endBooking() {
        room.removeBookedDates(guest.getDate1(),guest.getDate2());
    }
    public int getNumberOfNights() {
        return numberOfNights;}
    public void setNrOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
    public int getRoomNumber() {
        return room.getRoomNumber();
    }
    public String getGuestName() {
        return guest.getName();
    }
    public LocalDate getStartingDate() {
        return guest.getDate1();
    }
    public LocalDate getEndDate() {
        return guest.getDate2();
    }

}
