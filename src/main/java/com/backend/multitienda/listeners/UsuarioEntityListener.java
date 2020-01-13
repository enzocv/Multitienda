package com.backend.multitienda.listeners;

import com.backend.multitienda.historiesLogs.UsuarioHistory;
import com.backend.multitienda.models.entity.Usuario;
import com.backend.multitienda.utils.BeanUtil;

import javax.persistence.*;
import javax.transaction.Transactional;

import static com.backend.multitienda.listeners.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class UsuarioEntityListener {

  @PostPersist
  public void postPersist(Usuario target) {
    perform(target, INSERTED);
  }

  @PreUpdate
  public void preUpdate(Usuario target) {
    perform(target, UPDATED);
  }

  @PreRemove
  public void preRemove(Usuario target) {
    perform(target, DELETED);
  }

  @Transactional(MANDATORY)
  void perform(Usuario target, Action action) {
    EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    entityManager.persist(new UsuarioHistory(target, action));
  }
}
