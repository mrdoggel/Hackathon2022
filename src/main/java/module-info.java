module com.example.hackathon2022 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens com.example.hackathon2022 to javafx.fxml;
    exports com.example.hackathon2022;
}