package com.service.contain.modelo;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table (name="usuarios")
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true,nullable = false)
	private Long idUsuario;

	@Pattern(regexp = "[a-z A-Z0-9]+",message="El usuario debe contener solo letras y números")
	@NotBlank(message="El nombre de usuario no puede estar vacío")
	@Column(unique = true,nullable = false, length = 50)
	private String usuario;
	
	@Pattern(regexp = "[a-z A-Z]+",message="El nombre debe contener solo letras")
	@Column(length = 50)
	private String nombres;
	
	@Pattern(regexp = "[a-z A-Z]+",message="los apellidos deben contener solo letras")
	@Column(length = 50)
	private String apellidos;	
	
	@Min(0)
    @Max(200)
	private int edad;

	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="id_cargo", nullable = false)
	private Cargo cargo;

	public Usuario() {

	}
	
	public Usuario(Long idUsuario, String usuario, String nombres, String apellidos, int edad, Date fechaIngreso,
			Cargo cargo) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.fechaIngreso = fechaIngreso;
		this.cargo = cargo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public boolean esFechaValida() {
		Timestamp fechaIng=new Timestamp(this.fechaIngreso.getTime());
		Timestamp ahora = new Timestamp(System.currentTimeMillis());
		return fechaIng.before(ahora)||fechaIng.equals(ahora);
	}
	
	
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", edad=" + edad + ", fechaIngreso=" + fechaIngreso + ", cargo=" + cargo + "]";
	}



	private static final long serialVersionUID = 1L;
}
