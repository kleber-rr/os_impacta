package com.impacta.os.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.impacta.os.domain.enums.Prioridade;
import com.impacta.os.domain.enums.Status;

import lombok.Data;

@Entity(name="TB_ORDEM_SERVICO")
@Data
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SQ_ORDEM_SERVICO")
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	private String observacoes;
	private Integer status;
	
	@ManyToOne()
	@JoinColumn(name = "tecnico_id")
	@JsonIgnore
	private Tecnico tecnico;
	
	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	@JsonIgnore
	private Cliente cliente;

	

	public OrdemServico(Integer id, Prioridade prioridade,
			String observacoes, Status status, Tecnico tecnico, Cliente cliente) {
		super();
		this.id = id;
		this.setDataAbertura(LocalDateTime.now());
		this.prioridade = prioridade == null ? 0 : prioridade.getCod();
		this.observacoes = observacoes;
		this.status = status == null ? 0 : status.getCod();
		this.tecnico = tecnico;
		this.cliente = cliente;
	}
	
	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}
	
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getCod();
	}
	
	public Status getStatus() {
		return Status.toEnum(this.status);
	}
	
	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public OrdemServico() {
		super();
	}

}
