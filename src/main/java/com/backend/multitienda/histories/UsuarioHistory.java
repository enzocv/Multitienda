package com.backend.multitienda.histories;

import com.backend.multitienda.listeners.Action;
import com.backend.multitienda.models.entity.Usuario;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UsuarioHistory {
  @Id
  @GeneratedValue
  private Integer id;

//  @ManyToOne
//  @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_usuario_history_usuario"))
//  private Usuario usuario;

  private String usuarioContent;

  @CreatedBy
  private String modifiedBy;

  @CreatedDate
  @Temporal(TIMESTAMP)
  private Date modifiedDate;

  @Enumerated(STRING)
  private Action action;

  public UsuarioHistory() {
  }

  public UsuarioHistory(Usuario usuario, Action action) {
    this.usuarioContent = usuario.toString();
    this.action = action;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

//  public Usuario getUsuario() {
//    return usuario;
//  }
//
//  public void setUsuario(Usuario usuario) {
//    this.usuario = usuario;
//  }

  public String getUsuarioContent() {
    return usuarioContent;
  }

  public void setUsuarioContent(String usuarioContent) {
    this.usuarioContent = usuarioContent;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }
}