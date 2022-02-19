package com.teste.sistemainfoteste.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String usuarioLogin;

	private String senhaLogin;

	private String nome;

	private String cpf;

	private String endereco;

	private String telefone;

	public Usuario() {
	}

	public Usuario(Long id, String usuarioLogin, String senhaLogin) {
		this.id = id;
		this.usuarioLogin = usuarioLogin;
		this.senhaLogin = senhaLogin;
	}

	public Usuario(Long id, String usuarioLogin, String senhaLogin, String nome, String cpf, String endereco,
			String telefone) {
		this.id = id;
		this.usuarioLogin = usuarioLogin;
		this.senhaLogin = senhaLogin;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
	public String codigo() {
		String[] vetor = new String[4];
		int contador = 0;
		
		for (int i = 0; contador < 4; i++) {
			if (Character.isDigit(cpf.charAt(i))) {
				vetor[contador] = String.valueOf(cpf.charAt(i));
				contador += 1;
			}
		}
		
		return vetor[0] + vetor[1] + vetor[2] + vetor[3];
	}

}