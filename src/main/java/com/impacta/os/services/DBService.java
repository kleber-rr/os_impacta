package com.impacta.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.os.domain.Cliente;
import com.impacta.os.domain.OrdemServico;
import com.impacta.os.domain.Tecnico;
import com.impacta.os.domain.enums.Prioridade;
import com.impacta.os.domain.enums.Status;
import com.impacta.os.repositories.ClienteRepository;
import com.impacta.os.repositories.OrdemServicoRepository;
import com.impacta.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OrdemServicoRepository ordemServiceRepository;
		

	public void instanciaDB() {	
		
		Tecnico t1 = new Tecnico(null, "Osvalor Prosper", "114.700.130-88", "(95) 987126545");
		Tecnico t2 = new Tecnico(null, "Teste2", "008.275.032-70", "(95) 987126545");
		Tecnico t3 = new Tecnico(null, "TEste4", "008.275.042-41", "(95) 987126545");
		Cliente c1 = new Cliente(null, "Tiago Almeida" , "316.462.340-22", "(95) 981233741");
		OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Teste create Ordem de Serviço", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1,t2,t3));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemServiceRepository.saveAll(Arrays.asList(os1));


		
	}

}
