package com.impacta.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.os.domain.Tecnico;
import com.impacta.os.dtos.TecnicoDTO;
import com.impacta.os.repositories.TecnicoRepository;
import com.impacta.os.services.exceptions.DataIntegratyViolationException;
import com.impacta.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> op = repository.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Tecnico> findAll(){
		List<Tecnico> list = repository.findAll();
		return list;
	}
	
	public Tecnico save(TecnicoDTO dto) {
		if(findByCPF(dto) != null) 
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		return repository.save(dto.getTecnico());
	}
	
	private Tecnico findByCPF(TecnicoDTO dto) {
		Optional<Tecnico> tecnico = repository.findByCpf(dto.getCpf());
		if(tecnico.isEmpty())
			return null;
		return tecnico.get();
	}
	
}
