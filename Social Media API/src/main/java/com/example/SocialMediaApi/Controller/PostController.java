package com.example.SocialMediaApi.Controller;

import com.example.SocialMediaApi.Entity.Comment;
import com.example.SocialMediaApi.Entity.Post;
import com.example.SocialMediaApi.Entity.User;
import com.example.SocialMediaApi.Repository.UserRepo;
import com.example.SocialMediaApi.Service.PostService;
import com.example.SocialMediaApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping(path = "/save")
    public Post savePost(@RequestBody Post post) {
        Post post1 = postService.createPost(post);
        return post1;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Post>> getPostsForUser(@PathVariable int id){
        List<Post> posts = postService.getPostsForUser(id);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{id}/comments")
    public ResponseEntity<List<Comment>> getLatestCommentsForUserPosts(@PathVariable int id) {
        List<Comment> comments = postService.getLatestCommentsForUserPosts(id);
        return ResponseEntity.ok(comments);
    }

    public User getUserById(int id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.orElse(null);}

    @GetMapping("/followed-posts/{id}")
    public ResponseEntity<List<Post>> getFollowedPosts(@PathVariable int id) {
        User user = getUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        List<Post> posts = postService.getPostsByFollowedUsers(user);
        return ResponseEntity.ok(posts);
    }
}
