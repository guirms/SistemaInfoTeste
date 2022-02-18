package com.teste.sistemainfoteste.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teste.sistemainfoteste.entidades.Usuario;
import com.teste.sistemainfoteste.servicos.UsuarioServico;

@Controller
public class UsuarioRecurso {

	@Autowired
	UsuarioServico usuarioServico;

	@GetMapping
	public Usuario usuario(Long id) {
		id = 1L;
		return usuarioServico.pegarPorId(id);
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(Usuario usuario) {
		String nomeUsuario = usuario.getUsuarioLogin();
		String senhaUsuario = usuario.getSenhaLogin();
		Usuario usuarioDB = usuarioServico.pegarPorId(1L);
		if (usuarioDB.getUsuarioLogin().equals(nomeUsuario) && usuarioDB.getSenhaLogin().equals(senhaUsuario)) {
			return "redirect:/telaPrincipal";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = "/telaPrincipal", method = RequestMethod.GET)
	public String eventoForm() {
		return "telaPrincipal";
	}

}
