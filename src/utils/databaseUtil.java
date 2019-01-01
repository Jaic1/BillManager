package utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class databaseUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //public static String ip = "127.0.0.1";
    //public static String encoding = "UTF-8";
    public static String account = "root";
    public static String password = "admin";
    public static int port = 3306;
    public static String databaseName = "billmanager";
    public static String desktopDir = "C:\\Users\\chj\\Desktop";

    public static Connection getConnection() throws SQLException{
        //String sql = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&serverTimezone=GMT%2B8",ip,port,databaseName,encoding);
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/billmanager?characterEncoding=UTF-8&serverTimezone=GMT%2B8",
                account,password);
    }

    public static void backup(String mysqlPath,String backupFilePath) throws IOException {
        String cmdFormat = "\"%s\\bin\\mysqldump.exe\" -u%s -p%s  -hlocalhost -P%d %s -r \"%s\"";
        String cmd = String.format(cmdFormat, mysqlPath,account,password,port,
                databaseName,backupFilePath);
        Runtime.getRuntime().exec(cmd);
    }

    public static void recover(String mysqlPath,String recoverFilePath) throws Exception {
        String cmdFormat = "\"%s\\bin\\mysql.exe\" -u%s -p%s %s";
        String cmd = String.format(cmdFormat, mysqlPath,account,password,databaseName);
        Process p = Runtime.getRuntime().exec(cmd);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverFilePath), "utf8"));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\r\n");
        }
        br.close();

        OutputStream out = p.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
        writer.write(sb.toString());
        writer.flush();
        writer.close();
        out.close();
    }
}
