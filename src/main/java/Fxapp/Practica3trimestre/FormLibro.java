package Fxapp.Practica3trimestre;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FormLibro extends GridPane {
	public FormLibro() {
		// Ponemos el espaciado
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(10));

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

	}
}
