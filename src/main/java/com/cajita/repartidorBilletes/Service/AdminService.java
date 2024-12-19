package com.cajita.repartidorBilletes.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cajita.repartidorBilletes.Config.DenominacionConfig;
import com.cajita.repartidorBilletes.Config.MovimientoConfig;
import com.cajita.repartidorBilletes.Entity.Denominacion;
import com.cajita.repartidorBilletes.Entity.Movimiento;
import com.cajita.repartidorBilletes.Model.RespuestaEfectivoModel;
import com.cajita.repartidorBilletes.Utils.Constant;
import com.google.gson.Gson;

@Service
public class AdminService {

	@Autowired
	private DenominacionConfig denominacionConfig;

	@Autowired
	private MovimientoConfig movimientoConfig;

	public RespuestaEfectivoModel realizarRetiro(float cantidadRetiro) {

		RespuestaEfectivoModel datoRespuesta = new RespuestaEfectivoModel();
		List<String> detalleEfectivo = new ArrayList<>();

		List<Denominacion> movimientoAcumulado = new ArrayList<>();
		float valorAcumulado = 0;

		List<Denominacion> denominacionDb = getListDenominacion();
		List<Movimiento> movimientoDb = getListMovimiento();

		float sumaMovimiento = sumaMovimiento(movimientoDb);
		float sumaDenominacion = sumaDenominacion(denominacionDb);

		/*
		 * Validar que las entradas sea menor a la cantidad de entrada. Validar que las
		 * denominaciones totales sean menores a la cantidad de retiro
		 */
		if (cantidadRetiro > sumaMovimiento || cantidadRetiro > sumaDenominacion) {
			throw new NullPointerException(Constant.MESSAGE_WARNING);
		}

		for (Denominacion denominacion : denominacionDb) {
			if (cantidadRetiro != valorAcumulado) {
				int numeroDenominacion = (int) Math.min(
						(cantidadRetiro - valorAcumulado) / denominacion.getDenominacion(), denominacion.getCantidad());

				if (numeroDenominacion > 0) {
					Denominacion acumulableDenominacion = new Denominacion();
					acumulableDenominacion.setId(denominacion.getId());
					acumulableDenominacion.setCantidad(numeroDenominacion);
					acumulableDenominacion.setDenominacion(denominacion.getDenominacion());
					acumulableDenominacion.setTipo(denominacion.getTipo());
					movimientoAcumulado.add(acumulableDenominacion);
					valorAcumulado += numeroDenominacion * denominacion.getDenominacion();
				}
			}

		}

		if (cantidadRetiro != valorAcumulado) {
			throw new NullPointerException(Constant.MESSAGE_WARNING);
		}

		for (Denominacion movimiento : movimientoAcumulado) {
			String detalle;
			String tipo = movimiento.getTipo().equals(1) ? Constant.MESSAGE_BILLETE : Constant.MESSAGE_MONEDA;
			detalle = movimiento.getCantidad() + " " + tipo + " " + movimiento.getDenominacion();
			reducirCantidadDenominacion(movimiento);
			detalleEfectivo.add(detalle);
		}
		
		
		detalleEfectivo.add("Total " + valorAcumulado);

		agregarMovimiento(Constant.SALIDA, valorAcumulado);
		System.out.println("---->");
		System.out.println(new Gson().toJson(movimientoAcumulado));
		System.out.println(valorAcumulado);
		System.out.println("<---");
		
		datoRespuesta.setDetalle(detalleEfectivo);

		return datoRespuesta;
	}

	public List<Denominacion> getListDenominacion() {
		return (List<Denominacion>) denominacionConfig.findAll();
	}

	public List<Movimiento> getListMovimiento() {
		return (List<Movimiento>) movimientoConfig.findAll();
	}

	public void reducirCantidadDenominacion(Denominacion denominacionRestante) {
		Denominacion denominacionData = denominacionConfig.findById(denominacionRestante.getId()).get();
		denominacionData.setCantidad(denominacionData.getCantidad() - denominacionRestante.getCantidad());
		denominacionConfig.save(denominacionData);
	}

	public void agregarMovimiento(String tipo, float cantidad) {
		Integer id = Integer.valueOf((movimientoConfig.count() + 1) + "");
		Movimiento movimiento = new Movimiento();
		movimiento.setId(id);
		movimiento.setTipo(tipo);
		movimiento.setCantidad(cantidad);
		movimientoConfig.save(movimiento);
	}

	private float sumaMovimiento(List<Movimiento> listaMovimiento) {
		float movimientoAcumulable = 0;
		float movimientoSalida = 0;
		for (Movimiento movimiento : listaMovimiento) {
			if (movimiento.getTipo().equals(Constant.ENTRADA)) {
				movimientoAcumulable += movimiento.getCantidad();
			}
			if (movimiento.getTipo().equals(Constant.SALIDA)) {
				movimientoAcumulable += movimiento.getCantidad();
			}
		}
		return movimientoAcumulable - movimientoSalida;
	}

	private float sumaDenominacion(List<Denominacion> listaDenominacion) {
		float denominacionAcumulable = 0;

		for (Denominacion denominacion : listaDenominacion) {
			denominacionAcumulable = denominacion.getCantidad() * denominacion.getCantidad();
		}

		return denominacionAcumulable;

	}
}
