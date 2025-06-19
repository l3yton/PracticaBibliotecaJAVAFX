package Fxapp.AppBiblioteca;

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

public class AppBiblioteca extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // Configurar menús
        MenuBar menuBar = crearMenuBar();
        
        // Configurar pestañas
        TabPane tabPane = crearTabPane();
        
        root.setTop(menuBar);
        root.setCenter(tabPane);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de Biblioteca");
        primaryStage.show();
    }

    private MenuBar crearMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        Menu fileMenu = new Menu("Archivo");
        Menu editMenu = new Menu("Edición");
        Menu helpMenu = new Menu("Ayuda");
        
        // Items de menú Archivo
        MenuItem newItem = new MenuItem("Nuevo...");
        MenuItem exportItem = new MenuItem("Exportar...");
        MenuItem closeItem = new MenuItem("Cerrar...");
        
        // Items de menú Edición
        MenuItem searchItem = new MenuItem("Buscar...");
        MenuItem listItem = new MenuItem("Listar...");
        
        // Items de menú Ayuda
        MenuItem manualItem = new MenuItem("Manual de Uso...");
        
        // Agregar items a menús
        fileMenu.getItems().addAll(newItem, exportItem, closeItem);
        editMenu.getItems().addAll(searchItem, listItem);
        helpMenu.getItems().add(manualItem);
        
        // Agregar menús a la barra
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        
        // Configurar acciones
        configurarAccionesMenu(newItem, exportItem, closeItem, searchItem, listItem, manualItem);
        
        return menuBar;
    }

    private TabPane crearTabPane() {
        TabPane tabPane = new TabPane();
        
        PanelRegistroLibros formLibro = new PanelRegistroLibros();
        VistaCatalogoLibros listadoPanel = new VistaCatalogoLibros();
        PanelGestionLibros modoEliminar = new PanelGestionLibros(formLibro, tabPane, listadoPanel);
        
        Tab tabNew = crearTab("Nuevo Libro", formLibro, false);
        Tab tabEdit = crearTab("Modificar / Borrar", modoEliminar, false);
        Tab tabList = crearTab("Listado", listadoPanel, false);
        
        tabPane.getTabs().addAll(tabNew, tabEdit, tabList);
        
        return tabPane;
    }

    private Tab crearTab(String title, javafx.scene.Node content, boolean closable) {
        Tab tab = new Tab(title);
        tab.setContent(content);
        tab.setClosable(closable);
        return tab;
    }

    private void configurarAccionesMenu(MenuItem newItem, MenuItem exportItem, MenuItem closeItem,
                                      MenuItem searchItem, MenuItem listItem, MenuItem manualItem) {
        newItem.setOnAction(e -> {
            // Implementar acción para Nuevo
        });
        
        exportItem.setOnAction(e -> mostrarAlerta("Exportar", "Exportar datos", 
            "Funcionalidad de exportación", Alert.AlertType.INFORMATION));
        
        closeItem.setOnAction(e -> ((Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow()).close());
        
        searchItem.setOnAction(e -> {
            // Implementar acción para Buscar
        });
        
        listItem.setOnAction(e -> {
            // Implementar acción para Listar
        });
        
        manualItem.setOnAction(e -> mostrarAlerta("Ayuda", "Manual de usuario",
            "1. Use la pestaña 'Nuevo Libro' para añadir nuevos libros\n" +
            "2. Use 'Modificar/Borrar' para editar o eliminar libros existentes\n" +
            "3. Use 'Listado' para ver todos los libros", 
            Alert.AlertType.INFORMATION));
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}