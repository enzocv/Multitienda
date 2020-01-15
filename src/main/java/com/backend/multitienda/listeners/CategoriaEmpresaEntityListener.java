package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.CategoriaEmpresaHistory;
import com.backend.multitienda.models.entity.CategoriaEmpresa;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class CategoriaEmpresaEntityListener {
  @PostPersist
  public void postPersist(CategoriaEmpresa target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(CategoriaEmpresa target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(CategoriaEmpresa target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(CategoriaEmpresa target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new CategoriaEmpresaHistory(target, action));
  }
}
