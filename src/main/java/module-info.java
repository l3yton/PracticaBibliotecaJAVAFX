module Fxapp.Practica3trimestre {
	requires javafx.controls;
	requires javafx.graphics; // Necesario para Image y ImageView en Listado.java
	requires java.sql; // Necesario para la conexión a la base de datos
	requires io.github.cdimascio.dotenv.java; // Si estás utilizando la librería dotenv

	// Abre el paquete principal para que JavaFX FXML y otros módulos puedan acceder
	// a sus clases
	opens Fxapp.Practica3trimestre to javafx.fxml, javafx.graphics;

	// ¡Esta es la línea CRÍTICA para tu error!
	// Abre el paquete Fxapp.Model para que javafx.base (donde está
	// PropertyValueFactory)
	// pueda acceder a las propiedades de LibroDO usando reflexión.
	opens Fxapp.Model to javafx.base;
}