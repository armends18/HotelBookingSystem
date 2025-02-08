package dao;

import Model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.List;

public class RoomsDao {
    public static final String FILE_PATH = "src/main/java/DAO/rooms.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final ObservableList<Room> rooms = FXCollections.observableArrayList();
    public ObservableList<Room> getAll() {
        if(rooms.isEmpty()) {
            loadRoomsFromFile();
        }
        return rooms;
    }

    public RoomsDao() {
        loadRoomsFromFile();
        //this.view
    }

    private void loadRoomsFromFile() {
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(DATA_FILE))){
            Room room;
            while(true) {
                room=(Room) reader.readObject();
                rooms.add(room);
            }

        }
        catch (EOFException ignored) {
        }
        catch (IOException | ClassNotFoundException ex) {
            // log to a file
            System.out.println(ex.getMessage());
        }
    }

    public boolean create(Room room) {
        try(FileOutputStream outputStream = new FileOutputStream(DATA_FILE, true)) {
            ObjectOutputStream writer;
            if(DATA_FILE.length()>0){
                writer = new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer = new ObjectOutputStream(outputStream);
            }
            writer.writeObject(room);
            this.rooms.add(room);
            return true;
        } catch(IOException ex) {
            return false;
        }
    }

    public boolean delete(Room room) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Room r: rooms) {
                if(!r.equals(rooms)) {
                    outputStream.writeObject(r);
                }
            }

            rooms.remove(room);

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean deleteAll(List<Room> roomsToRemove) {

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Room r: rooms) {
                if (!roomsToRemove.contains(r)) {
                    writer.writeObject(r);
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
            for(Room r : rooms) {
                outputStream.writeObject(r);
            }

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
