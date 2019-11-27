package br.com.casa.programador.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.casa.programador.models.users.Pessoa;
import br.com.casa.programador.repository.PessoaRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private PessoaRepository pRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Pessoa pessoa = pRepository.findByEmail(username);
		if (pessoa == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		MyUserDetails myUser = new MyUserDetails(pessoa);
		return myUser;
	}
}
