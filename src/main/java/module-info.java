module com.example.examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;



    opens com.example.examen to javafx.fxml;
    exports com.example.examen;
}