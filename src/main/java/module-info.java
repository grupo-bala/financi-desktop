module grupobala {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens grupobala to javafx.fxml;
    exports grupobala;
}