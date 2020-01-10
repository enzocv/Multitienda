package com.backend.multitienda.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
  public UsuarioNotFoundException(int id) {
    super("No se puede encontrar al usuario" + id);
  }
}
