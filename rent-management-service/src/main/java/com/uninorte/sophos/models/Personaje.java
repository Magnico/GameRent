package com.uninorte.sophos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Personaje {
	 
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer personaje_id;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	
	public Personaje() {
		
	}

	public Personaje(Integer personaje_id, String nombre, String descripcion) {
		super();
		this.personaje_id = personaje_id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Integer getPersonaje_id() {
		return personaje_id;
	}

	public void setPersonaje_id(Integer personaje_id) {
		this.personaje_id = personaje_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Personaje [personaje_id=" + personaje_id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

	
	
}
