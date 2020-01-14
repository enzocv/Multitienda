package com.backend.multitienda.responses;

public class JwtResponses {
  private final String token;

  public JwtResponses(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
