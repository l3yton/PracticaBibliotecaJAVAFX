package Fxapp.LibroBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;

public class LibroDAO {

    // Método para obtener todos los libros
    public static ArrayList<LibroDO> getLibros(Connection con) {
        ArrayList<LibroDO> libros = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM libro");
            
            while (rs.next()) {
                LibroDO libro = crearLibroDesdeResultSet(rs);
                libros.add(libro);
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return libros;
    }

    // Método para eliminar libro por ID
    public static int deleteLibro(Connection con, int id) {
        try (PreparedStatement pstmt = con.prepareStatement(
                "DELETE FROM libro WHERE idLibro = ?")) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
            
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
            return -1;
        }
    }

    // Método para insertar nuevo libro
    public static int insertLibro(Connection con, LibroDO libro) {
        if (libro == null) return -1;
        
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO libro (titulo, genero, autor, anoPublicacion, portada, disponible) " +
                "VALUES (?, ?, ?, ?, ?, ?)")) {
            
            asignarValoresLibro(pstmt, libro);
            return pstmt.executeUpdate();
            
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
            return -1;
        }
    }

    // Método para actualizar libro existente
    public static int updateLibro(Connection con, LibroDO libro) {
        if (libro == null) return -1;
        
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE libro SET titulo = ?, genero = ?, autor = ?, anoPublicacion = ?, " +
                "portada = ?, disponible = ? WHERE idLibro = ?")) {
            
            asignarValoresLibro(pstmt, libro);
            pstmt.setInt(7, libro.getIdLibro());
            return pstmt.executeUpdate();
            
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
            return -1;
        }
    }

    // Método para cargar títulos en ComboBox
    public static ComboBox<String> cargarLibros(Connection con) {
        ComboBox<String> combo = new ComboBox<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT titulo FROM libro");
            
            while (rs.next()) {
                combo.getItems().add(rs.getString("titulo"));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return combo;
    }

    // Método para obtener libro por título
    public static LibroDO getLibroPorTitulo(Connection con, String titulo) {
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT * FROM libro WHERE titulo = ?")) {
            
            pstmt.setString(1, titulo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return crearLibroDesdeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return null;
    }

    // Método para eliminar libro por título
    public static int deleteLibroPorTitulo(Connection con, String titulo) {
        try (PreparedStatement pstmt = con.prepareStatement(
                "DELETE FROM libro WHERE titulo = ?")) {
            
            pstmt.setString(1, titulo);
            return pstmt.executeUpdate();
            
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
            return -1;
        }
    }

    // Métodos auxiliares
    private static LibroDO crearLibroDesdeResultSet(ResultSet rs) throws SQLException {
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

    private static void asignarValoresLibro(PreparedStatement pstmt, LibroDO libro) throws SQLException {
        pstmt.setString(1, libro.getTitulo());
        pstmt.setString(2, libro.getGenero());
        pstmt.setString(3, libro.getAutor());
        pstmt.setString(4, libro.getAnioPublicacion());
        pstmt.setString(5, libro.getPortada());
        pstmt.setBoolean(6, libro.isDisponible());
    }

    private static void manejarExcepcionSQL(SQLException e) {
        System.err.println("Error en operación con base de datos: " + e.getMessage());
        e.printStackTrace();
    }
}