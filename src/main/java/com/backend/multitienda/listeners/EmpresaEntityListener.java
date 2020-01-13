package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.EmpresaHistory;
import com.backend.multitienda.models.entity.Empresa;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class EmpresaEntityListener {
  @PrePersist
  public void postPersist(Empresa target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(Empresa target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(Empresa target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(Empresa target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new EmpresaHistory(target, action));
  }
}
