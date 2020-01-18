package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.StockProductoHistory;
import com.backend.multitienda.models.entity.StockProducto;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class StockProductoEntityListener {
  @PostPersist
  public void postPersist(StockProducto target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(StockProducto target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(StockProducto target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(StockProducto target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new StockProductoHistory(target, action));
  }
}
