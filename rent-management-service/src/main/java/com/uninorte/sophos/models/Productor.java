package com.uninorte.sophos.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Productor {
	 
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer productor_id;
	@Column
	private String nombre;
	@Column
	private String web;
	@ManyToMany(mappedBy = "productores")
    private List<Juego> juegos;
	
	public Productor() {
		
	}

	public Productor(Integer productor_id, String nombre, String web) {
		super();
		this.productor_id = productor_id;
		this.nombre = nombre;
		this.web = web;
	}

	public Integer getProductor_id() {
		return productor_id;
	}

	public void setProductor_id(Integer productor_id) {
		this.productor_id = productor_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Override
	public String toString() {
		return "Productor [productor_id=" + productor_id + ", nombre=" + nombre + ", web=" + web + "]";
	}
	
}
