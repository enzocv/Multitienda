package com.backend.multitienda.models.entity;

public class JwtResponse {

  private final String token;
  private final Usuario user;

  public JwtResponse(String token, Usuario user) {
    this.token = token;
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public Usuario getUser() {
    return user;
  }
}
