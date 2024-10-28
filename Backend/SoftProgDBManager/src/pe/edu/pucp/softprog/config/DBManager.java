package pe.edu.pucp.softprog.config;

import java.sql.DriverManager;
import java.sql.Connection;

public class DBManager {

    private Connection con;
    private static DBManager dbManager;
    private static String url = "jdbc:mysql://ta-ayp-prog3-20242.cbu6smk6ic6s.us-east-1.rds.amazonaws.com:3306/taProg3";
    private static String username = "admin";
    private static String password = "amorypaz_psw";

    public static String getUrl() {
            return url;
    }

    public static String getUsername() {
            return username;
    }

    public static String getPassword() {
            return password;
    }

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }

    public static DBManager getInstance(){
        if(dbManager == null){
            createInstance();
        }
        return dbManager;
    }

    private synchronized static void createInstance(){
        if(dbManager == null){
            dbManager = new DBManager();
        }
    }
}