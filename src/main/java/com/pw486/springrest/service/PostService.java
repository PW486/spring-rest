package com.pw486.springrest.service;

import com.pw486.springrest.entity.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostService {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Post> getAll() {
    return (List<Post>) entityManager.createQuery("from Post").getResultList();
  }

}
