package br.com.casa.programador.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casa.programador.models.users.Publicador;

@Repository
public interface PublicadorRepository extends JpaRepository<Publicador, Integer>{
	
}
