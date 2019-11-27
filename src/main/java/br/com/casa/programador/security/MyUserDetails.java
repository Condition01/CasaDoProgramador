package br.com.casa.programador.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.casa.programador.enums.StatusPessoa;
import br.com.casa.programador.models.users.Pessoa;

public class MyUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9103956968212149902L;

	String ROLE_PREFIX = "ROLE_";

	private String email;
	private String senha;
	private StatusPessoa status;
	private String role;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(Pessoa pessoa) {
		this.email = pessoa.getEmail();
		this.senha = pessoa.getSenha();
		this.status = pessoa.getStatus();
		this.role = pessoa.gettPessoa().toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
		System.out.println(ROLE_PREFIX + role);
		return list;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		if (status == StatusPessoa.ATIVADO) {
			return true;
		} else {
			return false;
		}
	}

}
