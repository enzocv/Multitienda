package com.backend.multitienda.historiesLogs;

import com.backend.multitienda.listeners.Action;
import com.backend.multitienda.models.entity.CategoriaEmpresa;
import com.backend.multitienda.models.entity.CategoriaProducto;
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
public class CategoriaProductoHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "categoria_producto_content", nullable = false, columnDefinition = "TEXT")
  private String categoriaProductoContent;

  @CreatedBy
  private String modifiedBy;

  @CreatedDate
  @Temporal(TIMESTAMP)
  private Date modifiedDate;

  @Enumerated(STRING)
  private Action action;

  public CategoriaProductoHistory() {
  }

  public CategoriaProductoHistory(CategoriaProducto producto, Action action) {
    this.categoriaProductoContent = producto.toString();
    this.action = action;
  }
}
