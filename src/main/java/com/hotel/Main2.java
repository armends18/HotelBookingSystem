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
        dao.getAll().get(0).setBookedDates(LocalDate.now(),LocalDate.now().plusDays(3));


         Guest g=new Guest("Rober", "3556789", "Roberto@gmail.com",LocalDate.of(2025,2,9),LocalDate.of(2025,2,10));
        gDao.createGuest(g);
        System.out.println(dao.getAll().get(0).toString());
        Booking b= new Booking(dao.getAll().get(0),gDao.getAll().get(0));
        for (int i=0;i<dao.getAll().size();i++) {
            System.out.println(dao.getAll().get(i).bookedDatesPrint());



        }
        b.generateFirstInvoice();
        b.cancelBooking();

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
