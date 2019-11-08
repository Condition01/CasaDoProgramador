package br.com.casa.programador.services;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.programador.models.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer>{

}
