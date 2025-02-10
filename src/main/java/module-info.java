module farmhub.farmhub.controllers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens farmhub.controllers to javafx.fxml;
    exports farmhub.controllers;
    exports farmhub;
}