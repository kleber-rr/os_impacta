package com.impacta.os.domain.enums;

import lombok.Getter;

@Getter
public enum Prioridade {
	BAIXA(0, "Baixa"), MEDIA(1, "Média"), ALTA(2, "Alta");

	private Integer cod;
	private String descricao;

	Prioridade(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static Prioridade toEnum(Integer cod) {
		if(cod == null) {
			return null;
		} 
		
		for(Prioridade p : Prioridade.values()) {
			if(cod.equals(p.getCod())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Prioridade não encontrada " + cod);
	}
}
