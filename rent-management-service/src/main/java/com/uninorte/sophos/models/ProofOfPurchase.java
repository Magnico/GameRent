package com.uninorte.sophos.models;

import java.util.List;

import com.uninorte.sophos.Renta;

public class ProofOfPurchase {
	
	private Integer renta_id;
	private double costo;
	private String fecha_ven;
    private String creationDate;
    private String cliente_identificacion;
    private String cliente_nombre;
    private List<String> juegos;
    
    public ProofOfPurchase(Renta renta) {
    	this.renta_id = renta.getRent_id();
    	this.creationDate = renta.getCreationDate();
    	this.fecha_ven = renta.getFecha_ven();
    	this.cliente_identificacion = renta.getCliente().getIdentificacion();
    	this.cliente_nombre = renta.getCliente().getNombre();
    	for (Juego j : renta.getJuegos()) {
    		this.juegos.add("#"+j.getJuego_id()+" - "+j.getNombre()+" - "+j.getPlataforma());
    	}
    }
    
    public String toString() {
    	 StringBuilder sb = new StringBuilder();
         sb.append("Renta ID: ").append(renta_id).append("\n");
         sb.append("Costo: ").append(costo).append("\n");
         sb.append("Fecha de Vencimiento: ").append(fecha_ven).append("\n");
         sb.append("Creation Date: ").append(creationDate).append("\n");
         sb.append("Cliente Identificacion: ").append(cliente_identificacion).append("\n");
         sb.append("Cliente Nombre: ").append(cliente_nombre).append("\n");
         sb.append("Juegos:\n");
         for (String juego : juegos) {
             sb.append("\t").append(juego).append("\n");
         }
         return sb.toString();
    }

}
