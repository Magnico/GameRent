package com.uninorte.sophos.model;

import java.util.List;

public class Juego {
	 
	private Integer juego_id;
	private String nombre;
	private String fecha_pub;
	private String plataforma;
	private boolean rentado;
    private List<Personaje> personajes;
    private List<Productor> productores;
    private Director director;
	
	public Juego() {
		
	}

	public Juego(Integer juego_id, String nombre, String fecha_pub, String plataforma, List<Personaje> personajes,
			List<Productor> productores, Director director) {
		super();
		this.juego_id = juego_id;
		this.nombre = nombre;
		this.fecha_pub = fecha_pub;
		this.plataforma = plataforma;
		this.personajes = personajes;
		this.productores = productores;
		this.director = director;
	}

	public Integer getJuego_id() {
		return juego_id;
	}

	public void setJuego_id(Integer juego_id) {
		this.juego_id = juego_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha_pub() {
		return fecha_pub;
	}

	public void setFecha_pub(String fecha_pub) {
		this.fecha_pub = fecha_pub;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}

	public List<Productor> getProductores() {
		return productores;
	}

	public void setProductores(List<Productor> productores) {
		this.productores = productores;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public boolean isRentado() {
		return rentado;
	}

	public void setRentado(boolean rentado) {
		this.rentado = rentado;
	}

	@Override
	public String toString() {
		return "Juego [juego_id=" + juego_id + ", nombre=" + nombre + ", fecha_pub=" + fecha_pub + ", plataforma="
				+ plataforma + ", rentado=" + rentado + ", personajes=" + personajes + ", productores=" + productores
				+ ", director=" + director + "]";
	}
}
