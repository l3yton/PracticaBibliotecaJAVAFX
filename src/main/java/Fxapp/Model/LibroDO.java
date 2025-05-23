package Fxapp.Model;

public class LibroDO {

	private int idLibro;
	private String titulo;
	private String genero;
	private String autor;
	private String anioPublicacion;
	private String portada;
	private boolean disponible;

	public LibroDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LibroDO(int idLibro, String titulo, String genero, String autor, String anioPublicacion, String portada,
			boolean disponible) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.genero = genero;
		this.autor = autor;
		this.anioPublicacion = anioPublicacion;
		this.portada = portada;
		this.disponible = disponible;

	}

	// Getters y setters
	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(String anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}