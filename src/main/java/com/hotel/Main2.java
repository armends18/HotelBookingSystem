package com.hotel;

import Model.*;
import controllers.LoginController;
import dao.BookingDao;
import dao.EmployeeDao;
import dao.GuestDao;
import dao.RoomsDao;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.print.Book;
import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

import static javafx.application.Application.launch;


    public class Main2 extends Application {
        private static final Screen screen = Screen.getPrimary();
        public static final Rectangle2D VISUAL_BOUNDS = screen.getVisualBounds();

        public static void main(String[] args) {
            seedData();
            seedGuestData();
            seedBookingData();
            seedEmployeeData();


            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            System.out.println(VISUAL_BOUNDS);
            LoginController loginController = new LoginController();
            Scene scene = new Scene(loginController.getLoginPage(), 800, 600);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setTitle("3A");
            primaryStage.getIcons().add(new Image("file:src/main/resources/images/appIcon.jpg"));
            primaryStage.show();
        }

        private static void seedData() {
            File file = new File(RoomsDao.FILE_PATH);
            if (file.length() == 0) {
                Room[] rooms = {
                        new Room(1, RoomType.SINGLE, 100),
                        new Room(2, RoomType.SINGLE, 100),
                        new Room(3, RoomType.SINGLE, 100),

                };
                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                    for (Room r : rooms) {
                        outputStream.writeObject(r);
                    }
                } catch (IOException ex) {
                    // log
                }

            }


        }
        private static  void seedEmployeeData() {
            File file2 = new File(EmployeeDao.FILE_PATH);
            if (file2.length() == 0) {
                Employee[] employees = {
                        new Employee("Jimmy","Jimmy","12345")
                };
                try(ObjectOutputStream outputStream1 =new ObjectOutputStream(new FileOutputStream(file2))){
                    for (Employee e : employees) {
                        outputStream1.writeObject(e);
                        System.out.println("yes");
                    }


                } catch (IOException e) {
                    //
                }
            }
        }
        public static  void seedGuestData() {
            File file = new File(GuestDao.FILE_PATH);
            if (file.length() == 0) {
                Guest[] guests = {
                        new Guest("Robert","0688899033","robert@gmail.com",LocalDate.of(2025,2,22),LocalDate.of(2025,2,25))
                };
                try (ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(file))){
                    for (Guest g : guests) {
                        outputStream.writeObject(g);
                    }
                }
                catch(IOException e){
                    //
                }
            }
        }
        public static void seedBookingData() {
            File file = new File(BookingDao.FILE_PATH);
            GuestDao guestDao = new GuestDao();
            RoomsDao roomsDao = new RoomsDao();
            EmployeeDao employeeDao = new EmployeeDao();
            if (file.length() == 0) {
                Booking booking1 = new Booking(roomsDao.getAll().get(0),guestDao.getAll().get(0));try(ObjectOutputStream  outputStream=new ObjectOutputStream(new FileOutputStream(file))){
                    outputStream.writeObject(booking1);

                } catch (IOException e) {
                }
            }

        }
    }

