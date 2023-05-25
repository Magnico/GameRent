package com.uninorte.sophos.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Juego {
	 
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer juego_id;
	@Column
	private String nombre;
	@Column
	private String fecha_pub;
	@Column
	private String plataforma;
	
	@ManyToMany
    @JoinTable(
        name = "juego_personaje",
        joinColumns = @JoinColumn(name = "juego_id"),
        inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private List<Personaje> personajes;
	
	@ManyToMany
    @JoinTable(name = "juego_productor",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "productor_id"))
    private List<Productor> productores;
	
	@ManyToOne
    @JoinColumn(name = "director_id")
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

	@Override
	public String toString() {
		return "Juego [juego_id=" + juego_id + ", nombre=" + nombre + ", fecha_pub=" + fecha_pub + ", plataforma="
				+ plataforma + ", personajes=" + personajes + ", productores=" + productores + ", director=" + director
				+ "]";
	}
	
}
