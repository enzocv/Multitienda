package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.SedeHistory;
import com.backend.multitienda.models.entity.Sede;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class SedeEntityListener {

  @PostPersist
  public void postPersist(Sede target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(Sede target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(Sede target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(Sede target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new SedeHistory(target, action));
  }
}
