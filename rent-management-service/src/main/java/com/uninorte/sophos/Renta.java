package com.uninorte.sophos;

import com.uninorte.sophos.models.Cliente;
import com.uninorte.sophos.models.Juego;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Renta {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer renta_id;
	@Column
	private String estado;
	@Column
	private double costo;
	@Column
	private String fecha_ven;
	@ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "juego_id")
    private Juego juego;
	
	public Renta() {
		
	}

	public Renta(Integer rent_id, String estado, double costo, String fecha_ven, Cliente cliente, Juego juego) {
		super();
		this.renta_id = rent_id;
		this.estado = estado;
		this.costo = costo;
		this.fecha_ven = fecha_ven;
		this.cliente = cliente;
		this.juego = juego;
	}

	public Integer getRent_id() {
		return renta_id;
	}

	public void setRent_id(Integer rent_id) {
		this.renta_id = rent_id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getFecha_ven() {
		return fecha_ven;
	}

	public void setFecha_ven(String fecha_ven) {
		this.fecha_ven = fecha_ven;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	@Override
	public String toString() {
		return "Renta [rent_id=" + renta_id + ", estado=" + estado + ", costo=" + costo + ", fecha_ven=" + fecha_ven
				+ ", cliente=" + cliente + ", juego=" + juego + "]";
	}
	
}
