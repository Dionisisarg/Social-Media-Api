package com.example.SocialMediaApi.Controller;

import com.example.SocialMediaApi.Entity.Comment;
import com.example.SocialMediaApi.Entity.User;
import com.example.SocialMediaApi.Repository.UserRepo;
import com.example.SocialMediaApi.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping(path = "/save")
    public Comment saveComment(@RequestBody Comment comment) {
        Comment comment1 = commentService.createComment(comment);
        return comment1;}

    @GetMapping("/{id}/latest-comments")
    public ResponseEntity<List<Comment>> getLatestCommentsByUser(@PathVariable int id){
        List<Comment> latestComments = commentService.getLatestCommentsByUser(id);
        return ResponseEntity.ok(latestComments);
    }

    public User getUserById(int id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.orElse(null);}

    @GetMapping("/followed-comments/{id}")
    public ResponseEntity<List<Comment>> getCommentsByFollowedUsers(@PathVariable int id) {
        User user = getUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        List<Comment> posts = commentService.getCommentsByFollowedUsers(user);
        return ResponseEntity.ok(posts);
    }
}
