package br.com.casa.programador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.programador.models.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer>{

}
