package Fxapp.AppBiblioteca;

import java.sql.Connection;

import Fxapp.LibroBD.LibroDAO;
import Fxapp.LibroBD.LibroDO;
import Fxapp.LibroBD.UtilsBD;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VistaCatalogoLibros extends VBox {
    private TableView<LibroDO> tabla = new TableView<>();
    private ObservableList<LibroDO> librosData = FXCollections.observableArrayList();

    public VistaCatalogoLibros() {
        configurarTabla();
        actualizarTabla();
        getChildren().add(tabla);
    }

    private void configurarTabla() {
        // Columnas
        TableColumn<LibroDO, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        
        TableColumn<LibroDO, String> colGenero = new TableColumn<>("Género");
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        
        TableColumn<LibroDO, String> colAutor = new TableColumn<>("Autor");
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        
        TableColumn<LibroDO, String> colAnio = new TableColumn<>("Año Publicación");
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anioPublicacion"));
        
        TableColumn<LibroDO, Boolean> colDisponible = new TableColumn<>("Disponible");
        colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        
        TableColumn<LibroDO, ImageView> colPortada = new TableColumn<>("Portada");
        colPortada.setCellValueFactory(cellData -> {
            String ruta = cellData.getValue().getPortada();
            if (ruta != null && !ruta.isEmpty()) {
                try {
                    Image img = new Image("file:" + ruta, 50, 50, true, true);
                    return new ReadOnlyObjectWrapper<>(new ImageView(img));
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        });
        
        // Añadir columnas a la tabla
        tabla.getColumns().addAll(colTitulo, colGenero, colAutor, colAnio, colDisponible, colPortada);
    }

    public void actualizarTabla() {
        Connection con = UtilsBD.ConectarBD();
        if (con != null) {
            librosData.setAll(LibroDAO.getLibros(con));
            tabla.setItems(librosData);
            UtilsBD.DesconectarBD(con);
        }
    }
}