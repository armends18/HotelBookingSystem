package View;

import Model.Booking;
import Model.Employee;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import dao.BookingDao;
import javafx.util.converter.IntegerStringConverter;

public class HomePage extends BorderPane {
    // Shared root layout
    private MenuBar menuBar;       // Shared MenuBar
    private Employee employee;

    private MenuItem profileItem;
    private MenuItem logoutItem;
    private VBox sidebarHome = new VBox();
    private final TableView<Booking> tableView=new TableView<>();
    private final TableColumn<Booking,Integer> roomNumber;
    private final TableColumn<Booking,String> guestsName;
    private final TableColumn<Booking,Integer> numberOfNights;
    private final TableColumn<Booking, LocalDate> startingDate;
    private final TableColumn<Booking,LocalDate> endDate ;
    private final Button deleteBookingBt=new Button("Delete Booking");
    private final Button endBookingBt=new Button("End Booking");
    private final Button generateFinalInvoice=new Button("Generate final Invoice for client");
    private final Button addBookingBt=new Button("Go to New Booking Menu");
    private final Button showRoomsBt=new Button("Show Available Rooms");



    public HomePage(Employee employee) {


        menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color:#fff598; -fx-text-fill: #ffffff;");
        Menu logoutMenu = createStyledMenu("Log Out");
        logoutItem = createStyledMenuItem("Log Out");
        ImageView imageView = new ImageView("file:src/main/resources/images/appIcon.jpg");
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);
        logoutMenu.getItems().add(logoutItem);
        menuBar.getMenus().addAll(logoutMenu);
        this.setTop(menuBar);
        sidebarHome.getChildren().add(imageView);
        this.setStyle("-fx-background-color: linear-gradient(to right,#58a7df,#b6e3ff);");
        Label titleLabel = new Label("Hello, " + employee.getName() + "!");
        titleLabel.setFont(new Font("Lucida Handwriting", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #dec000;");
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        roomNumber=new TableColumn<>("Room Number");
        roomNumber.setMinWidth(100);
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
        roomNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        roomNumber.setMaxWidth(150);

        guestsName=new TableColumn<>("Guests");
        guestsName.setMinWidth(100);
        guestsName.setMaxWidth(150);
        guestsName.setCellValueFactory(new PropertyValueFactory<>("GuestName"));
        guestsName.setCellFactory(TextFieldTableCell.forTableColumn());

        numberOfNights=new TableColumn<>("Number of night");
        numberOfNights.setMinWidth(100);
        numberOfNights.setMaxWidth(150);
        numberOfNights.setCellValueFactory(new PropertyValueFactory<>("NumberOfNights"));
        numberOfNights.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        startingDate=new TableColumn<>("Start Date");
        startingDate.setMinWidth(100);
        startingDate.setMaxWidth(150);
        startingDate.setCellValueFactory(new PropertyValueFactory<>("StartingDate"));


        endDate=new TableColumn<>("End Date");
        endDate.setMinWidth(100);
        endDate.setMaxWidth(150);
        endDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        tableView.getColumns().addAll(roomNumber,guestsName,numberOfNights,startingDate,endDate);

        deleteBookingBt.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #ff0000;-fx-background-radius: 5;-fx-border-radius: 5;-fx-border-color: #ff0000;-fx-border-width: 5");
        VBox buttonContainer = new VBox(10, endBookingBt, generateFinalInvoice, addBookingBt, showRoomsBt);

        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(10, 0, 0, 0));
        sidebarHome.getChildren().add(buttonContainer);
        this.setRight(sidebarHome);
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefSize(250,300);

        VBox tableContainer = new VBox();
                tableContainer.setSpacing(10);
        tableContainer.getChildren().addAll(titleLabel,scrollPane,deleteBookingBt);
        tableContainer.setAlignment(Pos.CENTER);
        tableContainer.setPadding(new Insets(20,20,20,20));
        this.setCenter(tableContainer);

    }


    private Menu createStyledMenu(String text) {
        Menu menu = new Menu(text);
        menu.setStyle("-fx-text-fill: #b6e3ff; -fx-font-size: 14px;");
        return menu;
    }

    // Method to create a styled MenuItem
    private MenuItem createStyledMenuItem(String text) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.setStyle("-fx-background-color: transparent; -fx-text-fill: #404436; -fx-font-size: 14px;");
        return menuItem;
    }

    public MenuItem getProfileItem() {
        return profileItem;
    }

    public MenuItem getLogoutItem() {
        return logoutItem;
    }

    public VBox getSidebarHome() {
        return sidebarHome;
    }
    public TableColumn<Booking,Integer> getRoomNumber() {
        return roomNumber;
    }
    public TableColumn<Booking,String> getGuestsName() {
        return guestsName;
    }
    public TableColumn<Booking,Integer> getNumberOfNights() {
        return numberOfNights;
    }
    public TableColumn<Booking,LocalDate> getStartingDate() {
        return startingDate;
    }
    public TableColumn<Booking,LocalDate> getEndDate() {
        return endDate;
    }
    public TableView<Booking> getTableView() {
        return tableView;
    }

    public Button getDeleteBookingBt() {
        return deleteBookingBt;
    }

    public Button getEndBookingBt() {
        return endBookingBt;
    }

    public Button getGenerateFinalInvoice() {
        return generateFinalInvoice;
    }

    public Button getAddBookingBt() {
        return addBookingBt;
    }

    public Button getShowRoomsBt() {
        return showRoomsBt;
    }

}