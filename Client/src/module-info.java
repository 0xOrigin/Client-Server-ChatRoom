module Client {
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires AppDataReader;
    requires ORM;
    requires Email;
    opens Client.Views;
    exports Client.Views;
    exports Client.Controllers;
    exports Client.Models;
}