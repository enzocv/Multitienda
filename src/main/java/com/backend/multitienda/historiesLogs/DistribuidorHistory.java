package com.backend.multitienda.historiesLogs;

import com.backend.multitienda.listeners.Action;
import com.backend.multitienda.models.entity.Distribuidor;
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
public class DistribuidorHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "distribuidor_content", nullable = false, columnDefinition = "TEXT")
  private String distribuidorContent;

  @CreatedBy
  private String modifiedBy;

  @CreatedDate
  @Temporal(TIMESTAMP)
  private Date modifiedDate;

  @Enumerated(STRING)
  private Action action;

  public DistribuidorHistory() {
  }

  public DistribuidorHistory(Distribuidor distribuidor, Action action) {
    this.distribuidorContent = distribuidor.toString();
    this.action = action;
  }
}
