package Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 1428379279566570853L;

    private int roomNumber;
    private RoomType type;
    private double price;
    ArrayList<LocalDate> bookedDates=new ArrayList<>();
    public Room(int roomNumber, RoomType type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }
    public String getTypeAsString(){
        if(getType()==RoomType.SINGLE){
            return "SINGLE";
        }
        else if(getType()==RoomType.DOUBLE){
            return "DOUBLE";
        }
        else{
            return "TWIN";
        }

    }
    public void setTypeAsString(String s){
        if (s=="SINGLE"){
            this.type=RoomType.SINGLE;
        }
        else if(s=="DOUBLE"){
            this.type=RoomType.DOUBLE;
        }
        else if(s=="TWIN"){
            this.type=RoomType.TWIN;
        }

    }

    public double getPrice() {
        return price;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable(LocalDate date1, LocalDate date2) {
        if (bookedDates.isEmpty()) {
            return true; // No bookings, room is available
        }

        for (int i = 0; i < bookedDates.size(); i += 2) { // Loop through booked date pairs
            LocalDate bookedStart = bookedDates.get(i);
            LocalDate bookedEnd = bookedDates.get(i + 1);

            if (!((date2.isBefore(bookedStart)||date2.isEqual(bookedStart)) || (date1.isAfter(bookedEnd)||date1.isEqual(bookedEnd)))) {
                return false; // Overlap found, room is not available
            }
        }
        return true;
    }

    public String bookedDatesPrint(){
        String s="";
        for (int i=0;i<bookedDates.size();i++) {

            s=s+bookedDates.get(i)+" ";
            if(i%2!=1)s=s+"- ";
        }

        return s;
    }
    public void setBookedDates(LocalDate date1, LocalDate date2) {
        if (!isAvailable(date1, date2)) {
            System.out.println("ERROR: Date is already booked.");
            return;
        }

        bookedDates.add(date1);
        bookedDates.add(date2);
        bookedDates.sort(LocalDate::compareTo);
    }

    public void removeBookedDates(LocalDate Date1,LocalDate Date2){
        this.bookedDates.remove(Date1);
        this.bookedDates.remove(Date2);

    }
    public void orderList(){

    }
    @Override
    public String toString() {
        return "Room{" + "roomNumber=" + roomNumber + ", type=" + type +", price=" + price + ", bookedDates=" + bookedDates + '}';
    }
}
