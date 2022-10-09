module grupobala {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    opens grupobala to javafx.fxml;
    exports grupobala;
}