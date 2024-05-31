package com.project.questapp.controller;

import com.project.questapp.model.Post;
import com.project.questapp.model.dto.PostCreateRequest;
import com.project.questapp.model.dto.PostUpdateRequest;
import com.project.questapp.repository.UserRepository;
import com.project.questapp.service.PostService;
import com.project.questapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId , @RequestBody PostUpdateRequest updateRequest){
        return postService.updateOnePostById(postId , updateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId ){
        postService.deleteOnePostById(postId);
    }




}
