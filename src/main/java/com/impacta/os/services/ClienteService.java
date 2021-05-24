package com.impacta.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.os.domain.Pessoa;
import com.impacta.os.domain.Cliente;
import com.impacta.os.dtos.ClienteDTO;
import com.impacta.os.repositories.PessoaRepository;
import com.impacta.os.repositories.ClienteRepository;
import com.impacta.os.services.exceptions.DataIntegratyViolationException;
import com.impacta.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> op = repository.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
	}
	
	public List<Cliente> findAll(){
		List<Cliente> list = repository.findAll();
		return list;
	}
	
	public Cliente update(@Valid ClienteDTO dto) {
		Cliente oldObj = findById(dto.getId());
		if(findByCPF(dto) != null && findByCPF(dto).getId() != dto.getId())
			throw new DataIntegratyViolationException("CPF já cadastrado para outro usuario");
		
		oldObj.setNome(dto.getNome());
		oldObj.setCpf(dto.getCpf());
		oldObj.setTelefone(dto.getTelefone());
		return repository.save(oldObj);
	}
	
	public Cliente save(ClienteDTO dto) {
		if(findByCPF(dto) != null) 
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		return repository.save(dto.getCliente());
	}
	
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj.get();
		}
		return null;
	}

	public void delete(Integer id) {
		Cliente oldObj = findById(id);
		if(oldObj == null) throw new ObjectNotFoundException("Cliente não encontrado");
		if(oldObj.getList().size() > 0) throw new DataIntegratyViolationException("Cliente possui ordens de serviço. Não pode ser deletado.");
		repository.delete(oldObj);
		return;
	}
	
	
	
}
