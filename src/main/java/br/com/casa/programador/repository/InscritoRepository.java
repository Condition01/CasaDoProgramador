	package br.com.casa.programador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.com.casa.programador.models.users.Inscrito;
import br.com.casa.programador.models.users.Pessoa;

public interface InscritoRepository extends JpaRepository<Inscrito, Integer>{
	@Query("Select i  from Inscrito i where i.email = :email ")
	public Inscrito findByEmail(@Param("email") String email);
}
