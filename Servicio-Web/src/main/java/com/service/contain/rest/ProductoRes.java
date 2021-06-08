package com.service.contain.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.contain.modelo.Producto;
import com.service.contain.servicio.ProductoServicio;
import com.service.contain.utils.DataException;

@RestController
@RequestMapping("/api/productos")
public class ProductoRes {

	@Autowired
	private ProductoServicio productoServicio;

	@PostMapping("/crear")
	private ResponseEntity<String> guardar(@RequestBody @Validated Producto producto, BindingResult result) {
		if (result.hasErrors()) {
			throw new DataException(result);
		} else {
			if (producto.esFechaValida()) {
				if (!(productoServicio.buscarProductoRegistrado(producto.getNombreProducto()).size()>0)){
					try {
						Producto productoTem = productoServicio.crearProducto(producto);
						return ResponseEntity.ok("{\"message\":\"Producto creado "+productoTem.getIdProducto()+"\"}");
					} catch (Exception e) {
						return ResponseEntity.ok("{\"message\":\"Producto no creado\"}");
					}
				} else {
					return ResponseEntity.ok("{\"message\":\"Nombre de producto ya registrado\"}");
				}
				
			} else {
				return ResponseEntity.ok("{\"message\":\"La fecha debe ser menor o igual a la actual\"}");
			}
		}
	}

	@PostMapping("/actualizar")
	private ResponseEntity<String> actualizar(@RequestBody @Validated Producto producto, BindingResult result) {
		if (result.hasErrors()) {
			throw new DataException(result);
		} else {
			if (producto.esFechaValida()) {
				Producto productoTem = productoServicio.crearProducto(producto);
				try {
					return ResponseEntity.ok("{\"message\":\"Producto actualizado "+productoTem.getIdProducto()+"\"}");
				} catch (Exception e) {
					return ResponseEntity.ok("{\"message\":\"Producto no actualizado\"}");
				}
			} else {
				return ResponseEntity.ok("{\"message\":\"La fecha debe ser menor o igual a la actual\"}");
			}
		}
	}
	
	@GetMapping
	private ResponseEntity<List<Producto>> listarProductos() {
		return ResponseEntity.ok(productoServicio.getProductos());
	}

	@GetMapping("/nombre/{nombreProducto}")
	private ResponseEntity<List<Producto>> listarProductoNombre(@PathVariable("nombreProducto") String nombreProducto) {
		return ResponseEntity.ok(productoServicio.buscarProducto(nombreProducto));
	}	
	
	@GetMapping("/id/{id}")
	private ResponseEntity<Optional<Producto>> listarProductoId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(productoServicio.buscarProductoPorId(id));
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id) {
		Producto utemProducto=new Producto();
		utemProducto.setIdProducto(id);
			try {
				productoServicio.eliminarProducto(utemProducto);
				return ResponseEntity.ok("{\"message\":\"Producto eliminado\"}");
			} catch (Exception e) {
				return ResponseEntity.ok("{\"message\":\"Producto no eliminado\"}");
			}
			
	}


}
