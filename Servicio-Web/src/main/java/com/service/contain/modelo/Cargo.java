package com.service.contain.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table (name="cargos")
public class Cargo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cargo",unique = true,nullable = false)	
	private Long idCargo;
	

	@Pattern(regexp = "[a-z A-Z]+",message="El nombre del cargo debe contener solo letras")
	@Column(name = "nombre_cargo",unique = true,nullable = false,length = 50)	
	private String nombreCargo;
	
	public Cargo() {
		
	}
	
	public Cargo(Long idCargo, String nombreCargo) {
		this.idCargo = idCargo;
		this.nombreCargo = nombreCargo;
	}	

	
	public Long getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}
	public String getNombreCargo() {
		return nombreCargo;
	}
	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}	
	private static final long serialVersionUID = 1L;
}
