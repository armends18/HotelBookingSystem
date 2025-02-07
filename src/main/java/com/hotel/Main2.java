package com.hotel;

import Model.Booking;
import Model.Guest;
import Model.Room;
import  Model.RoomType;
import dao.RoomsDao;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        seedData();
        RoomsDao dao = new RoomsDao();
        dao.getAll().get(0).setBookedDates(LocalDate.now(),LocalDate.now().plusDays(3));


         Guest g=new Guest("Rober", "3556789", "Roberto@gmail.com",LocalDate.of(2025,2,9),LocalDate.of(2025,2,9));
        Booking b= new Booking(dao.getAll().get(0),g);
        for (int i=0;i<dao.getAll().size();i++) {
            System.out.println(dao.getAll().get(i).bookedDatesPrint());

        }

//        System.out.println(rooms[0].bookedDatesPrint());
//        boolean availability =rooms[0].isAvailable(LocalDate.of(2025,1,14),LocalDate.of(2025,1,19));
//        System.out.println(availability);
//        RoomsDao roomsDao = new RoomsDao().create(rooms);
//
//        rooms[0].removeBookedDates(date2,date4);
//        System.out.println(rooms[0].bookedDatesPrint());
//        availability =rooms[0].isAvailable(LocalDate.of(2025,1,31),LocalDate.of(2025,2,14));
//        System.out.println(availability);
//        Guest guest =new Guest("Guest","06800000008","Guest@gmailcom",LocalDate.of(2025, 3, 12),LocalDate.of(2025,3,15));
//        Booking booking= new Booking(rooms[0],guest);
//        booking.generateFirstInvoice();
//        booking.cancelBooking();
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
