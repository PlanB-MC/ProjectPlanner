package val.pp.controller;

import java.sql.*;

public class dbController {
    private Connection con;
    private static Statement stmt;

    public dbController(String uName, String pWord) {
        System.out.println("   Loading JDBC driver for MS SQL Server database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.printf("   Unable to load JDBC driver... '%s'\n", e.getMessage());
            return;
        }
        try {
            System.out.println("   Locate database to open (using connection string)...");

            String connectionString = "jdbc:mysql://91.121.210.171:3306/s6_ProjectPlanner";
            System.out.println("      Connection string = " + connectionString);

            //TODO: remove this
            // create connection to DB, including username & password
            con = DriverManager.getConnection(connectionString, "u6_X4LuJb7KRn", "RBMgbJ7E1NS08W98");
            // create statement object for manipulating DB
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.printf("   Unable to connect to DB... '%s'\n", e.getMessage());
        }
    }

    public static void execute(String sql) throws SQLException {
        stmt.execute(sql);
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        return stmt.executeQuery(sql);
    }
}
