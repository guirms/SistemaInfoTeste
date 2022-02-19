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
	
	public Usuario inserir(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	public Usuario atualizar(Long id, Usuario usuario) {
			Usuario obj = pegarPorId(id);
			atualizarDados(obj, usuario);
			return usuarioRepo.save(obj);
	}

	private void atualizarDados(Usuario obj, Usuario usuario) {
		obj.setNome(usuario.getNome());
		obj.setCpf(usuario.getCpf());
		obj.setCodigo(usuario.gerarCodigo());
		obj.setEndereco(usuario.getEndereco());
		obj.setTelefone(usuario.getTelefone());
	}

}
