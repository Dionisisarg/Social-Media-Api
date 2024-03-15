package com.example.SocialMediaApi.Service;

import com.example.SocialMediaApi.Entity.Follower;
import com.example.SocialMediaApi.Entity.User;
import com.example.SocialMediaApi.Repository.FollowerRepo;
import com.example.SocialMediaApi.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {
    @Autowired
    private FollowerRepo followerRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Follower> getFollowers(User user) {

        return followerRepo.findByFollowedUser(user);
    }

    public List<Follower> getFollowing(User user) {
        return followerRepo.findByFollower(user);
    }

    public void addFollowerByEmail(String followerEmail, String followedUserEmail){
        User follower = userRepo.findByEmail(followerEmail);
        User followedUser = userRepo.findByEmail(followedUserEmail);
        if (follower == null || followedUser == null){
            throw new IllegalArgumentException("User not found");
        }
        Follower followerRelationship = new Follower();
        followerRelationship.setFollower(follower);
        followerRelationship.setFollowedUser(followedUser);
        followerRepo.save(followerRelationship);
    }

    public void removeFollowerByEmail(String followerEmail, String followedUserEmail){
        User follower = userRepo.findByEmail(followerEmail);
        User followedUser = userRepo.findByEmail(followedUserEmail);
        if (follower == null || followedUser == null){
            throw new IllegalArgumentException("User not found");
        }
        Follower followerRelationship = followerRepo.findByFollowerAndFollowedUser(follower, followedUser);
        if (followerRelationship != null){
            followerRepo.delete(followerRelationship);
        }
    }
}