package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class LibraryConnectDb {
    private Connection conn = null;
    LibraryReadProp objPro = new LibraryReadProp();
    Properties prop = objPro.readProp("config.properties");
    String url = prop.getProperty("url");
    String user = prop.getProperty("user");
    String password = prop.getProperty("password");

    
    public LibraryConnectDb(){

    }
    

    public Connection getConnectMySQL(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Không thể load driver");
        }
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sai thông số kết nối");
        }
        
        return conn;
    }
    

}
