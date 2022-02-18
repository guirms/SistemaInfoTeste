package com.teste.sistemainfoteste.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.sistemainfoteste.entidades.Usuario;
import com.teste.sistemainfoteste.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	UsuarioRepositorio usuarioRepo;
	
	public Usuario pegarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepo.findById(id);
		return usuario.get();
	}	
	
}
