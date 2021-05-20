package com.impacta.os.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.impacta.os.domain.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

	Optional<Tecnico> findByCpf(String cpf);

	@Query("Select obj FROM Tecnico obj WHERE obj.cpf = :cpf")
	Optional<Tecnico> findByCpfQuery(@Param("cpf") String cpf);

	@Query("Select obj FROM Tecnico obj WHERE obj.cpf = ?1")
	Optional<Tecnico> findByCpfInt(@Param("cpf") String cpf);
}
