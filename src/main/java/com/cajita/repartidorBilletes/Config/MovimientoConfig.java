package com.cajita.repartidorBilletes.Config;

import org.springframework.data.repository.CrudRepository;

import com.cajita.repartidorBilletes.Entity.Movimiento;

public interface MovimientoConfig extends CrudRepository<Movimiento, Integer> {

}
