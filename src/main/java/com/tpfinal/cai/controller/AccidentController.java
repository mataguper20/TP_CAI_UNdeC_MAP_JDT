package com.tpfinal.cai.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tpfinal.cai.dto.AccidentDTO;
import com.tpfinal.cai.dto.AccidentRequestDTO;
import com.tpfinal.cai.dto.PuntoPeligrosoDTO;
import com.tpfinal.cai.dto.FrecuenteDTO;
import com.tpfinal.cai.services.IAccidentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/accident")
public class AccidentController {

	@Inject
	private IAccidentService accidentsService;

	@GetMapping()
	public ResponseEntity<?> getAccidents() {
		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		result.addAll(this.getAccidentsService().getAccidents());
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAccidents(@PathVariable("id") String id) {
		ResponseEntity<?> response = null;
		AccidentDTO result = this.getAccidentsService().getOneById(id);
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/desde/{desde}/hasta/{hasta}")
	public ResponseEntity<?> accidentesEntreFechas(@PathVariable("desde") String desde, @PathVariable("hasta") String hasta) {
		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = this.getAccidentsService().accidentesEntreFechas(desde, hasta);
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/frecuentes/tipo/{datoTipo}/limite/{dateLimite}")
	public ResponseEntity<?> tiposFrecuentes(@PathVariable("datoTipo") String tipo, @PathVariable("dateLimite") int limite) {
		ResponseEntity<?> response = null;
		Collection<FrecuenteDTO> result = this.getAccidentsService().losMasFrecuentesPorTipo(tipo,limite);
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/porPuntoYRadio/lng/{lng}/lat/{lat}/radio/{radioKm}")
	public ResponseEntity<?> accidentesPorPuntoYRadio(@PathVariable("lng") float lng, @PathVariable("lat") float lat, @PathVariable("radioKm") float radioKm) {
		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = this.getAccidentsService().accidentesPorPuntoYRadio(lng, lat, radioKm);
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/puntosPeligrosos/{radioKm}")
	public ResponseEntity<?> puntosMasPeligrosos(@PathVariable("radioKm") float radioKm) {
		ResponseEntity<?> response = null;
		Collection<PuntoPeligrosoDTO> result = this.getAccidentsService().puntosMasPeligrososPorRadio(radioKm);
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/distanciaPromedio")
	public ResponseEntity<?> distanciaPromedio() {
		ResponseEntity<?> response = null;
		Float result = this.getAccidentsService().distanciaPromedio();
		response = ResponseEntity.ok(result);
		return response;
	}

	@PostMapping(value = "/api/accident")
	public ResponseEntity<?> saveAccidents(@RequestBody AccidentRequestDTO request) {
		ResponseEntity<?> response = null;
		this.getAccidentsService().saveAccident(request.getDescription());
		response = ResponseEntity.ok().build();
		return response;
	}

	public IAccidentService getAccidentsService() {
		return this.accidentsService;
	}

}
