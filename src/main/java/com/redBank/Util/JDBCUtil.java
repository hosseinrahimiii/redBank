package com.redBank.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USERNAME = "red";
    private static final String DB_PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try{
            //Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            conn.setAutoCommit(false);
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return conn;
    }
}
