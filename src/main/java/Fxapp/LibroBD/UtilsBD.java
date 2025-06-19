package Fxapp.LibroBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class UtilsBD {

    public static Connection ConectarBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Dotenv dotenv = Dotenv.configure().filename(".env").load();
            String url = construirURL(dotenv);
            
            Connection con = DriverManager.getConnection(
                url, 
                dotenv.get("DBUSER"), 
                dotenv.get("DBPASSWORD")
            );
            
            if (con != null) {
                System.out.println("Conexión establecida correctamente");
            }
            return con;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado");
            e.printStackTrace();
        }
        return null;
    }

    public static void DesconectarBD(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión");
                e.printStackTrace();
            }
        }
    }

    private static String construirURL(Dotenv dotenv) {
        return "jdbc:mysql://" + 
               dotenv.get("DBHOST") + ":" + 
               dotenv.get("DBPORT") + "/" + 
               dotenv.get("DBNAME");
    }
}