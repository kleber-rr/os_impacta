package com.impacta.os.domain.enums;

import lombok.Getter;

@Getter
public enum Status {
	ABERTO(0,"Aberto"),
	ANDAMENTO(1,"Andamento"),
	ENCERRADO(2,"Encerrado");

	private Integer cod;
	private String descricao;

	Status(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		} 
		
		for(Status p : Status.values()) {
			if(cod.equals(p.getCod())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Status não encontrado " + cod);
	}
}
