package main.javafinalsprint;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            ConsoleApp consoleApp = new ConsoleApp();
            consoleApp.start();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

