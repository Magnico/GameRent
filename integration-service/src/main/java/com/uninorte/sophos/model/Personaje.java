package com.uninorte.sophos.model;

public class Personaje {
	 
	private Integer personaje_id;
	private String nombre;
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
