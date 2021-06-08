package com.service.contain.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.contain.modelo.Actualizacion;
import com.service.contain.servicio.ActualizacionServicio;
import com.service.contain.utils.DataException;


@RestController
@RequestMapping("/api/actualizaciones")
public class ActualizacionRes {
	
	@Autowired
	private ActualizacionServicio actualizacionServicio;
	
	@PostMapping("/crear")
	private ResponseEntity<String> guardar(@RequestBody @Validated Actualizacion actualizacion, BindingResult result) {
		if (result.hasErrors()) {
			throw new DataException(result);
		} else {
			if (actualizacion.esFechaValida()) {
				try {
					Actualizacion actualizacionTem = actualizacionServicio.crearActualicacion(actualizacion);
					return ResponseEntity.ok("Actualizacion agregada " + actualizacionTem.getIdActualizacion());
				} catch (Exception e) {
					return ResponseEntity.ok("Actualizacion no agregada");
				}
			} else {
				return ResponseEntity.ok("La fecha debe ser menor o igual a la actual");
			}
		}
	}
	
	@GetMapping
	private ResponseEntity<List<Actualizacion>> listarActualizaciones() {
		return ResponseEntity.ok(actualizacionServicio.getActualicaciones());
	}

}
