package br.com.casa.programador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.casa.programador.models.users.Pessoa;
import br.com.casa.programador.models.users.Publicador;

@Repository
public interface PublicadorRepository extends JpaRepository<Publicador, Integer>{
	
	@Query("Select p from Publicador p where p.email = :email")
	public Publicador findByEmail(@Param("email") String email);
	
}
