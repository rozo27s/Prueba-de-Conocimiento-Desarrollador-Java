package com.service.contain.servicio;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.contain.modelo.Usuario;
import com.service.contain.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public Usuario crearUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return (ArrayList<Usuario>) usuarioRepositorio.findAll();
	}
	
	public void eliminarUsuario(Usuario usuario) {
		usuarioRepositorio.delete(usuario);
	}
	
	public Optional<Usuario> buscarUsuarioPorId(Long  id) {
		return usuarioRepositorio.findById(id);
	}
	
	public ArrayList<Usuario> usuarioYaRegistrado(String  usuario) {
		return usuarioRepositorio.findByUsuario(usuario);
	}

}
