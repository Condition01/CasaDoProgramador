package br.com.casa.programador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.programador.models.Data;

public interface DataRepository extends JpaRepository<Data, Integer>{}