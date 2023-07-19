package org.persistence;

import java.sql.*;

public class Main {
    private static String url = "jdbc:sqlserver://DESKTOP-71CK2QV\\SQLEXPRESS:1433;database=TicketSystem;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static String userName = "";
    private static String password = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RunDemo();
    }

    public static void RunDemo() {
        try {
            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * from Customers");

            while(results.next()) {
                System.out.println("aaa");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}

