package br.com.casa.programador.services;

import org.springframework.data.repository.CrudRepository;

import br.com.casa.programador.models.InscritoTema;
import br.com.casa.programador.models.Pessoa;

public interface InscritoTemaRepository extends CrudRepository<InscritoTema, Integer>{

}
