package Fxapp.Practica3trimestre;

import java.sql.Connection;

import Fxapp.Model.LibroDAO;
import Fxapp.Model.UtilsBD;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class ModoEliminar extends VBox { // Extiende VBox para poder usarlo como contenedor

	public ModoEliminar() {
		Connection con = UtilsBD.ConectarBD();

		// Crear y configurar el ComboBox
		ComboBox<String> comboBoxLibros = LibroDAO.cargarLibros(con);
		comboBoxLibros.setPromptText("Seleccione un libro");
		comboBoxLibros.setPrefWidth(200);

		// Agregar el ComboBox al layout
		this.getChildren().add(comboBoxLibros);
		this.setSpacing(10); // Espaciado entre elementos
	}
}