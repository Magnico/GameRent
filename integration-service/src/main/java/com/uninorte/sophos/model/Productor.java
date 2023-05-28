package com.uninorte.sophos.model;

public class Productor {
	 
	private Integer productor_id;
	private String nombre;
	private String web;
	
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
		return "Productor [productor_id=" + productor_id + ", nombre=" + nombre + ", web=" + web +  "]";
	}
	
}
