package com.service.contain.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table (name="productos")
public class Producto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true,nullable = false)
	private long idProducto;
	

	@Pattern(regexp = "[a-z A-Z0-9]+",message="El nombre del producto debe contener solo letras y números")
	@NotBlank(message="El nombre de usuario no puede estar vacío")
	@Column(name = "nombre_producto", unique = true,nullable = false,length = 400)
	private String nombreProducto;
	
	@Min(0)
	@Column(nullable = false)
	private int cantidad;
	
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="id_usuario_reg", nullable = false)
	private Usuario usuario;	
	
	
	public Producto() {
	}
	
	public Producto(long idProducto, String nombreProducto, int cantidad, Date fechaIngreso, Usuario usuario) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.usuario = usuario;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean esFechaValida() {
		Timestamp fechaIng=new Timestamp(this.fechaIngreso.getTime());
		Timestamp ahora = new Timestamp(System.currentTimeMillis());
		return fechaIng.before(ahora)||fechaIng.equals(ahora);
	}
	private static final long serialVersionUID = 1L;
}
