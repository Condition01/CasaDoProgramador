package br.com.casa.programador.services;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.programador.models.users.Inscrito;

public interface InscritoRepository extends JpaRepository<Inscrito, Integer>{
	
}
