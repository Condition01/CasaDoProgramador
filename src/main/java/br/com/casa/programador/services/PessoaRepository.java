package br.com.casa.programador.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.casa.programador.models.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer>{
	
}
