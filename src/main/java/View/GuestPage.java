package View;

import Model.*;
import dao.BookingDao;
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

public class GuestPage extends BorderPane {
    // Shared root layout
    private MenuBar menuBar;       // Shared MenuBar
    private MenuItem logoutItem;
    private Guest guest;
    private Label errorLabel=new Label();
    private BookingDao bookingDao=new BookingDao();
    private Booking booking;
    private int index;
    private Button cancelBookingBtn=new Button("Cancel Booking");
    private Button endBookingBtn=new Button("End Booking");
    private TextArea invoiceArea=new TextArea();
    public void setBooking(Booking b){
        this.booking=b;
    }


    public GuestPage(Guest guest) {
        this.guest=guest;

        for (int i=0;i<bookingDao.getSize();i++){

            if (guest.getUsername().equals(bookingDao.getAll().get(i).getGuest().getUsername())) {
                setBooking(bookingDao.getAll().get(i));
                index = i;
                System.out.println("Booking found!");
                break;
            }
        }
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
        this.setStyle("-fx-background-color: linear-gradient(to right,#67b6b6,#b6e3ff);");

        Label titleLabel = new Label("Hello, " + guest.getName() + "!");
        titleLabel.setFont(new Font("Lucid Handwriting", 28));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #dec000;");
        titleLabel.setAlignment(Pos.CENTER);

        errorLabel.setFont(new Font("Lucid Handwriting", 28));
        errorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ff0000;");
        errorLabel.setAlignment(Pos.CENTER);


        invoiceArea.setEditable(false);
        invoiceArea.setText(booking.generateFirstInvoice());
        invoiceArea.setMaxHeight(400);
        invoiceArea.setMaxWidth(400);
        invoiceArea.setFont(new Font("Calibre",12));

        cancelBookingBtn.setStyle("-fx-border-color: #ffda00;-fx-background-color: #424242;-fx-text-fill: #ffda00");
        endBookingBtn.setStyle("-fx-border-color: #ffda00;-fx-background-color: #424242;-fx-text-fill: #ffda00");

        HBox buttonContainer=new HBox();
        buttonContainer.setSpacing(30);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(30,30,30,30));
        buttonContainer.getChildren().addAll(cancelBookingBtn,endBookingBtn);

        VBox labelBox=new VBox();
        labelBox.getChildren().addAll(titleLabel,errorLabel);
        labelBox.setAlignment(Pos.CENTER);
        GridPane radioBtGrid=new GridPane();







        VBox totalContainer=new VBox();
        totalContainer.getChildren().addAll(labelBox,invoiceArea,buttonContainer);
        totalContainer.setAlignment(Pos.CENTER);
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


    public MenuItem getLogoutItem() {
        return logoutItem;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public int getIndex() {
        return index;
    }

    public Booking getBooking() {
        return booking;
    }

    public BookingDao getBookingDao() {
        return bookingDao;
    }

    public Guest getGuest() {
        return guest;
    }

    public Button getCancelBookingBtn() {
        return cancelBookingBtn;
    }

    public Button getEndBookingBtn() {
        return endBookingBtn;
    }
    public TextArea getInvoiceArea(){
        return invoiceArea;
    }

}
