package com.uninorte.sophos.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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
	@OneToMany(mappedBy = "cliente")
    private List<Renta> rentas;
	
	public Cliente() {
		
	}

	public Cliente(Integer user_id, String identificacion, String nombre, String password, String email, String celular,
			String fecha_nac, List<Renta> rentas) {
		super();
		this.user_id = user_id;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.password = password;
		this.email = email;
		this.celular = celular;
		this.fecha_nac = fecha_nac;
		this.rentas = rentas;
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

	public List<Renta> getRentas() {
		return rentas;
	}

	public void setRentas(List<Renta> rentas) {
		this.rentas = rentas;
	}

	@Override
	public String toString() {
		return "Cliente [user_id=" + user_id + ", identificacion=" + identificacion + ", nombre=" + nombre
				+ ", password=" + password + ", email=" + email + ", celular=" + celular + ", fecha_nac=" + fecha_nac
				+ ", rentas=" + rentas + "]";
	}
	
}
