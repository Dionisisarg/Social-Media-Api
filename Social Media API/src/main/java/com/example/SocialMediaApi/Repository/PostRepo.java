package com.example.SocialMediaApi.Repository;

import com.example.SocialMediaApi.Entity.Post;
import com.example.SocialMediaApi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(int id);

    List<Post> findByUserIdOrderByReleaseDatePostsDesc(int id);

    List<Post> findByUserInOrderByReleaseDatePostsDesc(List<User> followedUsers);

}
