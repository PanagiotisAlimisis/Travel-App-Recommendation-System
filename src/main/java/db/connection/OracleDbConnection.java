package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDbConnection {
    private static OracleDbConnection dbConnection = null;
    private Connection oracleDbConnection;
    private String username="IT21902";
    private String password="Alim!@()0";
    
    private OracleDbConnection() {
        try {
            String url = "jdbc:oracle:thin:@oracle12c.hua.gr:1521:orcl";
            oracleDbConnection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to Oracle Database has been established.");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static OracleDbConnection getDbConnection() {
        if(null == dbConnection) {
            dbConnection = new OracleDbConnection();
        }

        return dbConnection;
    }
    
    public Connection getOracleConnection() {
    	return this.oracleDbConnection;
    }
}