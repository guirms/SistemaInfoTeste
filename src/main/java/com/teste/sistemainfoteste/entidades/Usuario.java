package com.teste.sistemainfoteste.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private Long id;

	private String usuarioLogin;

	private String senhaLogin;

	private String nome;

	private String cpf;

	private String endereco;

	private String telefone;

	private String codigo;

	public Usuario(Long id, String usuarioLogin, String senhaLogin) {
		this.id = id;
		this.usuarioLogin = usuarioLogin;
		this.senhaLogin = senhaLogin;
	}

	public String gerarCodigo() {
		String[] vetor = new String[4];
		int contador = 0;

		for (int i = 0; contador < 4; i++) {
			if (Character.isDigit(cpf.charAt(i))) {
				vetor[contador] = String.valueOf(cpf.charAt(i));
				contador += 1;
			}
		}

		this.codigo = vetor[0];
		contador = 0;

		for (String dados : vetor) {
			if (contador > 0) {
				this.codigo += dados;
			}
			contador += 1;
		}

		return codigo;
	}

}