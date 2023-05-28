package com.uninorte.sophos.model;


public class Director {
	 
	private Integer director_id;
	private String nombre;
	private String fecha_nac;
	private String nacionalidad;
	
	public Director() {
		
	}

	public Director(Integer director_id, String nombre, String fecha_nac, String nacionalidad) {
		super();
		this.director_id = director_id;
		this.nombre = nombre;
		this.fecha_nac = fecha_nac;
		this.nacionalidad = nacionalidad;
	}

	public Integer getDirector_id() {
		return director_id;
	}

	public void setDirector_id(Integer director_id) {
		this.director_id = director_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "Director [director_id=" + director_id + ", nombre=" + nombre + ", fecha_nac=" + fecha_nac
				+ ", nacionalidad=" + nacionalidad + "]";
	}
	

}
