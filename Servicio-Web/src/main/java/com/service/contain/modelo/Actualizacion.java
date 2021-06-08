package com.service.contain.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name="actualizaciones")
public class Actualizacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_actualizacion", unique = true,nullable = false)
	private Long idActualizacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actualizacion", nullable = false)
	private Date fechaActualizacion;

	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="id_producto_act", nullable = false)
	private Producto producto;

	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="id_usuario_act", nullable = false)
	private Usuario usuario;

	public Actualizacion() {
		super();
	}
	public Actualizacion(Long idActualizacion, Date fechaActualizacion, Producto producto, Usuario usuario) {
		super();
		this.idActualizacion = idActualizacion;
		this.fechaActualizacion = fechaActualizacion;
		this.producto = producto;
		this.usuario = usuario;
	}
	public Long getIdActualizacion() {
		return idActualizacion;
	}
	public void setIdActualizacion(Long idActualizacion) {
		this.idActualizacion = idActualizacion;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean esFechaValida() {
		Timestamp fechaIng=new Timestamp(this.fechaActualizacion.getTime());
		Timestamp ahora = new Timestamp(System.currentTimeMillis());
		return fechaIng.before(ahora)||fechaIng.equals(ahora);
	}
	private static final long serialVersionUID = 1L;
}
