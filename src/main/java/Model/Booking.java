package Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = -7417220444497167797L;
    private Room room;
    private Guest guest;
    private double invoicePrice;
    private String invoiceId;
    private double refundPrice;
    public Booking(Room room, Guest guest) {
        this.room = room;
        this.guest = guest;
        this.room.setBookedDates(guest.getDate1(),guest.getDate2());
        this.invoiceId=LocalDate.now().toString();
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

    public void generateFirstInvoice(){
        invoicePrice = room.getPrice()* ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2())*1.2;
        System.out.println("Invoice_"+invoiceId+":");
        System.out.println("You have booked Room "+room.getRoomNumber()+" of type: "+room.getType() +" for "+ (ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2()))+" night/s.");
        System.out.println("The Dates of your stay are: "+guest.getDate1()+" and "+guest.getDate2());
        System.out.println("You have to check out at: "+guest.getDate2()+" at 11.00 am");
        System.out.println("Invoice Price: "+invoicePrice);
        System.out.println("Refund Policy:\nFull refund: Cancel at least 3 days before booking date!\n30% refund: at least 1 day before the booking\n");

    }
    public void generateFinalInvoice(){
        invoicePrice = room.getPrice()* ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2())*1.2;
        System.out.println("Invoice_"+invoiceId+":");
        System.out.println("You have stayed in Room "+room.getRoomNumber()+" for "+ (ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2()))+" night/s.");
        System.out.println("The Dates of your stay were: "+guest.getDate1()+" and "+guest.getDate2());
        System.out.println("Invoice Price: "+invoicePrice);


    }
    public void generateCancellationInvoice(){
        System.out.println("Invoice_"+invoiceId+":");
        System.out.println("You have cancelled your booking of room "+room.getRoomNumber()+" of type: "+room.getType() +" for "+ ChronoUnit.DAYS.between(guest.getDate1(),guest.getDate2())+" nights.");
        System.out.println("The Dates of your stay were: "+guest.getDate1()+" up to "+guest.getDate2());
        System.out.println("Invoice Price: "+invoicePrice);
        System.out.println("Refund Amount: "+refundPrice);

    }

    public void cancelBooking(){
        LocalDate today = LocalDate.now();

        if(guest.getDate1().isAfter(today)){
            if(ChronoUnit.DAYS.between(today,guest.getDate1())>=3){

                this.refundPrice= this.invoicePrice;
                this.invoicePrice=0;
                generateCancellationInvoice();
                room.removeBookedDates(guest.getDate1(),guest.getDate2());

            } else if (ChronoUnit.DAYS.between(today,guest.getDate1())>=1) {
                this.refundPrice= this.invoicePrice*0.3;
                this.invoicePrice=this.invoicePrice-this.refundPrice;
                generateCancellationInvoice();
                room.removeBookedDates(guest.getDate1(),guest.getDate2());
            }
            else {
                this.refundPrice= 0;
                generateCancellationInvoice();
            }

        }
    }
    public void endBooking() {
generateFinalInvoice();
room.removeBookedDates(guest.getDate1(),guest.getDate2());
    }
}
