package com.example.SocialMediaApi.Controller;

import com.example.SocialMediaApi.Entity.Follower;
import com.example.SocialMediaApi.Entity.User;
import com.example.SocialMediaApi.Repository.UserRepo;
import com.example.SocialMediaApi.Service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/followers")
public class FollowerController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FollowerService followerService;

    @GetMapping("/{name}/followers")
    public ResponseEntity<List<Follower>> getFollowers(@PathVariable String name){
        User user = userRepo.findByUsername(name);
        List<Follower> followers = followerService.getFollowers(user);
        return ResponseEntity.ok(followers);

    }

    @GetMapping("/{name}/following")
    public ResponseEntity<List<Follower>> getFollowing(@PathVariable String name){
        User user = userRepo.findByUsername(name);
        List<Follower> following = followerService.getFollowing(user);
        return ResponseEntity.ok(following);
    }

    @PostMapping("/{followerEmail}/follow/{followedUserEmail}")
    public ResponseEntity<Follower> addFollowerByEmail(@PathVariable String followerEmail, @PathVariable String followedUserEmail){
        followerService.addFollowerByEmail(followerEmail,followedUserEmail);
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/{followerEmail}/unfollow/{followedUserEmail}")
    public ResponseEntity<Follower> removeFollowerByEmail(@PathVariable String followerEmail, @PathVariable String followedUserEmail){
        followerService.removeFollowerByEmail(followerEmail,followedUserEmail);
        return ResponseEntity.ok().build();
    }
}
