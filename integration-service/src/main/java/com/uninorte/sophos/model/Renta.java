package com.uninorte.sophos.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Renta {
	
	private Integer renta_id;
	private String estado;
	private double costo;
	private String fecha_ven;
    private String creationDate;
    private Cliente cliente;
    private List<Juego> juegos;
	
	public Renta() {
		this.setInitialData();
	}

	public Renta(Integer rent_id, double costo, String fecha_ven, Cliente cliente, List<Juego> juegos) {
		super();
		this.renta_id = rent_id;
		this.costo = costo;
		this.fecha_ven = fecha_ven;
		this.cliente = cliente;
		this.juegos = juegos;
		this.setInitialData();
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
