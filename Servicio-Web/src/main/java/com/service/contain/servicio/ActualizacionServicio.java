package com.service.contain.servicio;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.contain.modelo.Actualizacion;
import com.service.contain.repositorio.ActualizacionRepositorio;

@Service
public class ActualizacionServicio {
	
	@Autowired
	private ActualizacionRepositorio actualizacionRepositorio;
	
	public Actualizacion crearActualicacion(Actualizacion actualizacion) {
		return actualizacionRepositorio.save(actualizacion);
	}
	
	public ArrayList<Actualizacion> getActualicaciones(){
		return (ArrayList<Actualizacion>) actualizacionRepositorio.findAll();
	}
	
	public void eliminarActualicacion(Actualizacion actualizacion) {
		actualizacionRepositorio.delete(actualizacion);
	}
	
	public Optional<Actualizacion> buscarActualicacionPorId(Long  id) {
		return actualizacionRepositorio.findById(id);
	}

}
