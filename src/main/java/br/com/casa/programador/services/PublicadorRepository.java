package br.com.casa.programador.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.casa.programador.models.Publicador;

@Repository
public interface PublicadorRepository extends CrudRepository<Publicador, Integer>{
	
}
