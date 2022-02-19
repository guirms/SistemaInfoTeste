package com.teste.sistemainfoteste.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teste.sistemainfoteste.entidades.Usuario;
import com.teste.sistemainfoteste.servicos.UsuarioServico;

@Controller
public class UsuarioController {

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

	@GetMapping("/telaPrincipal")
	public String eventoForm() {
		return "telaPrincipal";
	}

	@RequestMapping(value = "/telaPrincipal", method = RequestMethod.POST)
	public String eventoForm(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("flashMessage", "Verifique os campos!");
			attributes.addFlashAttribute("flashType", "danger");
			return "redirect:/telaPrincipal";
		} else {
			usuarioServico.atualizar(1L, usuario);
			attributes.addFlashAttribute("flashMessage", "Evento adicionado com sucesso!");
			attributes.addFlashAttribute("flashType", "success");
			return "redirect:/telaPrincipal";
		}
	}
}
