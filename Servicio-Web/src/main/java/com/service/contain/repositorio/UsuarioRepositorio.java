package com.service.contain.repositorio;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.service.contain.modelo.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>{
	public ArrayList<Usuario> findByUsuario(String usuario);
}
