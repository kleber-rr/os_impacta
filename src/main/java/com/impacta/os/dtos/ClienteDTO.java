package com.impacta.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.impacta.os.domain.Cliente;

import lombok.Data;

@Data
public class ClienteDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O campo nome é obrigatório")
	private String nome;
	@CPF
	@NotEmpty(message = "O campo CPF é obrigatório")
	private String cpf;
	@NotEmpty(message = "O campo telefone é obrigatório")
	private String telefone;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	@JsonIgnore
	public Cliente getCliente() {
		return new Cliente(this.id, this.nome, this.cpf, this.telefone);
	}
}
