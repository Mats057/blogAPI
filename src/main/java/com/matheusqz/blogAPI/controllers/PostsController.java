package com.matheusqz.blogAPI.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusqz.blogAPI.models.Post;
import com.matheusqz.blogAPI.models.RequestPost;
import com.matheusqz.blogAPI.repositories.PostRepository;
import com.matheusqz.blogAPI.services.PostService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping()
    public ResponseEntity<List<Post>> getPosts(@RequestParam(required = false) String term) {
        try {
            if (term != null && !term.isEmpty()) {
                return ResponseEntity.ok(postService.search(term));
            }
            return ResponseEntity.ok(postRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Integer id) {
        try {
            return postRepository.findById(id)
                    .map(post -> ResponseEntity.ok(post))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> createPost(@RequestBody @Validated RequestPost requestPost) {
        try {
            Post post = postService.convert(requestPost);
            postRepository.save(post);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(post.getId()).toUri();
            return ResponseEntity.created(location).body(post);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody RequestPost requestPost) {
        try {
            
            return postRepository.findById(id)
                    .map(post -> {
                        postService.update(post, requestPost);
                        return ResponseEntity.ok(post);
                    })
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Integer id) {
        try {
            return postRepository.findById(id)
            .map(post -> {
                postRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
