package com.pw486.springrest.controller;

import com.pw486.springrest.entity.Post;
import com.pw486.springrest.exception.ResourceNotFoundException;
import com.pw486.springrest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

  @Autowired
  PostRepository postRepository;

  @GetMapping("/posts")
  public List<Post> getAllPost() {
    return postRepository.findAll();
  }

  @GetMapping("/posts/{id}")
  public Post getPostById(@PathVariable("id") Long postId) {
    return postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
  }

  @PostMapping("/posts")
  public Post createPost(@Valid @RequestBody Post post) {
    return postRepository.save(post);
  }

  @PutMapping("/posts/{id}")
  public Post updatePost(@PathVariable(value = "id") Long postId,
                         @Valid @RequestBody Post postDetails) {

    Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

    post.setTitle(postDetails.getTitle());
    post.setText(postDetails.getText());

    return postRepository.save(post);
  }

  @DeleteMapping("/posts/{id}")
  public ResponseEntity<?> deletePost(@PathVariable("id") Long postId) {
    Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

    postRepository.delete(post);

    return ResponseEntity.ok().build();
  }

}
