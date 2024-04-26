package jm.task.core.jdbc.util;




import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Util {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/katabd";
    private static final String USER = "root";
    private static final String PSW = "1234";

    public static void main(String[] args) {

    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PSW);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return connection;


    }


}

