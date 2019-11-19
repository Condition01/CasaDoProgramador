package br.com.casa.programador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.programador.models.users.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{}
