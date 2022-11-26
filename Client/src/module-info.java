module Client {
    requires java.sql;
    requires AppDataReader;
    requires ORM;
    requires Email;
    exports Client;
    exports Client.Controllers;
}