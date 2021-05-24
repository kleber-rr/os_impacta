package com.impacta.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impacta.os.domain.Cliente;
import com.impacta.os.domain.OrdemServico;
import com.impacta.os.domain.Tecnico;
import com.impacta.os.domain.enums.Prioridade;
import com.impacta.os.domain.enums.Status;

import lombok.Data;

@Data
public class OrdemServicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	
	@NotEmpty(message = "O campo observações é obrigatória")
	private String observacoes;
	private Integer status;

	private Integer tecnico;

	private Integer cliente;

	public OrdemServicoDTO() {
		super();
	}

	public OrdemServicoDTO(OrdemServico obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public OrdemServico getOrdemServico(Tecnico t, Cliente c) {
		return new OrdemServico(null, Prioridade.toEnum(this.prioridade),
			this.observacoes, Status.toEnum(this.status), t, c);
	}
	
}
