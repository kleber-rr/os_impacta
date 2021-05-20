package com.impacta.os.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity(name="TB_PESSOA")
public abstract class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SQ_PESSOA")
	private Integer id;
	private String nome;
	@CPF
	private String cpf;
	private String telefone;

	public Pessoa() {
		super();
	}

	public Pessoa(Integer id, String nome, String cpf, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

}
