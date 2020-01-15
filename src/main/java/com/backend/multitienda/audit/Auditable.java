package com.backend.multitienda.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

  @CreatedBy
  @JsonIgnore
  protected U createdBy;

  @CreatedDate
  @JsonIgnore
  protected LocalDateTime createdDate;

  @LastModifiedBy
  @JsonIgnore
  protected U lastModifiedBy;

  @LastModifiedDate
  @JsonIgnore
  protected LocalDateTime lastModifiedDate;
}
