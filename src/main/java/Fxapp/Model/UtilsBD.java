package Fxapp.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class UtilsBD {

	/**
	 * Conecta con la BD
	 * 
	 * @return Objeto Connection si la conexión es exitosa, null en caso contrario.
	 */
	public static Connection ConectarBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Dotenv dotenv = Dotenv.configure().filename(".env").load();

			String usuario = dotenv.get("DBUSER");
			String password = dotenv.get("DBPASSWORD");
			String puerto = dotenv.get("DBPORT");
			String host = dotenv.get("DBHOST");
			String dbname = dotenv.get("DBNAME");

			// Se usa DriverManager.getConnection con los parámetros obtenidos de .env
			Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + puerto + "/" + dbname, usuario,
					password);
			if (con != null) {
				System.out.println("Conexión establecida sin errores.");
			}
			return con;

		} catch (ClassNotFoundException e) {
			System.err.println("Error: Driver JDBC no encontrado.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Error de conexión a la base de datos.");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error inesperado al conectar con la base de datos.");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Cierra la conexión a la BD
	 * 
	 * @param con La conexión a cerrar.
	 */
	public static void DesconectarBD(Connection con) {
		if (con != null) {
			try {
				con.close();
				System.out.println("Conexión cerrada.");
			} catch (SQLException e) {
				System.err.println("Error al cerrar la conexión a la base de datos.");
				e.printStackTrace();
			}
		}
	}
}