package Fxapp.Practica3trimestre;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {
		BorderPane pContenedor = new BorderPane();

		// Configuración de la barra de menú
		MenuBar barraMenu = new MenuBar();

		Menu mArchivo = new Menu("Archivo");
		Menu mEdicion = new Menu("Edicion");
		Menu mAyuda = new Menu("Ayuda");

		// Items archivo
		MenuItem iNuevo = new MenuItem("Nuevo...");
		MenuItem iExportar = new MenuItem("Exportar...");
		MenuItem iCerrar = new MenuItem("Cerrar...");

		// Items edicion
		MenuItem iBuscar = new MenuItem("Buscar...");
		MenuItem iListar = new MenuItem("Listar...");

		// Items ayuda
		MenuItem iManualUS = new MenuItem("Manual de Uso...");

		// Configuración del TabPane
		TabPane tabPes = new TabPane();

		// Pestañas
		Tab tformlibro = new Tab("Nuevo Libro");
		Tab tmodBorr = new Tab("Modificar / Borrar");
		Tab tListado = new Tab("Listado");

		// Configurar que las pestañas no se puedan cerrar
		tformlibro.setClosable(false);
		tmodBorr.setClosable(false);
		tListado.setClosable(false);

		// Crear los paneles de contenido
		FormLibro pformLibro = new FormLibro();
		Listado panelListado = new Listado(); // Instanciar Listado aquí
		ModoEliminar pmodoEliminar = new ModoEliminar(pformLibro, tabPes, panelListado); // Pasar tabPes y panelListado

		// Configuración de acciones del menú
		iNuevo.setOnAction(e -> {
			tabPes.getSelectionModel().select(tformlibro); // Abre la pestaña "Nuevo Libro"
			pformLibro.limpiarFormulario(); // Limpia el formulario al ir a Nuevo Libro
			pformLibro.showSaveButton(); // Asegura que el botón "Guardar" esté visible
		});
		iExportar.setOnAction(e -> mostrarAlerta("Exportar", "Exportar datos", "Funcionalidad de exportación",
				Alert.AlertType.INFORMATION));
		iCerrar.setOnAction(e -> stage.close());

		iBuscar.setOnAction(e -> tabPes.getSelectionModel().select(tmodBorr)); // Abre la pestaña "Modificar / Borrar"
		iListar.setOnAction(e -> {
			panelListado.actualizarTabla(); // Actualiza la tabla al ir a la pestaña de listado
			tabPes.getSelectionModel().select(tListado); // Abre la pestaña "Listado"
		});

		iManualUS.setOnAction(e -> mostrarAlerta("Ayuda", "Manual de usuario",
				"1. Use la pestaña 'Nuevo Libro' para añadir nuevos libros\n"
						+ "2. Use 'Modificar/Borrar' para editar o eliminar libros existentes\n"
						+ "3. Use 'Listado' para ver todos los libros",
				Alert.AlertType.INFORMATION));

		// Añadir items a los menús
		mArchivo.getItems().addAll(iNuevo, iExportar, iCerrar);
		mEdicion.getItems().addAll(iBuscar, iListar);
		mAyuda.getItems().addAll(iManualUS);

		// Añadir menús a la barra de menú
		barraMenu.getMenus().addAll(mArchivo, mEdicion, mAyuda);

		// Configurar el contenido de las pestañas
		tformlibro.setContent(pformLibro);
		tmodBorr.setContent(pmodoEliminar);
		tListado.setContent(panelListado); // Descomentado y con la instancia correcta

		// Añadir pestañas al TabPane
		tabPes.getTabs().addAll(tformlibro, tmodBorr, tListado);

		// Configurar el layout principal
		pContenedor.setTop(barraMenu);
		pContenedor.setCenter(tabPes);

		// Configurar la escena y mostrar la ventana
		Scene scene = new Scene(pContenedor, 800, 600);
		stage.setScene(scene);
		stage.setTitle("Gestión de Libros");
		stage.show();
	}

	private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(encabezado);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch();
	}
}