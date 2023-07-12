package model;

import java.util.*;
import model.Enum.*;
import model.utils.InterfaceModel;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Usuario implements InterfaceModel {

	private int id;
	private String usuario;
	private String contrasenia;
	private double dineroDisponible;
	private double tiempoDisponible;

	private int posicionX;
	private int posicionY;

	private int costoTotal = 0;
	private ENUMTIPO preferencia = ENUMTIPO.SinDefinir;
	private List<Atraccion> atracciones = new ArrayList<>();

	boolean administrador = false;

	public void setAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	// Constructor por defecto
	public Usuario(String usuario, String contrasenia, double dineroDisponible, double tiempoDisponible, int posicionX,
			int posicionY) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.dineroDisponible = dineroDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	// CONSTRUCTOR SOLO USADO POR DAO
	public Usuario(int id, String usuario, String contrasenia, double dineroDisponible, double tiempoDisponible,
			int posicionX, int posicionY, int admin) {
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.dineroDisponible = dineroDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		if (admin > 0)
			this.administrador = true;

	}

// Guetters and Setters/////////////////////////////////////////////////////////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public double getDineroDisponible() {
		return dineroDisponible;
	}

	public void setDineroDisponible(double dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public boolean canAfford(Atraccion attraction) {
		return attraction.getCosto() <= this.dineroDisponible;
	}

	public boolean canAttend(Atraccion attraction) {
		return attraction.getDuracion() <= this.tiempoDisponible;
	}
	
	public boolean puedeComprarPromocion(Promocion promocion) {
		return promocion.getCosto() <= this.dineroDisponible;
	}

	public boolean tieneTiempo(Promocion promocion) {
		return promocion.getDuracion() <= this.tiempoDisponible;
	}

	public int getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}

	public ENUMTIPO getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(ENUMTIPO preferencia) {
		this.preferencia = preferencia;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

// Calculos de distancia. //////////////////////////////////////////////////////

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	@Override
	public int getPosicionX() {
		return posicionX;
	}

	@Override
	public int getPosicionY() {
		return posicionY;
	}

	@Override
	public double distance(InterfaceModel otro) {
		return sqrt(pow(this.posicionX - otro.getPosicionX(), 2) + pow(this.posicionY - otro.getPosicionY(), 2));
	}

// funciones convenientes //////////////////////////////////////////////////////

	public void agregarAtraccion(Atraccion atraccion) {
		this.atracciones.add(atraccion);
	}

	public void agregarAtracciones(List<Atraccion> atracciones) {
		for (Atraccion i : atracciones) {
			this.atracciones.add(i);
		}
	}

	public int isAdministradorInt() {
		if (this.administrador)
			return 1;
		return 0;
	}

	public boolean checkPassword(String contrasenia) {
		// this.password en realidad es el hash del password
		return (this.contrasenia.equals(contrasenia));
	}

	@SuppressWarnings("unused")
	private static String ingresarDatoStr() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	public void addToItinerary(Atraccion attraction) {
		this.dineroDisponible -= attraction.getCosto();
		this.tiempoDisponible -= attraction.getDuracion();
		// TODO agregar a su lista
	}

// Overrides and comparate /////////////////////////////////////////////////////	

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Usuario usuario = (Usuario) o;
		return id == usuario.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Usuario [DNI=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", dineroInicial="
				+ dineroDisponible + ", tiempoDisponible=" + tiempoDisponible + ", posicionX=" + posicionX
				+ ", posicionY=" + posicionY + ", costoTotal=" + costoTotal + ", tipoFavorito=" + preferencia
				+ ", atracciones=" + atracciones + "]";
	}

	public boolean isNull() {
		return false;
	}
}
