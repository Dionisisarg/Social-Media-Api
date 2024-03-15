package com.example.SocialMediaApi.Repository;

import com.example.SocialMediaApi.Entity.Follower;
import com.example.SocialMediaApi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepo extends JpaRepository<Follower, String> {
    List<Follower> findByFollowedUser(User user);
    List<Follower> findByFollower(User user);

    Follower findByFollowerAndFollowedUser(User follower, User followedUser);


}
