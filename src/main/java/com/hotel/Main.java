package com.hotel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root Layout
        TabPane tabPane = new TabPane();

        // Tabs
        Tab roomsTab = createRoomsTab();
        Tab bookingsTab = createBookingsTab();
        Tab cancellationsTab = createCancellationsTab();

        tabPane.getTabs().addAll(roomsTab, bookingsTab, cancellationsTab);

        // Scene Setup
        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Hotel Booking System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab createRoomsTab() {
        Tab tab = new Tab("Rooms");
        tab.setClosable(true);

        TableView<String> roomTable = new TableView<>(); // Replace String with Room class later
        roomTable.setPlaceholder(new Label("No rooms available"));

        Button addRoomButton = new Button("Add Room");
        addRoomButton.setOnAction(e -> showAddRoomDialog());

        VBox layout = new VBox(10, new Label("Manage Rooms"), roomTable, addRoomButton);
        layout.setPadding(new Insets(10));
        tab.setContent(layout);

        return tab;
    }

    private Tab createBookingsTab() {
        Tab tab = new Tab("Bookings");
        tab.setClosable(false);

        TableView<String> bookingTable = new TableView<>(); // Replace String with Booking class later
        bookingTable.setPlaceholder(new Label("No bookings available"));

        Button addBookingButton = new Button("Add Booking");
        addBookingButton.setOnAction(e -> showAddBookingDialog());

        VBox layout = new VBox(10, new Label("Manage Bookings"), bookingTable, addBookingButton);
        layout.setPadding(new Insets(10));
        tab.setContent(layout);

        return tab;
    }

    private Tab createCancellationsTab() {
        Tab tab = new Tab("Cancellations");
        tab.setClosable(false);

        TableView<String> cancellationTable = new TableView<>(); // Replace String with Cancellation class later
        cancellationTable.setPlaceholder(new Label("No cancellations available"));

        VBox layout = new VBox(10, new Label("Manage Cancellations"), cancellationTable);
        layout.setPadding(new Insets(10));
        tab.setContent(layout);

        return tab;
    }

    private void showAddRoomDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Room");

        // Dialog UI
        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Room Number");

        TextField roomTypeField = new TextField();
        roomTypeField.setPromptText("Room Type");

        TextField priceField = new TextField();
        priceField.setPromptText("Price per Night");

        VBox dialogContent = new VBox(10, roomNumberField, roomTypeField, priceField);
        dialogContent.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(dialogContent);

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        dialog.showAndWait();
    }

    private void showAddBookingDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Booking");

        // Dialog UI
        TextField guestNameField = new TextField();
        guestNameField.setPromptText("Guest Name");

        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Room Number");

        DatePicker checkInDatePicker = new DatePicker();
        checkInDatePicker.setPromptText("Check-in Date");

        DatePicker checkOutDatePicker = new DatePicker();
        checkOutDatePicker.setPromptText("Check-out Date");

        VBox dialogContent = new VBox(10, guestNameField, roomNumberField, checkInDatePicker, checkOutDatePicker);
        dialogContent.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(dialogContent);

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
