module grupobala {
    requires transitive javafx.controls;
    requires java.sql;
    requires javafx.fxml;
    requires itextpdf;
    requires com.opencsv;

    opens grupobala to javafx.fxml;
    exports grupobala;
}
