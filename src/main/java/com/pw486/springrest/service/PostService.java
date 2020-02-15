package com.pw486.springrest.service;

import com.pw486.springrest.entity.Post;
import com.pw486.springrest.exception.ResourceNotFoundException;
import com.pw486.springrest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostService {

  @Autowired
  private PostRepository postRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public List<Post> findAll() {
    return entityManager.createQuery("from Post", Post.class).getResultList();
  }

  public Post findById(Long postId) {
    Post post;
    try {
      post = entityManager.createQuery("from Post where id = ?1", Post.class).setParameter(1, postId)
              .getSingleResult();
    } catch (Exception e) {
      throw new ResourceNotFoundException("Post", "id", postId);
    }

    return post;
  }

  public Post save(Post post) {
    return postRepository.save(post);
  }

  public void delete(Post post) {
    postRepository.delete(post);
  }

}
