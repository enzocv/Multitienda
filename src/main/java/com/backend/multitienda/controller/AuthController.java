package com.backend.multitienda.controller;

import com.backend.multitienda.configs.JwtTokenUtil;
import com.backend.multitienda.configs.JwtUserDetailsService;
import com.backend.multitienda.models.entity.*;
import com.backend.multitienda.repositories.IDistribuidorRepository;
import com.backend.multitienda.repositories.IProveedorRepository;
import com.backend.multitienda.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

  @Autowired
  private IProveedorRepository proveedorRepository;


  @Autowired
  private IDistribuidorRepository distribuidorRepository;

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    authenticate(authenticationRequest.getEmailUsuario(), authenticationRequest.getPassword());

    final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmailUsuario());

    final String token = jwtToken.generateToken(userDetails);
    Usuario user = new Usuario();
    String rol = "";
    if (token != null) {
      user = usuarioRepository.findByEmailUsuario(authenticationRequest.getEmailUsuario());
      rol = user.getRol().getDescripcionRol();
    }

    return ResponseEntity.ok(new JwtResponse(token, user, rol, getData(user)));
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

  private String getData(Usuario usuario) {
    String nombre = "";

    switch (usuario.getRol().getIdRol()) {
      case 1:
        nombre = "Administrador";
        break;
      case 2:
        nombre = getDataProveedor(usuario);
        break;
      case 3:
        nombre = getDataDistribuidor(usuario);
    }

    return nombre;
  }

  private String getDataProveedor(Usuario usuario) {
    Proveedor proveedor = proveedorRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
    return proveedor.getNombreProveedor() + " " + proveedor.getApellidoProveedor();
  }

  private String getDataDistribuidor(Usuario usuario) {
    Distribuidor distribuidor = distribuidorRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
    return distribuidor.getNombreDistribuidor() + " " + distribuidor.getApellidoDistribuidor();
  }

}
