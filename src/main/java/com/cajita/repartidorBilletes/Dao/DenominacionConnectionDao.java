package com.cajita.repartidorBilletes.Dao;

import org.springframework.data.repository.CrudRepository;

import com.cajita.repartidorBilletes.Entity.Denominacion;

public interface DenominacionConnectionDao extends CrudRepository<Denominacion, Long> {

}
