module com.example.wordle_clone {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.wordle_clone to javafx.fxml;
    exports com.example.wordle_clone;
}