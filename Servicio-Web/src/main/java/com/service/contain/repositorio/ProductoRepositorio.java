package com.service.contain.repositorio;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.service.contain.modelo.Producto;

public interface ProductoRepositorio extends CrudRepository<Producto, Long>{
	public ArrayList<Producto> findByNombreProductoContaining(String nombreProducto);
	public ArrayList<Producto> findByNombreProducto(String nombreProducto);
}
