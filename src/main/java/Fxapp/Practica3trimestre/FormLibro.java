package Fxapp.Practica3trimestre;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;

import Fxapp.Model.LibroDAO;
import Fxapp.Model.LibroDO;
import Fxapp.Model.UtilsBD;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FormLibro extends GridPane {
	// Declaración e inicialización de los campos en el mismo lugar
	private TextField tituloField = new TextField();
	private ComboBox<String> listadoComboBox = new ComboBox<>();
	private TextField autorTextField = new TextField();

	private DatePicker dpAnioPubl = new DatePicker(); // Nuevo control: DatePicker
	private CheckBox chxbDisponible = new CheckBox();
	private String rutaPortada;
	private Stage fileStage = new Stage();
	private Label rutaArchivolbl = new Label("");
	private Button savebtn;
	private Button modificarbtn;
	private int currentLibroId;
	private ProgressBar progressBar;

	public FormLibro() {
		// Configuración del layout
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(20));

		// Configuración del campo Título
		Label tituloLabel = new Label("Titulo");
		tituloField.setPromptText("Titulo del Libro");
		this.add(tituloLabel, 0, 1);
		this.add(tituloField, 1, 1);

		// Configuración del ComboBox de géneros
		Label listadoLabel = new Label("Listado de generos");
		listadoComboBox.getItems().addAll("Novela", "Ciencia Ficcion", "Historia", "Infantil", "Fantasia", "Thriller");
		listadoComboBox.setPromptText("Selecciona el genero del libro");
		this.add(listadoLabel, 0, 2);
		this.add(listadoComboBox, 1, 2);

		// Configuración del campo Autor
		Label autorLabel = new Label("Autor");
		autorTextField.setPromptText("Autor/a");
		this.add(autorLabel, 0, 3);
		this.add(autorTextField, 1, 3);

		// Configuración del DatePicker para año de publicación
		Label anioPublLabel = new Label("Fecha de Publicacion");
		dpAnioPubl.setPromptText("Selecciona la fecha");
		this.add(anioPublLabel, 0, 4);
		this.add(dpAnioPubl, 1, 4);

		// Configuración del CheckBox de disponibilidad
		Label disponibleLabel = new Label("Disponibilidad");
		this.add(disponibleLabel, 0, 5);
		this.add(chxbDisponible, 1, 5);

		// Configuración del FileChooser para la portada
		Button fileChooserBtn = new Button("Selecciona Archivo");
		fileChooserBtn.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif"),
					new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));
			File archivoSeleccionado = fileChooser.showOpenDialog(fileStage);

			if (archivoSeleccionado != null) {
				rutaPortada = archivoSeleccionado.getAbsolutePath();
				rutaArchivolbl.setText("Ruta del archivo: " + rutaPortada); // Update the label
			}
		});
		this.add(fileChooserBtn, 0, 6);
		this.add(rutaArchivolbl, 1, 6); // Add the label to the grid

		// Configuración de los botones de acción
		savebtn = new Button("Guardar");
		savebtn.setOnAction(e -> guardarLibro());
		this.add(savebtn, 0, 7);

		modificarbtn = new Button("Modificar");
		modificarbtn.setOnAction(e -> modificarLibro());
		this.add(modificarbtn, 1, 7);
		modificarbtn.setVisible(false); // Initially hide the modify button

		// ProgressBar
		progressBar = new ProgressBar(0);
		progressBar.setPrefWidth(200);
		this.add(progressBar, 0, 8, 2, 1); // Span across two columns
		progressBar.setVisible(false); // Initially hidden
	}

	private void guardarLibro() {
		// Validación de campos obligatorios
		if (tituloField.getText() == null || tituloField.getText().isEmpty()) {
			mostrarAlerta("Error", "Campo obligatorio", "El título es obligatorio", Alert.AlertType.ERROR);
			return;
		}

		if (listadoComboBox.getValue() == null) {
			mostrarAlerta("Error", "Campo obligatorio", "Debe seleccionar un género", Alert.AlertType.ERROR);
			return;
		}

		if (dpAnioPubl.getValue() == null) {
			mostrarAlerta("Error", "Campo obligatorio", "Debe seleccionar una fecha de publicación",
					Alert.AlertType.ERROR);
			return;
		}

		progressBar.setVisible(true);
		progressBar.setProgress(0.1);

		// Crear el objeto Libro con los datos del formulario
		LibroDO libro = new LibroDO();
		libro.setTitulo(tituloField.getText());
		libro.setGenero(listadoComboBox.getValue());
		libro.setAutor(autorTextField.getText());
		libro.setAnioPublicacion(dpAnioPubl.getValue().toString()); // Get date from DatePicker
		libro.setPortada(rutaPortada);
		libro.setDisponible(chxbDisponible.isSelected());

		progressBar.setProgress(0.5);

		// Guardar en la base de datos
		Connection con = UtilsBD.ConectarBD();
		if (con != null) {
			int resultado = LibroDAO.insertLibro(con, libro);
			if (resultado == 1) {
				mostrarAlerta("Éxito", "Libro guardado", "El libro se ha guardado correctamente",
						Alert.AlertType.INFORMATION);
				limpiarFormulario();
			} else {
				mostrarAlerta("Error", "Error al guardar", "No se pudo guardar el libro", Alert.AlertType.ERROR);
			}
			progressBar.setProgress(1.0);
			progressBar.setVisible(false);
		} else {
			mostrarAlerta("Error", "Error de conexión", "No se pudo conectar a la base de datos",
					Alert.AlertType.ERROR);
			progressBar.setVisible(false);
		}
	}

	private void modificarLibro() {
		if (tituloField.getText() == null || tituloField.getText().isEmpty()) {
			mostrarAlerta("Error", "Campo obligatorio", "El título es obligatorio", Alert.AlertType.ERROR);
			return;
		}

		if (listadoComboBox.getValue() == null) {
			mostrarAlerta("Error", "Campo obligatorio", "Debe seleccionar un género", Alert.AlertType.ERROR);
			return;
		}

		if (dpAnioPubl.getValue() == null) {
			mostrarAlerta("Error", "Campo obligatorio", "Debe seleccionar una fecha de publicación",
					Alert.AlertType.ERROR);
			return;
		}

		progressBar.setVisible(true);
		progressBar.setProgress(0.1);

		LibroDO libro = new LibroDO();
		libro.setIdLibro(currentLibroId); // Use the stored ID
		libro.setTitulo(tituloField.getText());
		libro.setGenero(listadoComboBox.getValue());
		libro.setAutor(autorTextField.getText());
		libro.setAnioPublicacion(dpAnioPubl.getValue().toString()); // Get date from DatePicker
		libro.setPortada(rutaPortada);
		libro.setDisponible(chxbDisponible.isSelected());

		progressBar.setProgress(0.5);

		Connection con = UtilsBD.ConectarBD();
		if (con != null) {
			int resultado = LibroDAO.updateLibro(con, libro); // Call update method
			if (resultado == 1) {
				mostrarAlerta("Éxito", "Libro modificado", "El libro se ha modificado correctamente",
						Alert.AlertType.INFORMATION);
				limpiarFormulario();
				showSaveButton(); // Show save button after modification
			} else {
				mostrarAlerta("Error", "Error al modificar", "No se pudo modificar el libro", Alert.AlertType.ERROR);
			}
			progressBar.setProgress(1.0);
			progressBar.setVisible(false);
		} else {
			mostrarAlerta("Error", "Error de conexión", "No se pudo conectar a la base de datos",
					Alert.AlertType.ERROR);
			progressBar.setVisible(false);
		}
	}

	public void cargarDatosLibro(LibroDO libro) {
		this.currentLibroId = libro.getIdLibro(); // Store the ID
		tituloField.setText(libro.getTitulo());
		listadoComboBox.setValue(libro.getGenero());
		autorTextField.setText(libro.getAutor());

		try {
			dpAnioPubl.setValue(LocalDate.parse(libro.getAnioPublicacion()));
		} catch (Exception e) {
			dpAnioPubl.setValue(null);
		}

		chxbDisponible.setSelected(libro.isDisponible());
		rutaPortada = libro.getPortada();
		rutaArchivolbl.setText("Ruta del archivo: " + (rutaPortada != null ? rutaPortada : "")); // Update label

		savebtn.setVisible(false);
		modificarbtn.setVisible(true);
	}

	public void limpiarFormulario() {
		tituloField.clear();
		listadoComboBox.getSelectionModel().clearSelection();
		autorTextField.clear();

		dpAnioPubl.setValue(null);
		chxbDisponible.setSelected(false);
		rutaPortada = null;
		rutaArchivolbl.setText("");
		modificarbtn.setVisible(false);
		savebtn.setVisible(true);
	}

	public void showSaveButton() {
		savebtn.setVisible(true);
		modificarbtn.setVisible(false);
	}

	private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(encabezado);
		alert.setContentText(contenido);
		alert.showAndWait();
	}
}