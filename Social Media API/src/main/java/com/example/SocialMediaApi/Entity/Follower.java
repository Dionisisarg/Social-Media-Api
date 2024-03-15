package com.example.SocialMediaApi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_user_id")
    private User followedUser;

    public Follower(){

    }

    public Follower(int id, User follower, User followedUser) {
        this.id = id;
        this.follower = follower;
        this.followedUser = followedUser;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public User getFollower() {

        return follower;
    }

    public void setFollower(User user) {

        this.follower = user;
    }

    public User getFollowedUser() {

        return followedUser;
    }

    public void setFollowedUser(User followedUser) {

        this.followedUser = followedUser;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id=" + id +
                ", follower=" + follower +
                "followedUser=" + followedUser +
                '}';
    }
}
