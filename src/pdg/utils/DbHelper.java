package pdg.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DbHelper {
    private static Connection conn = null;

    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(AppConfig.get().getConnectionString());
        }

        return conn;
    }

    public static void closeConnection() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static void migrate() throws Exception {
        String driverType = AppConfig.get().getDriverType();
        String autoIncFunc = driverType.equals("mysql") ? "AUTO_INCREMENT" : "AUTOINCREMENT";

        ArrayList<String> queries = new ArrayList<>();
        queries.add(
                String.format("CREATE TABLE IF NOT EXISTS users (id INTEGER NOT NULL PRIMARY KEY %s, username VARCHAR(50) NOT NULL,fullName VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, salt VARCHAR(255) NOT NULL, country VARCHAR(50) NOT NULL, numberOfWins integer NOT NULL DEFAULT 0, createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)", autoIncFunc)
        );

        Connection conn = getConnection();
        for (String sql : queries) {
            conn.createStatement().executeUpdate(sql);
        }
    }
}
