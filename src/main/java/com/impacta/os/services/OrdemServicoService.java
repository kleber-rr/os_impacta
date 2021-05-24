package com.impacta.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.os.domain.Cliente;
import com.impacta.os.domain.OrdemServico;
import com.impacta.os.domain.Tecnico;
import com.impacta.os.domain.enums.Prioridade;
import com.impacta.os.domain.enums.Status;
import com.impacta.os.dtos.OrdemServicoDTO;
import com.impacta.os.repositories.OrdemServicoRepository;
import com.impacta.os.services.exceptions.DataIntegratyViolationException;
import com.impacta.os.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TecnicoService tecnicoService;

//	private OrdemServico fromDTO(OrdemServicoDTO obj) {
//		OrdemServicoDTO newObj = new OrdemServicoDTO();
//		newObj.setId(obj.getId());
//		newObj.setObservacoes(obj.getObservacoes());
//		newObj.setPrioridade(obj.getPrioridade());
//		newObj.setStatus(obj.getStatus());
//		
//		Tecnico t = tecnicoService.findById(obj.getTecnico());
//		Cliente c = clienteService.findById(obj.getCliente());
//		return newObj.getOrdemServico(t, c);
//		
//	}
	
	public OrdemServico update(OrdemServicoDTO dto) {
		OrdemServico oldOS = findById(dto.getId());
		if(dto.getTecnico() != null) oldOS.setTecnico(tecnicoService.findById(dto.getTecnico()));
		if(dto.getCliente() != null) oldOS.setCliente(clienteService.findById(dto.getCliente()));
		if(dto.getDataAbertura() != null) 
			throw new DataIntegratyViolationException("Não é possível alterar a data de abertura da OS");
		if(dto.getObservacoes() != null) oldOS.setObservacoes(dto.getObservacoes());
		if(dto.getPrioridade() != null) oldOS.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
		if(dto.getStatus() != null) oldOS.setStatus(Status.toEnum(dto.getStatus()));
		if(dto.getDataFechamento() != null) oldOS.setDataFechamento(dto.getDataFechamento());
			
		return repository.save(oldOS);
	}
	
	
	public OrdemServico findById(Integer id) {
		Optional<OrdemServico> op = repository.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<OrdemServico> findAll() {
		List<OrdemServico> list = repository.findAll();
		return list;
	}


	public OrdemServico save(OrdemServicoDTO dto) {
		Tecnico t = tecnicoService.findById(dto.getTecnico());
		Cliente c = clienteService.findById(dto.getCliente());
		return repository.save(dto.getOrdemServico(t, c));
	}

	public void delete(Integer id) {
		OrdemServico oldObj = findById(id);
		if (oldObj == null)
			throw new ObjectNotFoundException("Objeto não encontrado");
		repository.delete(oldObj);
		return;
	}

}
