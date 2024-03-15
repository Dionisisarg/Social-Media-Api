package com.example.SocialMediaApi.Service;

import com.example.SocialMediaApi.Entity.Comment;
import com.example.SocialMediaApi.Entity.Follower;
import com.example.SocialMediaApi.Entity.Post;
import com.example.SocialMediaApi.Entity.User;
import com.example.SocialMediaApi.Repository.CommentRepo;
import com.example.SocialMediaApi.Repository.FollowerRepo;
import com.example.SocialMediaApi.Repository.PostRepo;
import com.example.SocialMediaApi.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FollowerRepo followerRepo;

    public Comment createComment(Comment comment){

        return commentRepo.save(comment);
    }

    public List<Comment> getLatestCommentsByUser(int id){
        List<Post> posts = postRepo.findByUserId(id);

        List<Comment> latestComments = new ArrayList<>();
        for (Post post : posts){
            List<Comment> comments = commentRepo.findTop10ByPostOrderByReleaseDateCommentsDesc(post);
            latestComments.addAll(comments);
        }
        return latestComments;
    }

    public List<Comment> getCommentsByFollowedUsers(User user) {
        List<Follower> followers = followerRepo.findByFollowedUser(user);
        List<User> followedUsers = followers.stream().map(Follower::getFollower).collect(Collectors.toList());
        return commentRepo.findByUserInOrderByReleaseDateCommentsDesc(followedUsers);
    }
}
