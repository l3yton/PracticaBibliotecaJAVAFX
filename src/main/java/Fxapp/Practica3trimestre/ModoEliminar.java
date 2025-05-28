package Fxapp.Practica3trimestre;

import java.sql.Connection;

import Fxapp.Model.LibroDAO;
import Fxapp.Model.LibroDO;
import Fxapp.Model.UtilsBD;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class ModoEliminar extends VBox {
	private ComboBox<String> comboBoxLibros;
	private FormLibro formLibro;
	private TabPane tabPane; // Reference to the TabPane
	private Listado listadoPanel; // Reference to the Listado panel

	public ModoEliminar(FormLibro formLibro, TabPane tabPane, Listado listadoPanel) { // Constructor now accepts TabPane
																						// and Listado
		this.formLibro = formLibro;
		this.tabPane = tabPane; // Assign the TabPane
		this.listadoPanel = listadoPanel; // Assign the Listado panel

		cargarComboBoxLibros(); // Initial load of books

		comboBoxLibros.setPromptText("Seleccione un libro");
		comboBoxLibros.setPrefWidth(200);

		Button btnEditar = new Button("Editar");
		btnEditar.setOnAction(e -> editarLibro());

		Button btnEliminar = new Button("Eliminar");
		btnEliminar.setOnAction(e -> eliminarLibro());

		this.getChildren().addAll(comboBoxLibros, btnEditar, btnEliminar);
		this.setSpacing(10);
	}

	private void cargarComboBoxLibros() {
		Connection con = UtilsBD.ConectarBD();
		if (con != null) {
			comboBoxLibros = LibroDAO.cargarLibros(con);
			UtilsBD.DesconectarBD(con); // Disconnect after loading
		} else {
			mostrarAlerta("Error", "Error de conexión", "No se pudo conectar a la base de datos para cargar libros",
					Alert.AlertType.ERROR);
			comboBoxLibros = new ComboBox<>(); // Initialize an empty ComboBox
		}
	}

	private void editarLibro() {
		String tituloSeleccionado = comboBoxLibros.getValue();
		if (tituloSeleccionado == null) {
			mostrarAlerta("Error", "Ningún libro seleccionado", "Por favor, seleccione un libro para editar",
					Alert.AlertType.ERROR);
			return;
		}

		Connection con = UtilsBD.ConectarBD();
		LibroDO libro = LibroDAO.getLibroPorTitulo(con, tituloSeleccionado);
		UtilsBD.DesconectarBD(con); // Disconnect after fetching data

		if (libro != null) {
			formLibro.cargarDatosLibro(libro);
			// Switch to the "Nuevo Libro" tab
			for (Tab tab : tabPane.getTabs()) {
				if (tab.getText().equals("Nuevo Libro")) {
					tabPane.getSelectionModel().select(tab);
					break;
				}
			}
		} else {
			mostrarAlerta("Error", "Libro no encontrado", "No se pudo cargar el libro seleccionado",
					Alert.AlertType.ERROR);
		}
	}

	private void eliminarLibro() {
		String tituloSeleccionado = comboBoxLibros.getValue();
		if (tituloSeleccionado == null) {
			mostrarAlerta("Error", "Ningún libro seleccionado", "Por favor, seleccione un libro para eliminar",
					Alert.AlertType.ERROR);
			return;
		}

		Connection con = UtilsBD.ConectarBD();
		if (con != null) {
			int resultado = LibroDAO.deleteLibroPorTitulo(con, tituloSeleccionado);
			if (resultado == 1) {
				mostrarAlerta("Éxito", "Libro eliminado", "El libro se ha eliminado correctamente",
						Alert.AlertType.INFORMATION);
				comboBoxLibros.getItems().remove(tituloSeleccionado); // Remove from ComboBox
				comboBoxLibros.getSelectionModel().clearSelection(); // Clear selection
				listadoPanel.actualizarTabla(); // Actualizar la tabla en el panel de listado
			} else {
				mostrarAlerta("Error", "Error al eliminar", "No se pudo eliminar el libro", Alert.AlertType.ERROR);
			}
			UtilsBD.DesconectarBD(con); // Disconnect after deletion
		} else {
			mostrarAlerta("Error", "Error de conexión", "No se pudo conectar a la base de datos para eliminar el libro",
					Alert.AlertType.ERROR);
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