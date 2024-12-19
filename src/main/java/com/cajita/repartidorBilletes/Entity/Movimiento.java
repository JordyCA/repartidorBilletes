package com.cajita.repartidorBilletes.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="movimiento")
@Data
public class Movimiento {
	
	@Id
	private Integer id;
	private String tipo;
	private Float cantidad;
}
