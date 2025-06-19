package Fxapp.AppBiblioteca;

import java.sql.Connection;

import Fxapp.LibroBD.LibroDAO;
import Fxapp.LibroBD.LibroDO;
import Fxapp.LibroBD.UtilsBD;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class PanelGestionLibros extends VBox {
    private ComboBox<String> librosCombo = new ComboBox<>();
    private PanelRegistroLibros formLibro;
    private TabPane tabPane;
    private VistaCatalogoLibros listadoPanel;

    public PanelGestionLibros(PanelRegistroLibros formLibro, TabPane tabPane, VistaCatalogoLibros listadoPanel) {
        this.formLibro = formLibro;
        this.tabPane = tabPane;
        this.listadoPanel = listadoPanel;
        
        configurarUI();
        cargarLibros();
    }

    private void configurarUI() {
        setSpacing(10);
        setPadding(new javafx.geometry.Insets(10));
        
        Button editarBtn = new Button("Editar");
        Button eliminarBtn = new Button("Eliminar");
        
        editarBtn.setOnAction(e -> editarLibro());
        eliminarBtn.setOnAction(e -> eliminarLibro());
        
        getChildren().addAll(librosCombo, editarBtn, eliminarBtn);
    }

    private void cargarLibros() {
        Connection con = UtilsBD.ConectarBD();
        if (con != null) {
            librosCombo.setItems(LibroDAO.cargarLibros(con).getItems());
            UtilsBD.DesconectarBD(con);
        }
    }

    private void editarLibro() {
        String titulo = librosCombo.getValue();
        if (titulo == null) {
            mostrarAlerta("Error", "Selección requerida", "Seleccione un libro", Alert.AlertType.ERROR);
            return;
        }
        
        Connection con = UtilsBD.ConectarBD();
        if (con != null) {
            LibroDO libro = LibroDAO.getLibroPorTitulo(con, titulo);
            UtilsBD.DesconectarBD(con);
            
            if (libro != null) {
                formLibro.cargarDatosLibro(libro);
                tabPane.getSelectionModel().select(0); // Seleccionar pestaña de formulario
            }
        }
    }

    private void eliminarLibro() {
        String titulo = librosCombo.getValue();
        if (titulo == null) {
            mostrarAlerta("Error", "Selección requerida", "Seleccione un libro", Alert.AlertType.ERROR);
            return;
        }
        
        Connection con = UtilsBD.ConectarBD();
        if (con != null) {
            int resultado = LibroDAO.deleteLibroPorTitulo(con, titulo);
            UtilsBD.DesconectarBD(con);
            
            if (resultado > 0) {
                mostrarAlerta("Éxito", "Libro eliminado", "El libro se ha eliminado correctamente", Alert.AlertType.INFORMATION);
                librosCombo.getItems().remove(titulo);
                listadoPanel.actualizarTabla();
            } else {
                mostrarAlerta("Error", "Error al eliminar", "No se pudo eliminar el libro", Alert.AlertType.ERROR);
            }
        }
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}