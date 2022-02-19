package com.teste.sistemainfoteste.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	Integer validacao = 0;

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
	public String login(Usuario usuario, RedirectAttributes attributes) {
		String nomeUsuario = usuario.getUsuarioLogin();
		String senhaUsuario = usuario.getSenhaLogin();
		Usuario usuarioDB = usuarioServico.pegarPorId(1L);
		if (usuarioDB.getUsuarioLogin().equals(nomeUsuario) && usuarioDB.getSenhaLogin().equals(senhaUsuario)) {
			this.validacao = 1;
			return "redirect:/telaPrincipal";
		} else {
			attributes.addFlashAttribute("flashMessage", "Usuário ou login incorreto(s)!");
			attributes.addFlashAttribute("flashType", "danger");
			return "redirect:/login";
		}
	}

	@GetMapping("/telaPrincipal")
	public String telaPrincipal() {
		return validarEntrada("telaPrincipal");
		}
		
	@GetMapping("/cadastroUsuario")
	public String eventoForm() {
		return validarEntrada("cadastroUsuario");
	}

	@PostMapping("/cadastroUsuario")
	public String eventoForm(Usuario usuario, RedirectAttributes attributes) {
		if (usuario.getNome().equals("") && (usuario.getCpf().equals("") || verificarCpf(usuario))) {
			attributes.addFlashAttribute("flashMessage", "Verifique os campos 'nome' e 'cpf'!");
			attributes.addFlashAttribute("flashType", "danger");
		} else if (usuario.getNome().equals("")) {
			attributes.addFlashAttribute("flashMessage", "Verifique o campo 'nome'!");
			attributes.addFlashAttribute("flashType", "danger");
		} else if (usuario.getCpf().equals("") || verificarCpf(usuario)) {
			attributes.addFlashAttribute("flashMessage", "Verifique o campo 'cpf'!");
			attributes.addFlashAttribute("flashType", "danger");
		} else if (verificarCpf(usuario)) {
			attributes.addFlashAttribute("flashMessage", "Verifique o campo 'cpf'!");
			attributes.addFlashAttribute("flashType", "danger");
		} else {
			String codigo = usuario.gerarCodigo();
			usuarioServico.atualizar(1L, usuario);
			attributes.addFlashAttribute("flashMessage", "Pessoa cadastrada com sucesso, código: " + codigo);
			attributes.addFlashAttribute("flashType", "success");
			return "redirect:/telaPrincipal";
		}
		return "redirect:/cadastroUsuario";
	}

	private boolean verificarCpf(Usuario usuario) {
		int contador = 0;

		for (int i = 0; i < usuario.getCpf().length(); i++) {
			if (Character.isDigit(usuario.getCpf().charAt(i))) {
				contador += 1;
			}
		}

		if (contador != 11) {
			return true;
		} else {
			return false;
		}
	}
	
	private String validarEntrada(String endpoint) {
		if (this.validacao == 1) {
			return endpoint;
		}
		else {
			return "erro";
		}
	}
}
