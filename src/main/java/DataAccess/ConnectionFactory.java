package DataAccess;

import org.postgresql.Driver;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() {

        try{

            Properties dbconfig = new Properties();
            InputStream input = new FileInputStream("dbconfig.properties");
            dbconfig.load(input);

            String URL = dbconfig.getProperty("dburl");
            String DBNAME = dbconfig.getProperty("dbname");
            String USER = dbconfig.getProperty("dbuser");
            String PASS = dbconfig.getProperty("dbpassword");

            DriverManager.registerDriver(new Driver());

            return DriverManager.getConnection((URL+DBNAME), USER, PASS);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }

        return null;

    }

    public static void main(String[] args) {

        boolean success = true;

        try{
            Connection connection = ConnectionFactory.getConnection();
        } catch (Exception e){
            success = false;
            e.printStackTrace();
            System.out.println("Connection failed. Check your config file");
        }

        if(success){
            System.out.println("Connection successful");
        }

    }

}