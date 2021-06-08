package com.service.contain.servicio;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.contain.modelo.Cargo;
import com.service.contain.repositorio.CargoRepositorio;

@Service
public class CargoServicio {
	
	@Autowired
	private CargoRepositorio cargoRepositorio;
	
	public Cargo crearCargo(Cargo cargo) {
		return cargoRepositorio.save(cargo);
	}
	
	public ArrayList<Cargo> getCargos(){
		return (ArrayList<Cargo>) cargoRepositorio.findAll();
	}
	
	public void eliminarCargo(Cargo cargo) {
		cargoRepositorio.delete(cargo);
	}
	
	public Optional<Cargo> buscarCargoPorId(Long  id) {
		return cargoRepositorio.findById(id);
	}

}
