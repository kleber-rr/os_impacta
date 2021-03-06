package com.impacta.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.os.domain.Pessoa;
import com.impacta.os.domain.Tecnico;
import com.impacta.os.dtos.TecnicoDTO;
import com.impacta.os.repositories.PessoaRepository;
import com.impacta.os.repositories.TecnicoRepository;
import com.impacta.os.services.exceptions.DataIntegratyViolationException;
import com.impacta.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> op = repository.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
	}
	
	public List<Tecnico> findAll(){
		List<Tecnico> list = repository.findAll();
		return list;
	}
	
	public Tecnico update(@Valid TecnicoDTO dto) {
		Tecnico oldObj = findById(dto.getId());
		if(findByCPF(dto) != null && findByCPF(dto).getId() != dto.getId())
			throw new DataIntegratyViolationException("CPF já cadastrado para outro usuario");
		
		oldObj.setNome(dto.getNome());
		oldObj.setCpf(dto.getCpf());
		oldObj.setTelefone(dto.getTelefone());
		return repository.save(oldObj);
	}
	
	public Tecnico save(TecnicoDTO dto) {
		if(findByCPF(dto) != null) 
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		return repository.save(dto.getTecnico());
	}
	
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj.isEmpty() || obj == null) {
			return null;
		}
		
		return obj.get();
	}

	public void delete(Integer id) {
		Tecnico oldObj = findById(id);
		if(oldObj == null) throw new ObjectNotFoundException("Técnico não encontrado");
		if(oldObj.getList().size() > 0) throw new DataIntegratyViolationException("Técnico possui ordens de serviço. Não pode ser deletado.");
		repository.delete(oldObj);
		return;
	}
	
	
	
}
