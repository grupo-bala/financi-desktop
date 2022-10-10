module grupobala {
    requires transitive javafx.controls;
    requires java.sql;
    requires javafx.fxml;

    opens grupobala to javafx.fxml;
    exports grupobala;
}