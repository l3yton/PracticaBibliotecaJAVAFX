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
		Menu mHerramientas = new Menu("Herramientas");
		Menu mAyuda = new Menu("Ayuda");
		Menu mZip = new Menu("Comprimir");

		MenuItem iNuevo = new MenuItem("Nuevo...");
		MenuItem iAbrir = new MenuItem("Abrir...");
		MenuItem iCerrar = new MenuItem("Cerrar...");
		MenuItem iSeleccionar = new MenuItem("Seleccionar  Archivos...");
		MenuItem iDestino = new MenuItem("Destino...");
		MenuItem iComprimir = new MenuItem("Comprimir...");
		MenuItem iAcercaDe = new MenuItem("Acerca de...");
		MenuItem iPulsame = new MenuItem("Atrevete si tienes...");
		MenuItem iSeleccionar2 = new MenuItem("Seleccionar  Archivos...");
		MenuItem iDestino2 = new MenuItem("Destino...");
		MenuItem iComprimir2 = new MenuItem("Comprimir...");

		/*
		 * // Añadimos los item a los mens mArchivo.getItems().addAll(iNuevo, iAbrir,
		 * mZip, iCerrar); mHerramientas.getItems().addAll(iSeleccionar, iDestino,
		 * iComprimir); mAyuda.getItems().addAll(iAcercaDe, iPulsame);
		 * mZip.getItems().addAll(iSeleccionar2, iDestino2, iComprimir2);
		 * 
		 * // Añadimos los menu al menuBar barraMenu.getMenus().addAll(mArchivo,
		 * mHerramientas, mAyuda); // Creamos el panel de las pestañas TabPane tabPes =
		 * new TabPane(); // Pestañas para el panel Tab tAnimales = new
		 * Tab("Mostrar Animales"); Tab tzip = new Tab("Comprimir"); Tab tSsh = new
		 * Tab("Ssh"); // Ponemos Closable a false para que no salga la x de cerrar
		 * pestaña tAnimales.setClosable(false); tzip.setClosable(false);
		 * tSsh.setClosable(false);
		 * 
		 * // Creamos el grid panel de animales AnimalPanel pAnima = new AnimalPanel();
		 * // Metemos el animal panel en un scroll ScrollPane scrollp = new
		 * ScrollPane(pAnima);
		 * 
		 * // Creamos el panel ssH SshPanel sshp = new SshPanel();
		 * tSsh.setContent(sshp);
		 * 
		 * ZipPanel zip = new ZipPanel(); tzip.setContent(zip);
		 * 
		 * // Añadimos el scrollpane a la pestaña tAnimales.setContent(scrollp);
		 * 
		 * // Añadimos las pestañas al panel pestañoso
		 * tabPes.getTabs().addAll(tAnimales, tzip, tSsh);
		 * 
		 * // Metemos el dentro del borderpane pContendedor el menu y el panel de
		 * epestaas // tapPane y taPes pContenedor.setTop(barraMenu);
		 * pContenedor.setCenter(tabPes);
		 * 
		 * Scene scene = new Scene(pContenedor, 800, 600); ventana.setScene(scene); //
		 * Mostramos la ventana ventana.show();
		 * 
		 * /************ EVENTOS
		 *******/
		/*
		 * iCerrar.setOnAction(e -> { ventana.close(); });
		 * 
		 * iAbrir
		 */
	}

	public static void main(String[] args) {
		launch();
	}

}