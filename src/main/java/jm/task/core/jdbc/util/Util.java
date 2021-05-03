package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/firsttask?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = System.getenv("PASSWORD");
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Util() {
        try {
            connection = DriverManager.getConnection(URL , USERNAME , PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
