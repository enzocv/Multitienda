package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.ProveedorHistory;
import com.backend.multitienda.models.entity.Proveedor;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.*;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class ProveedorEntityListener {
  @PostPersist
  public void postPersist(Proveedor target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(Proveedor target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(Proveedor target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(Proveedor target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new ProveedorHistory(target, action));
  }
}
