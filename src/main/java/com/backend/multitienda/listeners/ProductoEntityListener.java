package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.ProductoHistory;
import com.backend.multitienda.models.entity.Producto;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class ProductoEntityListener {
  @PostPersist
  public void postPersist(Producto target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(Producto target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(Producto target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(Producto target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new ProductoHistory(target, action));
  }
}
