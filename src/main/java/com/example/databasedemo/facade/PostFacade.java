package com.example.databasedemo.facade;

import com.example.databasedemo.entity.Post;
import com.example.databasedemo.exception.ResourceNotFoundException;
import com.example.databasedemo.repository.PostRepository;
import com.example.databasedemo.request.PostRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostFacade {

    private PostRepository postRepository;

    public PostFacade(
            PostRepository postRepository
    ) {
        this.postRepository = postRepository;
    }

    public Post getPostById(long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        return postRepository.save(post);
    }

    public Post updatePost(long postId, PostRequest postRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        return postRepository.save(post);
    }
}
