package br.com.casa.programador;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.casa.programador.enums.StatusPessoa;
import br.com.casa.programador.models.Publicador;
import br.com.casa.programador.services.PublicadorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class CT01InserirPessoa {

	@Autowired
	PublicadorRepository publicadorRepository;
	
	@Test
	public void insere() {
		Publicador p = new Publicador();
		p.setId(1);
		p.setNome("Bruno Cardoso");
		p.setDatanasc(new Date());
		p.setEmail("brunofc11@gmail.com");
		p.setnPublicacoes(15);
		p.setSexo("Homem");
		p.setStatus(StatusPessoa.ATIVADO);
		p.setCpf("456456465");
	}

}
