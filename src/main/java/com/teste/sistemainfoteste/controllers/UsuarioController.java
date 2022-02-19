package com.teste.sistemainfoteste.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teste.sistemainfoteste.entidades.Usuario;
import com.teste.sistemainfoteste.repositorios.UsuarioRepositorio;
import com.teste.sistemainfoteste.servicos.UsuarioServico;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioServico usuarioServico;

	@Autowired
	UsuarioRepositorio usuarioRepo;

	@GetMapping
	public Usuario usuario(Long id) {
		id = 1L;
		return usuarioServico.pegarPorId(id);
	}

	@PostMapping
	public void instanciarUsuario() {
		Usuario usuario = new Usuario(null, "SISTEMA", "candidato123");
		usuarioRepo.save(usuario);
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
	public String eventoForm(Usuario usuario, RedirectAttributes attributes) {
		if (usuario.getNome().equals("") && usuario.getCpf().equals("")) {
			attributes.addFlashAttribute("flashMessage", "Verifique os campos 'nome' e 'cpf'!");
			attributes.addFlashAttribute("flashType", "danger");
		}
		else if (usuario.getNome().equals("")) {
			attributes.addFlashAttribute("flashMessage", "Verifique o campo 'nome'!");
			attributes.addFlashAttribute("flashType", "danger");}
		else if (usuario.getCpf().equals("")) {
			attributes.addFlashAttribute("flashMessage", "Verifique o campo 'cpf'!");
			attributes.addFlashAttribute("flashType", "danger");
		} else {
			usuarioServico.atualizar(1L, usuario);
			attributes.addFlashAttribute("flashMessage", "Usuário adicionado com sucesso!");
			attributes.addFlashAttribute("flashType", "success");
		}
		return "redirect:/telaPrincipal";
	}
}
