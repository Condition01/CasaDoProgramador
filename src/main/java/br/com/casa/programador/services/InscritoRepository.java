package br.com.casa.programador.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.com.casa.programador.models.users.Inscrito;

public interface InscritoRepository extends JpaRepository<Inscrito, Integer>{
	
}
