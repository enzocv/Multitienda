package com.backend.multitienda.models.entity;

public class JwtResponse {

  private final String token;
  private final Usuario user;
  private final String rol;
  private final String nombres;

  public JwtResponse(String token, Usuario user, String rol, String nombres) {
    this.token = token;
    this.user = user;
    this.rol = rol;
    this.nombres = nombres;
  }

  public String getToken() {
    return token;
  }

  public Usuario getUser() {
    return user;
  }

  public String getRol() {
    return rol;
  }

  public String getNombres() {
    return nombres;
  }
}
