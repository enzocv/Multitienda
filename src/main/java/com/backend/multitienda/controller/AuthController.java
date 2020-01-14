package com.backend.multitienda.controller;

import com.backend.multitienda.configs.JwtTokenUtil;
import com.backend.multitienda.configs.JwtUserDetailsService;
import com.backend.multitienda.models.entity.JwtRequest;
import com.backend.multitienda.models.entity.JwtResponse;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.repositories.IUsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;

@RestController
@CrossOrigin
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtToken;

  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  @Autowired
  private IUsuarioRepository usuarioRepository;

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    authenticate(authenticationRequest.getEmailUsuario(), authenticationRequest.getPassword());

    final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmailUsuario());

    final String token = jwtToken.generateToken(userDetails);
    Usuario user = new Usuario();

    if (token != null) {
      user = usuarioRepository.findByEmailUsuario(authenticationRequest.getEmailUsuario());
    }

    return ResponseEntity.ok(new JwtResponse(token, user));
  }

  private void authenticate(String username, String password) throws Exception {

    try {

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    } catch (DisabledException e) {

      throw new Exception("User deshabilitado", e);

    } catch (BadCredentialsException e) {

      throw new Exception("Usuario o contraseña incorrectos", e);

    }

  }


}
