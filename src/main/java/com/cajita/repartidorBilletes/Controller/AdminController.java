package com.cajita.repartidorBilletes.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cajita.repartidorBilletes.Entity.Denominacion;
import com.cajita.repartidorBilletes.Model.RequestRetiroEfectivoModel;
import com.cajita.repartidorBilletes.Model.ResponseErrorModel;
import com.cajita.repartidorBilletes.Model.RespuestaEfectivoModel;
import com.cajita.repartidorBilletes.Service.AdminService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/listaDenominacion")
	public ResponseEntity<String>  getListDenominacion() {
		try {
			List<Denominacion> listaDenominacion = adminService.getListDenominacion();
			return new ResponseEntity<>(new Gson().toJson(listaDenominacion), HttpStatus.OK);
		} catch (Exception e) {
			ResponseErrorModel responseError = new ResponseErrorModel();
			responseError.setCodigo(500);
			responseError.setMessage(e.getMessage());
			return new ResponseEntity<>(new Gson().toJson(responseError), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/retiroEfectivo")
	public ResponseEntity<String>  retiroEfectivo(
			@RequestBody RequestRetiroEfectivoModel retiroEfectivo) {
		try {
			RespuestaEfectivoModel efectivo = adminService.realizarRetiro(retiroEfectivo.getCantidad());
			return new ResponseEntity<>(new Gson().toJson(efectivo), HttpStatus.OK);
		} catch (Exception e) {
			ResponseErrorModel responseError = new ResponseErrorModel();
			responseError.setCodigo(500);
			responseError.setMessage(e.getMessage());
			return new ResponseEntity<>(new Gson().toJson(responseError), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
