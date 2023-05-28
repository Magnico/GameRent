package com.uninorte.sophos.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.uninorte.sophos.Juego;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;




@Entity
public class Renta {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer renta_id;
	@Column(nullable=false)
	private String estado;
	@Column(nullable=false)
	private double costo;
	@Column(nullable=false)
	private String fecha_ven;
    @Column(updatable = false,nullable=false)
    private String creationDate; 
	@ManyToOne
    @JoinColumn(name = "cliente_id",nullable=false)
    private Cliente cliente;
	@ManyToMany
    @JoinTable(name = "renta_juego",
            joinColumns = @JoinColumn(name = "renta_id"),
            inverseJoinColumns = @JoinColumn(name = "juego_id"))
    private List<Juego> juegos;
	
	public Renta() {
	}

	public Renta(Integer rent_id, double costo, String fecha_ven, Cliente cliente, List<Juego> juegos) {
		super();
		this.renta_id = rent_id;
		this.costo = costo;
		this.fecha_ven = fecha_ven;
		this.cliente = cliente;
		this.juegos = juegos;
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

	public List<Juego> getJuegos() {
		return juegos;
	}

	public void setJuego(List<Juego> juegos) {
		this.juegos = juegos;
	}
	
	public String getCreationDate() {
		return this.creationDate;
	}
	
	public <T> T getObject(Class<T> returnType) {
        if (returnType == Juego.class) {
            return returnType.cast(getJuegos());
        } else if (returnType == Cliente.class) {
            return returnType.cast(getCliente());
        } else {
            throw new IllegalArgumentException("Invalid class type");
        }
    }
	
	@PrePersist
	private void setInitialData() {
		this.creationDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.estado = "ACTIVA";
	}

	@Override
	public String toString() {
		return "Renta [rent_id=" + renta_id + ", estado=" + estado + ", costo=" + costo + ", fecha_ven=" + fecha_ven
				+ ", cliente=" + cliente + ", juego=" + juegos + "]";
	}
	
}
