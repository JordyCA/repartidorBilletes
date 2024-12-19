package com.cajita.repartidorBilletes.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cajita.repartidorBilletes.Entity.Denominacion;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/listaDenominacion")
	public Denominacion getListDenominacion() {
		Denominacion denominacion = new Denominacion();
		return denominacion;
	}
}
