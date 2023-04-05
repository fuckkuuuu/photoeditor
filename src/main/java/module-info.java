module com.example.film {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.film to javafx.fxml;
    exports com.example.film;
}