package com.uninorte.sophos.model;


public class Cliente {
	 
	private Integer user_id;
	private String identificacion;
	private String nombre;
	private String password;
	private String email;
	private String celular;
	private String fecha_nac;
	private boolean renta_vencida;
	
	public Cliente() {
		
	}

	public Cliente(Integer user_id, String identificacion, String nombre, String password, String email, String celular,
			String fecha_nac) {
		super();
		this.user_id = user_id;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.password = password;
		this.email = email;
		this.celular = celular;
		this.fecha_nac = fecha_nac;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public boolean isRenta_vencida() {
		return renta_vencida;
	}

	public void setRenta_vencida(boolean renta_vencida) {
		this.renta_vencida = renta_vencida;
	}

	@Override
	public String toString() {
		return "Cliente [user_id=" + user_id + ", identificacion=" + identificacion + ", nombre=" + nombre
				+ ", password=" + password + ", email=" + email + ", celular=" + celular + ", fecha_nac=" + fecha_nac
				+ ", renta_vencida=" + renta_vencida + "]";
	}
	
}
