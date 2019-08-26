package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    String url = "jdbc:mysql://192.168.10.11:3306/examples";
    String user = "homestead", pass = "secret";    
    Connection con;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {            
        }
        return con;
    }
}
