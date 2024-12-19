package com.cajita.repartidorBilletes.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="denominacion")
@Data
public class Denominacion {
	
	@Id
	private Integer id;
	private Integer tipo; /* 1.- Billete, 2.- Moneda */
	private Integer cantidad; 
	private Float denominacion;

}
