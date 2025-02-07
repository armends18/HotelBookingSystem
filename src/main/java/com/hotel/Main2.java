package com.hotel;

import Model.Booking;
import Model.Guest;
import Model.Room;
import  Model.RoomType;
import dao.GuestDao;
import dao.RoomsDao;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        seedData();
        RoomsDao dao = new RoomsDao();
        GuestDao gDao = new GuestDao();



    }
    private static void seedData() {
        File file = new File(RoomsDao.FILE_PATH);
        if(file.length() == 0) {
           Room[] rooms= {
                   new Room(1,RoomType.SINGLE, 100),
                   new Room(2,RoomType.SINGLE, 100),
                   new Room(3,RoomType.SINGLE, 100),

            };
            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                for(Room r : rooms) {
                    outputStream.writeObject(r);
                }
            } catch (IOException ex) {
                // log
            }
        }
    }

}
