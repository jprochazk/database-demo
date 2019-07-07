package com.example.databasedemo.controller;


import com.example.databasedemo.entity.Post;
import com.example.databasedemo.facade.PostFacade;
import com.example.databasedemo.request.PostRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostFacade postFacade;

    public PostController(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public List<Post> getAllPosts()
    {
        return this.postFacade.getAllPosts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Post getPostById(
            @PathVariable(value = "id") Long postId
    ) {
        return this.postFacade.getPostById(postId);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Post createPost(
            @Valid @RequestBody PostRequest postRequest
            ) {
        return this.postFacade.createPost(postRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Post updatePost(
            @PathVariable(value = "id") Long postId,
            @Valid @RequestBody PostRequest postRequest
    ) {
        return this.postFacade.updatePost(postId, postRequest);
    }
}
