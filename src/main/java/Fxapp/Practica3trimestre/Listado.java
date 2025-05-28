package Fxapp.Practica3trimestre;

import java.sql.Connection;

import Fxapp.Model.LibroDAO;
import Fxapp.Model.LibroDO;
import Fxapp.Model.UtilsBD;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Listado extends VBox {
	private TableView<LibroDO> tablaLibros;
	private ObservableList<LibroDO> librosData;

	public Listado() {
		tablaLibros = new TableView<>();
		librosData = FXCollections.observableArrayList();

		// Configurar columnas
		TableColumn<LibroDO, String> colTitulo = new TableColumn<>("Título");
		colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colTitulo.setPrefWidth(150); // Ajustar ancho de columna

		TableColumn<LibroDO, String> colGenero = new TableColumn<>("Género");
		colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colGenero.setPrefWidth(100);

		TableColumn<LibroDO, String> colAutor = new TableColumn<>("Autor");
		colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		colAutor.setPrefWidth(150);

		TableColumn<LibroDO, String> colAnio = new TableColumn<>("Año Publicación");
		colAnio.setCellValueFactory(new PropertyValueFactory<>("anioPublicacion"));
		colAnio.setPrefWidth(120);

		TableColumn<LibroDO, Boolean> colDisponible = new TableColumn<>("Disponible");
		colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
		colDisponible.setPrefWidth(80);

		TableColumn<LibroDO, ImageView> colPortada = new TableColumn<>("Portada");
		colPortada.setCellValueFactory(cellData -> {
			String rutaPortada = cellData.getValue().getPortada();
			if (rutaPortada != null && !rutaPortada.isEmpty()) {
				// Cargar la imagen desde la ruta del archivo
				// Se usa 'file:' para indicar que es una ruta de archivo local
				// Los parámetros son: URL, ancho, alto, preservar ratio, suavizado
				try {
					Image image = new Image("file:" + rutaPortada, 50, 50, true, true);
					ImageView imageView = new ImageView(image);
					return new ReadOnlyObjectWrapper<ImageView>(imageView); // Devolver un ReadOnlyObjectWrapper con el
																			// ImageView
				} catch (Exception e) {
					System.err.println(
							"Error al cargar la imagen de la portada: " + rutaPortada + " - " + e.getMessage());
					return null; // En caso de error al cargar la imagen
				}
			}
			return null; // Si no hay ruta de portada
		});
		colPortada.setPrefWidth(80);

		tablaLibros.getColumns().addAll(colTitulo, colGenero, colAutor, colAnio, colDisponible, colPortada);

		// Cargar datos inicialmente
		actualizarTabla();

		this.getChildren().add(tablaLibros);
	}

	// Método para actualizar la tabla con los datos más recientes
	public void actualizarTabla() {
		Connection con = UtilsBD.ConectarBD();
		if (con != null) {
			librosData.setAll(LibroDAO.getLibros(con)); // Cargar todos los libros en la lista observable
			tablaLibros.setItems(librosData);
			UtilsBD.DesconectarBD(con); // Desconectar la BD después de obtener los datos
		} else {
			System.err.println("No se pudo conectar a la base de datos para actualizar el listado.");
		}
	}
}