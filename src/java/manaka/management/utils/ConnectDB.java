package manaka.management.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {
    private Connection connection;
    
    public Connection getConnection(){
        if(connection == null){
            String url = "jdbc:postgresql://localhost/Management";
            String user = "postgres";
            String password = "root";
            try {
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(ConnectDB.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
            System.out.println("Namorona connection vaovao le ol");
        }
        return connection;
    }
}