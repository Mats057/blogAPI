package com.matheusqz.blogAPI.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusqz.blogAPI.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findByTitleContainingIgnoreCase(String title);
    List<Post> findByContentContainingIgnoreCase(String content);
    List<Post> findByCategoryContainingIgnoreCase(String category);

}
