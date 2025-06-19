package Fxapp.AppBiblioteca;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;

import Fxapp.LibroBD.LibroDAO;
import Fxapp.LibroBD.LibroDO;
import Fxapp.LibroBD.UtilsBD;
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

public class PanelRegistroLibros extends GridPane {
    private TextField tituloField = new TextField();
    private ComboBox<String> generoCombo = new ComboBox<>();
    private TextField autorField = new TextField();
    private DatePicker fechaPublicacionPicker = new DatePicker();
    private CheckBox disponibleCheck = new CheckBox();
    private Label rutaLabel = new Label("");
    private Button saveButton = new Button("Guardar");
    private Button modifyButton = new Button("Modificar");
    private String rutaPortada;
    private int currentLibroId;
    private ProgressBar progressBar = new ProgressBar(0);

    public PanelRegistroLibros() {
        configurarInterfaz();
        configurarEventos();
    }

    private void configurarInterfaz() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);
        
        // Configurar componentes
        generoCombo.getItems().addAll("Novela", "Ciencia Ficcion", "Historia", "Infantil", "Fantasia", "Thriller");
        progressBar.setVisible(false);
        modifyButton.setVisible(false);
        
        // Agregar componentes al layout
        add(new Label("Título:"), 0, 0);
        add(tituloField, 1, 0);
        add(new Label("Género:"), 0, 1);
        add(generoCombo, 1, 1);
        add(new Label("Autor:"), 0, 2);
        add(autorField, 1, 2);
        add(new Label("Año Publicación:"), 0, 3);
        add(fechaPublicacionPicker, 1, 3);
        add(new Label("Disponible:"), 0, 4);
        add(disponibleCheck, 1, 4);
        
        Button fileButton = new Button("Seleccionar Portada");
        fileButton.setOnAction(e -> seleccionarPortada());
        add(fileButton, 0, 5);
        add(rutaLabel, 1, 5);
        
        add(saveButton, 0, 6);
        add(modifyButton, 1, 6);
        add(progressBar, 0, 7, 2, 1);
    }

    private void configurarEventos() {
        saveButton.setOnAction(e -> guardarLibro());
        modifyButton.setOnAction(e -> modificarLibro());
    }

    private void seleccionarPortada() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"),
            new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
        File archivo = fileChooser.showOpenDialog(new Stage());
        if (archivo != null) {
            rutaPortada = archivo.getAbsolutePath();
            rutaLabel.setText("Ruta: " + rutaPortada);
        }
    }

    private void guardarLibro() {
        if (!validarCampos()) return;
        
        progressBar.setVisible(true);
        progressBar.setProgress(0.3);
        
        LibroDO libro = crearLibroDesdeFormulario();
        
        Connection con = UtilsBD.ConectarBD();
        if (con != null) {
            int resultado = LibroDAO.insertLibro(con, libro);
            UtilsBD.DesconectarBD(con);
            
            if (resultado > 0) {
                mostrarAlerta("Éxito", "Libro guardado", "El libro se ha guardado correctamente", Alert.AlertType.INFORMATION);
                limpiarFormulario();
            } else {
                mostrarAlerta("Error", "Error al guardar", "No se pudo guardar el libro", Alert.AlertType.ERROR);
            }
        }
        progressBar.setProgress(1.0);
        progressBar.setVisible(false);
    }

    private void modificarLibro() {
        if (!validarCampos()) return;
        
        progressBar.setVisible(true);
        progressBar.setProgress(0.3);
        
        LibroDO libro = crearLibroDesdeFormulario();
        libro.setIdLibro(currentLibroId);
        
        Connection con = UtilsBD.ConectarBD();
        if (con != null) {
            int resultado = LibroDAO.updateLibro(con, libro);
            UtilsBD.DesconectarBD(con);
            
            if (resultado > 0) {
                mostrarAlerta("Éxito", "Libro modificado", "El libro se ha modificado correctamente", Alert.AlertType.INFORMATION);
                limpiarFormulario();
                mostrarBotonGuardar();
            } else {
                mostrarAlerta("Error", "Error al modificar", "No se pudo modificar el libro", Alert.AlertType.ERROR);
            }
        }
        progressBar.setProgress(1.0);
        progressBar.setVisible(false);
    }

    private boolean validarCampos() {
        if (tituloField.getText().isEmpty()) {
            mostrarAlerta("Error", "Campo obligatorio", "El título es obligatorio", Alert.AlertType.ERROR);
            return false;
        }
        if (generoCombo.getValue() == null) {
            mostrarAlerta("Error", "Campo obligatorio", "Debe seleccionar un género", Alert.AlertType.ERROR);
            return false;
        }
        if (fechaPublicacionPicker.getValue() == null) {
            mostrarAlerta("Error", "Campo obligatorio", "Debe seleccionar una fecha de publicación", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private LibroDO crearLibroDesdeFormulario() {
        LibroDO libro = new LibroDO();
        libro.setTitulo(tituloField.getText());
        libro.setGenero(generoCombo.getValue());
        libro.setAutor(autorField.getText());
        libro.setAnioPublicacion(fechaPublicacionPicker.getValue().toString());
        libro.setPortada(rutaPortada);
        libro.setDisponible(disponibleCheck.isSelected());
        return libro;
    }

    public void cargarDatosLibro(LibroDO libro) {
        currentLibroId = libro.getIdLibro();
        tituloField.setText(libro.getTitulo());
        generoCombo.setValue(libro.getGenero());
        autorField.setText(libro.getAutor());
        try {
            fechaPublicacionPicker.setValue(LocalDate.parse(libro.getAnioPublicacion()));
        } catch (Exception e) {
            fechaPublicacionPicker.setValue(null);
        }
        disponibleCheck.setSelected(libro.isDisponible());
        rutaPortada = libro.getPortada();
        rutaLabel.setText("Ruta: " + (rutaPortada != null ? rutaPortada : ""));
        
        saveButton.setVisible(false);
        modifyButton.setVisible(true);
    }

    public void limpiarFormulario() {
        tituloField.clear();
        generoCombo.getSelectionModel().clearSelection();
        autorField.clear();
        fechaPublicacionPicker.setValue(null);
        disponibleCheck.setSelected(false);
        rutaPortada = null;
        rutaLabel.setText("");
        mostrarBotonGuardar();
    }

    public void mostrarBotonGuardar() {
        saveButton.setVisible(true);
        modifyButton.setVisible(false);
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}