package com.matheusqz.blogAPI.services;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusqz.blogAPI.models.Post;
import com.matheusqz.blogAPI.models.RequestPost;
import com.matheusqz.blogAPI.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostService() {

    }

    public Post convert(RequestPost requestPost) {
        Post post = new Post(requestPost);
        return post;
    }

    public Post update(Post post, RequestPost requestPost) {
        post.setTitle(requestPost.title());
        post.setCategory(requestPost.category());
        post.setContent(requestPost.content());
        post.setTags(requestPost.tags());
        postRepository.save(post);
        return post;
    }

    public List<Post> search(String term) {
        List<Post> postsByTitle = postRepository.findByTitleContainingIgnoreCase(term);
        List<Post> postsByCategory = postRepository.findByCategoryContainingIgnoreCase(term);
        List<Post> postsByContent = postRepository.findByContentContainingIgnoreCase(term);

        Set<Post> filteredSet = Stream.of(postsByTitle, postsByCategory, postsByContent).flatMap(List::stream)
                .collect(Collectors.toSet());
        List<Post> sortedList = filteredSet.stream()
                .sorted(Comparator.comparingInt(Post::getId))
                .collect(Collectors.toList());
        return sortedList;
    }

}
