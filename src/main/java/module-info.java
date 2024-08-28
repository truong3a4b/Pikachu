module com.example.pikachu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.pikachu to javafx.fxml;
    exports com.example.pikachu;
}