package br.com.casa.programador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.programador.models.Blocks;

public interface BlockRepository extends JpaRepository<Blocks, Integer>{}
