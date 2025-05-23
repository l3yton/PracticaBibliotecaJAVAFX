package Fxapp.Practica3trimestre;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {
		MenuBar barraMenu = new MenuBar();

		Menu mArchivo = new Menu("Archivo");
		Menu mEdicion = new Menu("Edicion");
		Menu mAyuda = new Menu("Ayuda");
		Menu mZip = new Menu("Comprimir");

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

	}

	public static void main(String[] args) {
		launch();
	}

}