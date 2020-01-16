package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.CategoriaProductoHistory;
import com.backend.multitienda.models.entity.CategoriaProducto;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class CategoriaProductoEntityListener {
  @PostPersist
  public void postPersist(CategoriaProducto target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(CategoriaProducto target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(CategoriaProducto target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(CategoriaProducto target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new CategoriaProductoHistory(target, action));
  }
}
