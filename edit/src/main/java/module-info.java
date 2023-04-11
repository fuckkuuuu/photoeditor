module com.example.edit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens com.example.edit to javafx.fxml;
    exports com.example.edit;
}