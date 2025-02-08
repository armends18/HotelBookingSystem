package com.hotel;

import Model.Booking;
import Model.Guest;
import Model.Room;
import  Model.RoomType;
import controllers.LoginController;
import dao.GuestDao;
import dao.RoomsDao;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

import static javafx.application.Application.launch;


    public class Main2 extends Application {
        private static final Screen screen = Screen.getPrimary();
        public static final Rectangle2D VISUAL_BOUNDS = screen.getVisualBounds();

        public static void main(String[] args) {
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
    }

