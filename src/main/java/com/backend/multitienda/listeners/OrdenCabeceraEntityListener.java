package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.OrdenCabeceraHistory;
import com.backend.multitienda.models.entity.OrdenCabecera;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class OrdenCabeceraEntityListener {
  @PostPersist
  public void postPersist(OrdenCabecera target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(OrdenCabecera target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(OrdenCabecera target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(OrdenCabecera target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new OrdenCabeceraHistory(target, action));
  }
}
