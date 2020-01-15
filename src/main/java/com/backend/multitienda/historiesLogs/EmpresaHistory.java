package com.backend.multitienda.historiesLogs;

import com.backend.multitienda.listeners.Action;
import com.backend.multitienda.models.entity.Empresa;
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
public class EmpresaHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "empresa_content", nullable = false, columnDefinition = "TEXT")
  private String empresaContent;

  @CreatedBy
  private String modifiedBy;

  @CreatedDate
  @Temporal(TIMESTAMP)
  private Date modifiedDate;

  @Enumerated(STRING)
  private Action action;

  public EmpresaHistory() {
  }

  public EmpresaHistory(Empresa empresa, Action action) {
    this.empresaContent = empresa.toString();
    this.action = action;
  }
}
