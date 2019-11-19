package br.com.casa.programador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.casa.programador.models.Tema;
import br.com.casa.programador.models.users.Pessoa;

public interface TemaRepository extends JpaRepository<Tema, Integer>{
	@Query("SELECT t FROM Tema t where t.nome = :nome")
	public Tema findByNome(@Param("nome") String nome);
	
	@Query("SELECT t FROM Tema t")
	public List<Tema> findAllByTema();
}
