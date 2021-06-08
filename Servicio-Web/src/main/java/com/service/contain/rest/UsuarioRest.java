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

import com.service.contain.modelo.Usuario;
import com.service.contain.servicio.UsuarioServicio;
import com.service.contain.utils.DataException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRest {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@PostMapping("/crear")
	private ResponseEntity<String> guardar(@RequestBody @Validated Usuario usuario, BindingResult result) {
		if (usuario.esFechaValida()) {
			if (result.hasErrors()) {
				throw new DataException(result);
			} else {
				try {
					if (!(usuarioServicio.usuarioYaRegistrado(usuario.getUsuario()).size() > 0)) {
						Usuario usuarioTem = usuarioServicio.crearUsuario(usuario);
						return ResponseEntity.ok("{\"message\":\"Usuario creado " + usuarioTem.getIdUsuario() + "\"}");
					} else {
						throw new DataException(result);
					}

				} catch (Exception e) {
					throw new DataException(result);
				}
			}

		} else {
			throw new DataException(result);
		}
	}

	@PostMapping("/actualizar")
	private ResponseEntity<String> actualizar(@RequestBody @Validated Usuario usuario, BindingResult result) {
		if (usuario.esFechaValida()) {
			if (result.hasErrors()) {
				throw new DataException(result);
			} else {
				try {
					Usuario usuarioTem = usuarioServicio.crearUsuario(usuario);
					return ResponseEntity.ok("{\"message\":\"Usuario actualizado "+usuarioTem.getIdUsuario()+"\"}");
				}catch (Exception e){
					throw new DataException(result);
				}
			}
		} else {
			throw new DataException(result);
		}
	}

	@GetMapping
	private ResponseEntity<List<Usuario>> listarUsuario() {
		return ResponseEntity.ok(usuarioServicio.getUsuarios());
	}

	@GetMapping("/{id}")
	private ResponseEntity<Optional<Usuario>> listarUsuarioId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(usuarioServicio.buscarUsuarioPorId(id));
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id) {
		Usuario utemUsuario=new Usuario();
		utemUsuario.setIdUsuario(id);
		try {
			usuarioServicio.eliminarUsuario(utemUsuario);
			return ResponseEntity.ok("{\"message\":\"Usuario eliminado\"}");
		} catch (Exception e) {
			return ResponseEntity.ok("{\"message\":\"Usuario no eliminado\"}");
		}
	}

}
