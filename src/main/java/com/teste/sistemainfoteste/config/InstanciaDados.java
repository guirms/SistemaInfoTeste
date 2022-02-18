package com.teste.sistemainfoteste.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.teste.sistemainfoteste.entidades.Usuario;
import com.teste.sistemainfoteste.repositorios.UsuarioRepositorio;

@Controller
public class InstanciaDados implements CommandLineRunner {

	@Autowired
	UsuarioRepositorio usuarioRepo;
	
	@Override
	public void run(String... args) throws Exception {

		Usuario usuarioInfo = new Usuario(null, "SISTEMA", "candidato123");
		usuarioRepo.saveAll(Arrays.asList(usuarioInfo));
		
	}

}
