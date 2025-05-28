package Fxapp.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.ComboBox;

public class LibroDAO {

	/**
	 * La función devuelve todos los libros de la bd
	 * 
	 * @param con Conexion activa a la bd
	 * @return ArrayList de LibroDO con los libros
	 */
	public static ArrayList<LibroDO> getLibros(Connection con) {
		ArrayList<LibroDO> listaLibros = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM libro");

			while (rs.next()) {
				LibroDO libro = new LibroDO();
				libro.setIdLibro(rs.getInt("idLibro"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setGenero(rs.getString("genero"));
				libro.setAutor(rs.getString("autor"));
				libro.setAnioPublicacion(rs.getString("anoPublicacion"));
				libro.setPortada(rs.getString("portada"));
				libro.setDisponible(rs.getBoolean("disponible"));

				listaLibros.add(libro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Es importante cerrar la conexión cuando ya no se necesita,
			// aunque en este caso la gestión de la conexión se hace fuera de DAO
			// por los métodos estáticos que reciben la conexión.
			// Si la conexión se abre y cierra dentro de cada método DAO, es mejor.
		}
		return listaLibros; // Siempre devuelve una lista, aunque esté vacía
	}

	/**
	 * La función elimina el libro asociado a la id que introducimos
	 * 
	 * @param con Conexion activa a la bd
	 * @param id  ID del libro a eliminar
	 * @return 1 si ha podido borrar el libro, -1 si falla
	 */
	public static int deleteLibro(Connection con, int id) {
		try {
			String query = "DELETE FROM libro WHERE idLibro = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			int resultado = pstmt.executeUpdate();
			return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Funcion que inserta un nuevo libro
	 * 
	 * @param con   Conexion a la BD
	 * @param libro Objeto de la clase LibroDO con los datos a insertar
	 * @return -1 si falla o no hay datos, 1 si ha podido insertar el registro
	 */
	public static int insertLibro(Connection con, LibroDO libro) {
		if (libro == null) {
			return -1;
		}
		try {
			String query = "INSERT INTO libro (titulo, genero, autor, anoPublicacion, portada, disponible) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, libro.getTitulo());
			pstmt.setString(2, libro.getGenero());
			pstmt.setString(3, libro.getAutor());
			pstmt.setString(4, libro.getAnioPublicacion());
			pstmt.setString(5, libro.getPortada());
			pstmt.setBoolean(6, libro.isDisponible());

			int resultado = pstmt.executeUpdate();
			return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Function to update an existing book.
	 * 
	 * @param con   Active database connection.
	 * @param libro LibroDO object with updated data.
	 * @return 1 if the book was updated, -1 on failure.
	 */
	public static int updateLibro(Connection con, LibroDO libro) {
		if (libro == null) {
			return -1;
		}
		try {
			String query = "UPDATE libro SET titulo = ?, genero = ?, autor = ?, anoPublicacion = ?, portada = ?, disponible = ? WHERE idLibro = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, libro.getTitulo());
			pstmt.setString(2, libro.getGenero());
			pstmt.setString(3, libro.getAutor());
			pstmt.setString(4, libro.getAnioPublicacion());
			pstmt.setString(5, libro.getPortada());
			pstmt.setBoolean(6, libro.isDisponible());
			pstmt.setInt(7, libro.getIdLibro()); // Set the ID for the WHERE clause

			int resultado = pstmt.executeUpdate();
			return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static ComboBox<String> cargarLibros(Connection con) {
		ComboBox<String> comboBoxLibros = new ComboBox<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT titulo FROM libro");

			while (rs.next()) {
				String titulo = rs.getString("titulo");
				comboBoxLibros.getItems().add(titulo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comboBoxLibros; // Devuelve el ComboBox, puede estar vacío
	}

	public static LibroDO getLibroPorTitulo(Connection con, String titulo) {
		try {
			String query = "SELECT * FROM libro WHERE titulo = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, titulo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				LibroDO libro = new LibroDO();
				libro.setIdLibro(rs.getInt("idLibro"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setGenero(rs.getString("genero"));
				libro.setAutor(rs.getString("autor"));
				libro.setAnioPublicacion(rs.getString("anoPublicacion"));
				libro.setPortada(rs.getString("portada"));
				libro.setDisponible(rs.getBoolean("disponible"));
				return libro;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int deleteLibroPorTitulo(Connection con, String titulo) {
		try {
			String query = "DELETE FROM libro WHERE titulo = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, titulo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}