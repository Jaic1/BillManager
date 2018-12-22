package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String ip = "127.0.0.1";
    public static int port = 3306;
    public static String databaseName = "billmanager";
    public static String encoding = "UTF-8";
    public static String account = "root";
    public static String password = "admin";

    public static Connection getConnection() throws SQLException{
        String sql = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&&serverTimezone=GMT%2B8",ip,port,databaseName,encoding);
        return DriverManager.getConnection(sql,account,password);
    }
}
