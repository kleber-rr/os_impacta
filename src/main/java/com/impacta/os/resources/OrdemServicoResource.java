package com.impacta.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impacta.os.domain.OrdemServico;
import com.impacta.os.dtos.OrdemServicoDTO;
import com.impacta.os.services.OrdemServicoService;

@RestController
@RequestMapping(value = "/ordem-servicos")
public class OrdemServicoResource {

	@Autowired
	private OrdemServicoService service;
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<List<OrdemServicoDTO>> delete(@PathVariable Integer id){
		service.delete(id);
		List<OrdemServicoDTO> list = service.findAll().stream().map(
				obj -> new OrdemServicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> update(@Valid @RequestBody OrdemServicoDTO objDTO, @PathVariable Integer id){
		objDTO.setId(id);
		
		OrdemServico obj = service.update(objDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new OrdemServicoDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<OrdemServicoDTO> save(@Valid @RequestBody OrdemServicoDTO objDTO){
		OrdemServico obj = service.save(objDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new OrdemServicoDTO(obj));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer id){
		OrdemServico obj = service.findById(id);
		return ResponseEntity.ok(new OrdemServicoDTO(obj));
	}
	
	
	@GetMapping(value="/")
	public ResponseEntity<List<OrdemServicoDTO>> findAll(){
		List<OrdemServicoDTO> list = service.findAll().stream().map(
				obj -> new OrdemServicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	
}
