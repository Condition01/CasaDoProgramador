package br.com.casa.programador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.programador.models.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer>{}
