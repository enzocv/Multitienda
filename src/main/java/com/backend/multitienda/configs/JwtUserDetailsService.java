package com.backend.multitienda.configs;

import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private IUsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuario: " + emailUsuario + " con encontrado");
    }
    return new org.springframework.security.core.userdetails.User(usuario.getEmailUsuario(), usuario.getPassword(),
      new ArrayList<>());
  }
}
