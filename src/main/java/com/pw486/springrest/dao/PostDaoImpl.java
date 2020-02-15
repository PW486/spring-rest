package com.pw486.springrest.dao;

import com.pw486.springrest.entity.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostDaoImpl implements PostDao<Post> {
  private static final long serialVersionUID = 1L;

  private EntityManager entityManager;

  public PostDaoImpl() {
    super();
  }

  public PostDaoImpl(EntityManager manager) {
    entityManager = manager;
  }

  @Override
  public List<Post> getAll() {
    return (List<Post>) entityManager.createQuery("from Post").getResultList();
  }

  @Override
  public Post findById(long id) {
    return (Post) entityManager.createQuery("from Post where id = ?1").setParameter(1, id).getSingleResult();
  }
}
