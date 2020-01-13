package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.PermisoHistory;
import com.backend.multitienda.models.entity.Permiso;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.*;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class PermisoEntityListener {
  @PostPersist
  public void postPersist(Permiso target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(Permiso target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(Permiso target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(Permiso target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new PermisoHistory(target, action));
  }
}
