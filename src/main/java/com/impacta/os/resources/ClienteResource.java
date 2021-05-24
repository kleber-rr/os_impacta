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

import com.impacta.os.domain.Cliente;
import com.impacta.os.dtos.ClienteDTO;
import com.impacta.os.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<List<ClienteDTO>> delete(@PathVariable Integer id){
		service.delete(id);
		List<ClienteDTO> list = service.findAll().stream().map(
				obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		objDTO.setId(id);
		
		Cliente obj = service.update(objDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteDTO objDTO){
		Cliente obj = service.save(objDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(obj));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		Cliente obj = service.findById(id);
		return ResponseEntity.ok(new ClienteDTO(obj));
	}
	
	
	@GetMapping(value="/")
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<ClienteDTO> list = service.findAll().stream().map(
				obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	
}
