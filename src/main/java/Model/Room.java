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
            return "SINGLE";
        }
        else{
            return "SINGLE";
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
        if (bookedDates.get(0).isAfter(date2)&& date2.isAfter(date1)) {
            return true;

        }
        for (int i=1;i<(bookedDates.size()-1);i=i+2 ) {
            if ((bookedDates.get(i).isBefore(date1)|| bookedDates.get(i).isEqual(date1)) && bookedDates.get(i+1).isAfter(date2)) {
                    return true;

            }

        }
        if (bookedDates.get(bookedDates.size()-1).isBefore(date1)|| bookedDates.get(bookedDates.size()-1).isEqual(date1)) {
            return true;
        }

        return false;
    }
    public String bookedDatesPrint(){
        String s="";
        for (int i=0;i<bookedDates.size();i++) {
            s=s+bookedDates.get(i)+" ";
            if(i%2!=0)s=s+"\n";
        }

        return s;
    }
    public void setBookedDates(LocalDate date1,LocalDate date2) {
       if (bookedDates.isEmpty()) {
        this.bookedDates.add(date1);
        this.bookedDates.add(date2);
       }
       else if(isAvailable(date1,date2)) {
               this.bookedDates.add(date1);
               this.bookedDates.add(date2);
           }
       else
           System.out.println("ERROR: Booked date is not available");
    }
    public void removeBookedDates(LocalDate Date1,LocalDate Date2){
        this.bookedDates.remove(Date1);
        this.bookedDates.remove(Date2);

    }
    @Override
    public String toString() {
        return "Room{" + "roomNumber=" + roomNumber + ", type=" + type +", price=" + price + ", bookedDates=" + bookedDates + '}';
    }
}
