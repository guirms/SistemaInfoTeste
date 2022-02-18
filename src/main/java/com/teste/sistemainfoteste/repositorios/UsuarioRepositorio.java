package com.teste.sistemainfoteste.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.sistemainfoteste.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}