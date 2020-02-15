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
    return entityManager.createQuery("from Post", Post.class).getResultList();
  }

  @Override
  public Post findById(long id) {
    return entityManager.createQuery("from Post where id = ?1", Post.class).setParameter(1, id)
            .getSingleResult();
  }

}
