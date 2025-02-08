module com.hotel.hotelbookingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.hotel to javafx.fxml;
    exports com.hotel;
    opens controllers to javafx.fxml;
    exports controllers ;
    opens dao to javafx.fxml;
    exports dao;
    opens Model to javafx.fxml;
    exports Model;
    opens View to javafx.fxml;
    exports View;



}