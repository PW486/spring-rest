package com.pw486.springrest.repository;

import com.pw486.springrest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  @Query("SELECT p FROM Post p ORDER BY p.title")
  List<Post> findAllOrderByTitle();

}
