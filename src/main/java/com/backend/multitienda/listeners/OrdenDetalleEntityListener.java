package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.OrdenDetalleHistory;
import com.backend.multitienda.models.entity.OrdenDetalle;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class OrdenDetalleEntityListener {
  @PostPersist
  public void postPersist(OrdenDetalle target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(OrdenDetalle target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(OrdenDetalle target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(OrdenDetalle target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new OrdenDetalleHistory(target, action));
  }
}
