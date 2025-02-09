package dao;

import Model.Employee;
import Model.Exceptions.AlreadyExists;
import Model.Exceptions.WrongPassword;
import Model.Exceptions.WrongUsername;
import Model.Guest;
import Model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.List;

public class GuestDao {
    public static final String FILE_PATH = "src/main/java/DAO/guests.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final ObservableList<Guest> guests = FXCollections.observableArrayList();
    public ObservableList<Guest> getAll() {
        if(guests.isEmpty()) {
            loadGuestFromFile();
        }
        return guests;
    }

    public GuestDao() {
        loadGuestFromFile();
        //this.view
    }

    private void loadGuestFromFile() {
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(DATA_FILE))){
            Guest guest;
            while(true) {
                guest=(Guest) reader.readObject();
                guests.add(guest);
            }

        }
        catch (EOFException ignored) {
        }
        catch (IOException | ClassNotFoundException ex) {
            // log to a file
            System.out.println(ex.getMessage());
        }
    }

    public boolean createGuest(Guest guest) {
        try(FileOutputStream outputStream = new FileOutputStream(DATA_FILE, true)) {
            ObjectOutputStream writer;
            if(DATA_FILE.length()>0){
                writer = new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer = new ObjectOutputStream(outputStream);
            }
            writer.writeObject(guest);
            this.guests.add(guest);
            return true;
        } catch(IOException ex) {
            return false;
        }
    }

    public boolean deleteGuest(Guest guest) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Guest g:guests) {
                if(!g.equals(guest)) {
                    outputStream.writeObject(g);
                }
            }

            guests.remove(guest);

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean deleteAll(List<Guest> guestsToRemove) {

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Guest g: guests) {
                if (!guestsToRemove.contains(g)) {
                    writer.writeObject(g);
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
            for(Guest g:guests) {
                outputStream.writeObject(g);
            }

            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    public Guest authLogin(String username, String password) throws WrongPassword, WrongUsername {
        for(Guest g : guests) {
            if(g.getUsername().equals(username)) {
                if(g.getPassword().equals(password)) {
                    return g;

                }
                else throw new WrongPassword("Password is incorrect");
            }

        }
        throw new WrongUsername("Username is wrong");
    }
    public boolean guestExists(String username) throws AlreadyExists {
        for(Guest g : guests) {
            if (username==null||username.isEmpty()){
                throw new AlreadyExists("Invalid Username");
            }
            else if(g.getUsername() != null && g.getUsername().equals(username)) {
                throw new AlreadyExists("Username is wrong");
            }

        }
        return false;
    }

}
