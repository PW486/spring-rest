package com.pw486.springrest.controller;

import com.pw486.springrest.dao.PostDaoImpl;
import com.pw486.springrest.entity.Post;
import com.pw486.springrest.exception.ResourceNotFoundException;
import com.pw486.springrest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

  @Autowired
  PostRepository postRepository;

  @PersistenceContext
  EntityManager entityManager;

  PostDaoImpl dao;

  @PostConstruct
  public void init() {
    dao = new PostDaoImpl(entityManager);
  }

  @GetMapping("/posts")
  public List<Post> getAllPost() {
//    return postRepository.findAll();
//    return dao.getAll();
    return postRepository.findAllOrderByTitle();
  }

  @GetMapping("/posts/{id}")
  public Post getPostById(@PathVariable("id") Long postId) {
    return dao.findById(postId);
  }

  @PostMapping("/posts")
  public Post createPost(@Valid @RequestBody Post post) {
    return postRepository.save(post);
  }

  @PutMapping("/posts/{id}")
  public Post updatePost(@PathVariable("id") Long postId, @Valid @RequestBody Post postDetail) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

    post.setTitle(postDetail.getTitle());
    post.setText(postDetail.getText());

    return postRepository.save(post);
  }

  @DeleteMapping("/posts/{id}")
  public ResponseEntity<?> deletePost(@PathVariable("id") Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

    postRepository.delete(post);
    return ResponseEntity.ok().build();
  }

}
