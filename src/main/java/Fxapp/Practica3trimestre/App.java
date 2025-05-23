package Fxapp.Practica3trimestre;

import javafx.application.Application;
import javafx.scene.Scene;
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

		// Añadimos los item a los mens
		mArchivo.getItems().addAll(iNuevo, iExportar, iCerrar);
		mEdicion.getItems().addAll(iBuscar, iListar);
		mAyuda.getItems().addAll(iManualUS);

		// Añadimos al menu bar los menu
		barraMenu.getMenus().addAll(mArchivo, mEdicion, mAyuda);

		TabPane tabPes = new TabPane();
		// Pestañas para el panel
		Tab tformlibro = new Tab("Nuevo Libro");
		Tab tmodBorr = new Tab("Modificar / Borrar ");
		Tab tListado = new Tab("Listado");

		tformlibro.setClosable(false);
		tmodBorr.setClosable(false);
		tListado.setClosable(false);

		FormLibro pformLibro = new FormLibro();

		tabPes.getTabs().addAll(tformlibro, tmodBorr, tListado);

		pContenedor.setTop(barraMenu);
		pContenedor.setCenter(tabPes);

		tformlibro.setContent(pformLibro);

		Scene scene = new Scene(pContenedor, 800, 600);
		stage.setScene(scene);
		// Mostramos la ventana
		stage.show();

	}

	public static void main(String[] args) {
		launch();
	}

}