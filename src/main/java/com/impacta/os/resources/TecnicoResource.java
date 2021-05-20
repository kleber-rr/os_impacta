package com.impacta.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impacta.os.domain.Tecnico;
import com.impacta.os.dtos.TecnicoDTO;
import com.impacta.os.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> save(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico obj = service.save(objDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new TecnicoDTO(obj));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok(new TecnicoDTO(obj));
	}
	
	
	@GetMapping(value="/")
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<TecnicoDTO> list = service.findAll().stream().map(
				obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
}
