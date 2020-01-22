package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.EmpaqueHistory;
import com.backend.multitienda.models.entity.Empaque;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class EmpaqueEntityListener {
  @PostPersist
  public void postPersist(Empaque target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(Empaque target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(Empaque target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(Empaque target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new EmpaqueHistory(target, action));
  }
}
