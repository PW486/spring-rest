package com.pw486.springrest.controller;

import com.pw486.springrest.entity.Post;
import com.pw486.springrest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

  @Autowired
  PostService postService;

  @GetMapping("/posts")
  public List<Post> getAllPost() {
    return postService.findAll();
  }

  @GetMapping("/posts/{id}")
  public Post getPostById(@PathVariable("id") Long postId) {
    return postService.findById(postId);
  }

  @PostMapping("/posts")
  public Post createPost(@Valid @RequestBody Post post) {
    return postService.save(post);
  }

  @PutMapping("/posts/{id}")
  public Post updatePost(@PathVariable("id") Long postId, @Valid @RequestBody Post postData) {
    Post post = postService.findById(postId);
    post.setTitle(postData.getTitle());
    post.setText(postData.getText());

    return postService.save(post);
  }

  @DeleteMapping("/posts/{id}")
  public ResponseEntity<?> deletePost(@PathVariable("id") Long postId) {
    Post post = postService.findById(postId);
    postService.delete(post);

    return ResponseEntity.ok().build();
  }

}
