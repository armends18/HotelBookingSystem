package View;

import Model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.math.RoundingMode;

public class NewGuestPage extends BorderPane {
    // Shared root layout
    private MenuBar menuBar;       // Shared MenuBar
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

    private RadioButton singleBt=new RadioButton();
    private RadioButton doubleBt=new RadioButton();
    private RadioButton twinBt=new RadioButton();
    private Label errorLabel=new Label();



    public NewGuestPage(Guest guest) {
        this.guest=guest;

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
        this.setStyle("-fx-background-color: linear-gradient(to right,#58a7df,#b6e3ff);");

        Label titleLabel = new Label("Hello, " + guest.getName() + "!");
        titleLabel.setFont(new Font("Lucid Handwriting", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #dec000;");
        titleLabel.setAlignment(Pos.CENTER);

        errorLabel.setFont(new Font("Lucid Handwriting", 28));
        errorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ff0000;");
        errorLabel.setAlignment(Pos.CENTER);

        VBox labelBox=new VBox();
        labelBox.getChildren().addAll(titleLabel,errorLabel);
        labelBox.setAlignment(Pos.CENTER);
        GridPane radioBtGrid=new GridPane();
        Label RoomType = new Label("Select Room type:");
        Label singleRoom = new Label("Single");
        Label doubleRoom = new Label("Double");
        Label twinRoom = new Label("Twin");
        RoomType.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");
        RoomType.setFont(new Font("Lucid Handwriting", 25));
        singleRoom.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");
        singleRoom.setFont(new Font("Lucid Handwriting", 20));
        doubleRoom.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");
        doubleRoom.setFont(new Font("Lucid Handwriting", 20));
        twinRoom.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");
        twinRoom.setFont(new Font("Lucid Handwriting", 20));
        ToggleGroup tgGr=new ToggleGroup();
        singleBt.setToggleGroup(tgGr);
        doubleBt.setToggleGroup(tgGr);
        twinBt.setToggleGroup(tgGr);
        radioBtGrid.add(RoomType,0,0);
        radioBtGrid.add(singleRoom,0,1);
        radioBtGrid.add(singleBt,1,1);
        radioBtGrid.add(doubleRoom,0,2);
        radioBtGrid.add(doubleBt,1,2);
        radioBtGrid.add(twinRoom,0,3);
        radioBtGrid.add(twinBt,1,3);

        Label startDateLb=new Label("Start Date:");
        Label endDateLb=new Label("End Date");
        GridPane dateGrid=new GridPane();
        dateGrid.add(startDateLb,0,0);
        dateGrid.add(endDateLb,1,0);
        dateGrid.add(dateFrom,0,1);
        dateGrid.add(dateTo,1,1);
        dateGrid.add(bookingBt,0,2);
        dateGrid.add(showRoomsBt,1,2);
        dateGrid.setAlignment(Pos.CENTER);

        tableView.setEditable(true);
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
        scrollPane.setMaxHeight(400);
        scrollPane.setPrefSize(400,400);



        VBox vbox=new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().add(radioBtGrid);
        vbox.getChildren().add(dateGrid);
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(vbox,scrollPane);
        hBox.setAlignment(Pos.CENTER);

        VBox totalContainer=new VBox();
        totalContainer.getChildren().addAll(labelBox,hBox);
        this.setCenter(totalContainer);

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

    public Button getShowRoomsBt() {
        return showRoomsBt;
    }

    public Button getBookingBt() {
        return bookingBt;
    }
    public DatePicker getDateFrom() {
        return dateFrom;
    }

    public DatePicker getDateTo() {
        return dateTo;
    }

    public RadioButton getSingleBt() {
        return singleBt;
    }

    public RadioButton getDoubleBt() {
        return doubleBt;
    }

    public RadioButton getTwinBt() {
        return twinBt;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
