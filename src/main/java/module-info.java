module edu.uab {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.uab to javafx.fxml;
    exports edu.uab;
}