package com.service.contain.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.contain.modelo.Cargo;
import com.service.contain.servicio.CargoServicio;
import com.service.contain.utils.DataException;

@RestController
@RequestMapping("/api/cargos")
public class CargoRest {

	@Autowired
	private CargoServicio cargoServicio;

	@PostMapping("/crear")
	private ResponseEntity<String> guardar(@RequestBody @Validated Cargo cargo, BindingResult result) {
		if (result.hasErrors()) {
			throw new DataException(result);
		} else {
			try {
				Cargo cargoTem = cargoServicio.crearCargo(cargo);
				return ResponseEntity.ok("{\"message\":\"Cargo creado "+cargoTem.getIdCargo()+"\"}");
			} catch (Exception e) {
				return ResponseEntity.ok("{\"message\":\"Cargo no creado\"}");
			}
		}
	}
	
	@PostMapping("/actualizar")
	private ResponseEntity<String> actualizar(@RequestBody @Validated Cargo cargo, BindingResult result) {
		if (result.hasErrors()) {
			throw new DataException(result);
		} else {
			try {
				Cargo cargoTem = cargoServicio.crearCargo(cargo);
				return ResponseEntity.ok("{\"message\":\"Cargo actualizado "+cargoTem.getIdCargo()+"\"}");
			} catch (Exception e) {
				return ResponseEntity.ok("{\"message\":\"Cargo no actualizado\"}");
			}
		}
	}

	@GetMapping
	private ResponseEntity<List<Cargo>> listarCargos() {
		return ResponseEntity.ok(cargoServicio.getCargos());
	}

	@GetMapping("{id}")
	private ResponseEntity<Optional<Cargo>> listarCargosId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(cargoServicio.buscarCargoPorId(id));
	}


}
