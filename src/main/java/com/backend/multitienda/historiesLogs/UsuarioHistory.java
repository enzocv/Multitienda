package com.backend.multitienda.historiesLogs;

import com.backend.multitienda.listeners.Action;
import com.backend.multitienda.models.entity.Usuario;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UsuarioHistory {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(name = "usuario_content", nullable = false, columnDefinition = "TEXT")
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
}
