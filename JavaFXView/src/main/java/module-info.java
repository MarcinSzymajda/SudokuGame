module com.example.javafxview {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;
    requires log4j;
    requires java.sql;


    opens com.example.javafxview to javafx.fxml;
    exports com.example.javafxview;
}