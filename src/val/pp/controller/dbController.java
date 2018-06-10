package val.pp.controller;

import java.sql.*;

public class dbController {
    private static Connection con;
    private static Statement stmt;

    public dbController(String uName, String pWord) {
        System.out.println("\t\tLoading JDBC driver for MySQL database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.printf("\t\tUnable to load JDBC driver... '%s'\n", e.getMessage());
            return;
        }
        try {
            System.out.println("\t\tLocate database to open (using connection string)...");

            String connectionString = "jdbc:mysql://91.121.210.171:3306/s6_ProjectPlanner";
            System.out.println("\t\tConnection string = " + connectionString);

            //TODO: remove this
            // create connection to DB, including username & password

            con = DriverManager.getConnection(connectionString, "u6_X4LuJb7KRn", "RBMgbJ7E1NS08W98");
            // create statement object for manipulating DB
            openDB();
        } catch (Exception e) {
            System.out.printf("\n\t\tUnable to connect to DB... '%s'\n", e.getMessage());
        }
    }

    public static void closeDB() {
        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.printf("\t\tUnable to Close DB... '%s'\n", e.getMessage());
        }
    }

    private static void openDB() {
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            System.out.printf("\t\tUnable to connect to DB... '%s'\n", e.getMessage());
        }
    }

    public static void execute(String sql) throws SQLException {
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            openDB();
            stmt.execute(sql);
        }
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            openDB();
            return stmt.executeQuery(sql);
        }
    }
}
