package com.backend.multitienda.dto;

import lombok.Data;

@Data
public class UsuarioDto {

  private int idUsuario;

  private String emailUsuario;

  private String password;

  private String estado;

  private int idRol;

}
