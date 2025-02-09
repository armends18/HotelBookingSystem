package View;

import Model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class NewGuestPage extends BorderPane {
    // Shared root layout
    private MenuBar menuBar;       // Shared MenuBar


    private MenuItem profileItem;
    private MenuItem logoutItem;

    private final TableView<Room> tableView=new TableView<>();
    private final TableColumn<Room,Integer> roomNumberC;
    private final TableColumn<Room, String> roomType;
    private final TableColumn<Room, Double> roomPrice;
    private final Button bookingBt=new Button("New Booking");
    private final Button showRoomsBt=new Button("Show Available Rooms");
    private DatePicker dateFrom = new DatePicker();
    private DatePicker dateTo = new DatePicker();
    private Guest guest;


    public NewGuestPage(Guest guest) {
        this.guest=guest;

        menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color:#fff598; -fx-text-fill: #ffffff;");
        Menu logoutMenu = createStyledMenu("Log Out");
        logoutItem = createStyledMenuItem("Log Out");
//        ImageView imageView = new ImageView("file:src/main/resources/images/appIcon.jpg");
//        imageView.setFitWidth(250);
//        imageView.setFitHeight(250);
//        imageView.setPreserveRatio(true);
//        logoutMenu.getItems().add(logoutItem);
        menuBar.getMenus().addAll(logoutMenu);
        this.setTop(menuBar);
        this.setStyle("-fx-background-color: linear-gradient(to right,#58a7df,#b6e3ff);");
        Label titleLabel = new Label("Hello, " + guest.getName() + "!");
        titleLabel.setFont(new Font("Lucida Handwriting", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #dec000;");
        tableView.setEditable(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        roomNumberC =new TableColumn<>("Room Number");
        roomNumberC.setMinWidth(100);
        roomNumberC.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
        roomNumberC.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        roomNumberC.setMaxWidth(150);

        roomType=new TableColumn<>("Guests");
        roomType.setMinWidth(100);
        roomType.setMaxWidth(150);
        roomType.setCellValueFactory(new PropertyValueFactory<>("TypeAsString"));
        roomType.setCellFactory(TextFieldTableCell.forTableColumn());

        roomPrice=new TableColumn<>("Number of night");
        roomPrice.setMinWidth(100);
        roomPrice.setMaxWidth(150);
        roomPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        roomPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tableView.getColumns().addAll(roomNumberC,roomType,roomPrice);



        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefSize(250,300);

        HBox tableContainer = new HBox();
        tableContainer.setSpacing(10);
        tableContainer.getChildren().addAll(titleLabel,scrollPane);
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
    public TableView getTableView(){
        return tableView;
    }
    public MenuItem getProfileItem() {
        return profileItem;
    }

    public MenuItem getLogoutItem() {
        return logoutItem;
    }

    public TableColumn<Room, Integer> getRoomNumberC() {
        return roomNumberC;
    }

    public TableColumn<Room, String> getRoomType() {
        return roomType;
    }

    public TableColumn<Room, Double> getRoomPrice() {
        return roomPrice;
    }

    public Button getBookingBt() {
        return bookingBt;
    }

    public Button getShowRoomsBt() {
        return showRoomsBt;
    }
}
