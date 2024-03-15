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
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;


@Service
public class PostService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private FollowerRepo followerRepo;
    @Autowired
    private CommentRepo commentRepo;

    public Post createPost(Post post){

        return postRepo.save(post);
    }

    public List<Post> getPostsForUser(int id){
        return postRepo.findByUserId(id);
    }


    public List<Comment> getLatestCommentsForUserPosts(int id) {
        List<Post> userPosts = postRepo.findByUserIdOrderByReleaseDatePostsDesc(id);
        List<Comment> comments = new ArrayList<>();

        for (Post post : userPosts) {
            List<Comment> postComments = commentRepo.findTop100ByPostIdOrderByReleaseDateCommentsDesc(post.getId());
            comments.addAll(postComments);
        }

        comments.sort(Comparator.comparing(Comment::getReleaseDateComments).reversed());

        if (comments.size()>100){
            comments = comments.subList(0,100);
        }

        return comments;
    }

    public List<Post> getPostsByFollowedUsers(User user) {
        List<Follower> followers = followerRepo.findByFollowedUser(user);
        List<User> followedUsers = followers.stream().map(Follower::getFollower).collect(Collectors.toList());
        return postRepo.findByUserInOrderByReleaseDatePostsDesc(followedUsers);
    }
}

