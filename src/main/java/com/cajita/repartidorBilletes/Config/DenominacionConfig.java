package com.cajita.repartidorBilletes.Config;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cajita.repartidorBilletes.Entity.Denominacion;

@Repository
public interface DenominacionConfig extends CrudRepository<Denominacion, Integer> {

}
