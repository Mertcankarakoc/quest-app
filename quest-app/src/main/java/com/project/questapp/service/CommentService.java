package com.project.questapp.service;

import com.project.questapp.model.Comment;
import com.project.questapp.model.Post;
import com.project.questapp.model.User;
import com.project.questapp.model.dto.CommentCreateRequest;
import com.project.questapp.model.dto.CommentUpdateRequest;
import com.project.questapp.repository.CommentRepository;
import com.project.questapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostService postService, UserService userService, PostService postService1) {
        this.commentRepository = commentRepository;

        this.userService = userService;
        this.postService = postService1;
    }

    public List<Comment> getAllCommentsWitParam(Optional<Long> userId, Optional<Long> postId){
        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }else {
            return commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest newCommentCreate) {
        Optional<User> userOptional = userService.getOneUserById(newCommentCreate.getUserId());
        Post post = postService.getOnePostById(newCommentCreate.getPostId());
        if (userOptional.isPresent() && post != null) {
            User user = userOptional.get();
            Comment commentToSave = new Comment();
            commentToSave.setId(newCommentCreate.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(newCommentCreate.getText());
            return commentRepository.save(commentToSave);
        } else {
            return null;
        }
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }else {
            return null;
        }
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
