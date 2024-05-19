module org.example.salon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.security.sasl;


    opens org.example.salon to javafx.fxml;
    exports org.example.salon;
}