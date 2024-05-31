package com.project.questapp.controller;

import com.project.questapp.model.Comment;
import com.project.questapp.model.dto.CommentCreateRequest;
import com.project.questapp.model.dto.CommentUpdateRequest;
import com.project.questapp.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
                                        @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWitParam(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest newCommentCreate){
        return commentService.createOneComment(newCommentCreate);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId,
                                    @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateOneCommentById(commentId,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }


}
