package com.uninorte.sophos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {
	 
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer user_id;
	@Column
	private String identificacion;
	@Column
	private String nombre;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String celular;
	@Column
	private String fecha_nac;
	
	public Cliente() {
		
	}

	public Cliente(Integer user_id, String identificacion, String name, String password, String email,
			String celular, String fecha_nac) {
		super();
		this.user_id = user_id;
		this.identificacion = identificacion;
		this.nombre = name;
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

	public void setNombre(String name) {
		this.nombre = name;
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

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", identificacion=" + identificacion + ", nombre=" + nombre
				+ ", password=" + password + ", email=" + email + ", celular=" + celular + ", fecha_nac="
				+ fecha_nac + "]";
	}
}
