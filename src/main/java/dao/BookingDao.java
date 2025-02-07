package dao;

import Model.Guest;
import Model.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.List;

public class BookingDao {
    public static final String FILE_PATH = "src/main/java/DAO/booking.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final ObservableList<Booking> bookings = FXCollections.observableArrayList();
    public ObservableList<Booking> getAll() {
        if(bookings.isEmpty()) {
            loadBookingFromFile();
        }
        return bookings;
    }

    public BookingDao() {
        loadBookingFromFile();
        //this.view
    }

    private void loadBookingFromFile() {
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(DATA_FILE))){
            Booking booking;
            while(true) {
                booking=(Booking) reader.readObject();
                bookings.add(booking);
            }

        }
        catch (EOFException ignored) {
        }
        catch (IOException | ClassNotFoundException ex) {
            // log to a file
            System.out.println(ex.getMessage());
        }
    }

    public boolean createBooking(Booking booking) {
        try(FileOutputStream outputStream = new FileOutputStream(DATA_FILE, true)) {
            ObjectOutputStream writer;
            if(DATA_FILE.length()>0){
                writer = new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer = new ObjectOutputStream(outputStream);
            }
            writer.writeObject(booking);
            this.bookings.add(booking);
            return true;
        } catch(IOException ex) {
            return false;
        }
    }

    public boolean deleteBooking(Booking booking) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Booking b: bookings) {
                if(!b.equals(booking)) {
                    outputStream.writeObject(b);
                }
            }

            bookings.remove(booking);

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean deleteAll(List<Booking> bookingsToRemove) {

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Booking b: bookingsToRemove) {
                if (!bookingsToRemove.contains(b)) {
                    writer.writeObject(b);
                }
            }
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }

    public boolean updateAll() {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for(Booking booking: bookings) {
                outputStream.writeObject(booking);
            }

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
