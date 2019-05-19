package com.example.databasedemo.controller;


import com.example.databasedemo.entity.Post;
import com.example.databasedemo.exception.PostNotFoundException;
import com.example.databasedemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts()
    {
        return this.postRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(
            @PathVariable(value = "id") Long postId
    ) {
        return this.postRepository
            .findById(postId)
            .orElseThrow(() -> new PostNotFoundException("Post", "id", postId))
        ;

    }

    @PostMapping("/posts")
    public Post createPost(
            @Valid @RequestBody Post post
    ) {
        return this.postRepository.save(post);
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(
            @PathVariable(value = "id") Long postId,
            @Valid @RequestBody Post postData
    ) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post", "id", postId));

        post.setTitle(postData.getTitle());
        post.setContent(postData.getTitle());

        return postRepository.save(post);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(
        @PathVariable(value = "id") Long postId
    ) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post", "id", postId));

        postRepository.delete(post);

        return ResponseEntity.ok().build();
    }
}
