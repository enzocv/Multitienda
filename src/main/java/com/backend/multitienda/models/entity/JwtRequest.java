package com.backend.multitienda.models.entity;

public class JwtRequest {

  private String emailUsuario;
  private String password;

  public JwtRequest() {
  }

  public JwtRequest(String emailUsuario, String password) {
    this.emailUsuario = emailUsuario;
    this.password = password;
  }

  public String getEmailUsuario() {
    return emailUsuario;
  }

  public void setEmailUsuario(String emailUsuario) {
    this.emailUsuario = emailUsuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
