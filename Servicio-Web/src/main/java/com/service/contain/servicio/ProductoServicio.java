package com.service.contain.servicio;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.contain.modelo.Producto;
import com.service.contain.repositorio.ProductoRepositorio;

@Service
public class ProductoServicio {
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	public Producto crearProducto(Producto producto) {
		return productoRepositorio.save(producto);
	}
	
	public ArrayList<Producto> getProductos(){
		return (ArrayList<Producto>) productoRepositorio.findAll();
	}
	
	public void eliminarProducto(Producto producto) {
		productoRepositorio.delete(producto);
	}
	
	public Optional<Producto> buscarProductoPorId(Long  id) {
		return productoRepositorio.findById(id);
	}
	
	public ArrayList<Producto> buscarProducto(String  nombreProducto) {
		return productoRepositorio.findByNombreProductoContaining(nombreProducto);
	}
	
	public ArrayList<Producto> buscarProductoRegistrado(String  nombreProducto) {
		return productoRepositorio.findByNombreProducto(nombreProducto);
	}
	
}
