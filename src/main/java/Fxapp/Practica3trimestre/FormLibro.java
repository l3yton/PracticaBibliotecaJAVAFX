package Fxapp.Practica3trimestre;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FormLibro extends GridPane {
	public FormLibro() {
		// Ponemos el espaciado
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(20));

		Label tituloLabel = new Label("Titulo");
		TextField tituloField = new TextField();
		tituloField.setPromptText("Titulo del Libro");
		this.add(tituloLabel, 0, 1);
		this.add(tituloField, 1, 1);

		Label listadoLabel = new Label("Listado de generos");
		ComboBox listadoComboBox = new ComboBox();
		listadoComboBox.getItems().addAll("Novela", "Ciencia Ficcion", "Historia", "Infantil");
		listadoComboBox.setPromptText("Selecciona el genero del libro");
		this.add(listadoLabel, 0, 2);
		this.add(listadoComboBox, 1, 2);

		Label autorLabel = new Label("Autor");
		TextField autorTextField = new TextField();
		autorTextField.setPromptText("Autor/a");
		this.add(autorLabel, 0, 3);
		this.add(autorTextField, 1, 3);

		Label anioPublLabel = new Label("Año de Publicacion");
		Slider sldAnioPubl = new Slider();
		sldAnioPubl.setShowTickLabels(true);
		sldAnioPubl
				.setOnMouseReleased(e -> anioPublLabel.setText("Año de Publicacion: " + (int) sldAnioPubl.getValue()));
		sldAnioPubl.setMin(1800);
		sldAnioPubl.setMax(2025);
		this.add(anioPublLabel, 0, 4);
		this.add(sldAnioPubl, 1, 4);

		Label disponibleLabel = new Label("Disponibilidad");
		CheckBox chxbDisponible = new CheckBox();
		this.add(disponibleLabel, 0, 5);
		this.add(chxbDisponible, 1, 5);

		Stage fileStage = new Stage();
		Button fileChooserBtn = new Button("Selecciona Archivo");

		fileChooserBtn.setOnAction(event -> {
			// Crear y configurar el FileChooser
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Todos los archivos", "*.*");
			fileChooser.getExtensionFilters().add(filter);

			// Abrir el gestor de archivos
			File archivoSeleccionado = fileChooser.showOpenDialog(fileStage);

			// Verifica si se ha seleccionado un archivo
			if (archivoSeleccionado != null) {
				// Copiar la ruta del archivo a un String
				String rutaArchivo = archivoSeleccionado.getAbsolutePath();
				Label rutaArchivolbl = new Label();
				rutaArchivolbl.setText("Ruta del archivo" + rutaArchivo);
				this.add(rutaArchivolbl, 3, 6);

			}
		});
		this.add(fileChooserBtn, 0, 6);

		Button savebtn = new Button("Guardar");
		this.add(savebtn, 0, 7);

		Button modificarbtn = new Button("Modificar");
		this.add(modificarbtn, 1, 7);

	}
}
