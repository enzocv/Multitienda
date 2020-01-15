package com.backend.multitienda.historiesLogs;

import com.backend.multitienda.listeners.Action;
import com.backend.multitienda.models.entity.Proveedor;
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
public class ProveedorHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "proveedor_content", nullable = false, columnDefinition = "TEXT")
  private String proveedorContent;

  @CreatedBy
  private String modifiedBy;

  @CreatedDate
  @Temporal(TIMESTAMP)
  private Date modifiedDate;

  @Enumerated(STRING)
  private Action action;

  public ProveedorHistory() {
  }

  public ProveedorHistory(Proveedor proveedor, Action action) {
    this.proveedorContent = proveedor.toString();
    this.action = action;
  }
}
