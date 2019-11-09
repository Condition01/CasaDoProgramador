package br.com.casa.programador.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.casa.programador.models.users.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	@Query("SELECT p FROM Pessoa p WHERE p.email = :email and p.senha = :senha")
	public Pessoa findByEmailAndPassword(@Param("email") String email, @Param("senha") String senha);
}
