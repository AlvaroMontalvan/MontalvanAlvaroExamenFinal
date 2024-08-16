package MADataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MASQLiteDataHelper {
    private static String DBPathConnection = "jdbc:sqlite:MADataBase//MAHormiguero.sqlite";  
    private static Connection connection = null;  
    // protected SQLiteDataHelper(){}

    protected static synchronized Connection openConnection() throws Exception{ 
        try {
            if(connection == null)   
            connection = DriverManager.getConnection(DBPathConnection);
        } catch (SQLException e) { 
            throw e;
        }
        return connection;
    }

    protected static void cerrarConexion() throws Exception{
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
