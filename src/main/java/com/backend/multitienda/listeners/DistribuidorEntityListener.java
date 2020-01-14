package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.DistribuidorHistory;
import com.backend.multitienda.models.entity.Distribuidor;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.*;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class DistribuidorEntityListener {
  @PostPersist
  public void postPersist(Distribuidor target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(Distribuidor target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(Distribuidor target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(Distribuidor target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new DistribuidorHistory(target, action));
  }

}
