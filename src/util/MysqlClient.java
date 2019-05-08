package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Configures a mysqlClient
 */
public class MysqlClient {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost/starstucom?useServerPrepStmts=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
