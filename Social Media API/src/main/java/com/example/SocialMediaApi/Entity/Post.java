package com.example.SocialMediaApi.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "release_date_posts")
    private Date releaseDatePosts;
    @Column(name = "body", length = 1000)
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Post() {

    }

    public Post(int id, Date releaseDatePosts, String body, User user) {
        this.id = id;
        this.releaseDatePosts = releaseDatePosts;
        this.body = body;
        this.user = user;
    }

    public int  getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public Date getReleaseDatePosts() {

        return releaseDatePosts;
    }

    public void setReleaseDatePosts(Date releaseDatePosts) {

        this.releaseDatePosts = releaseDatePosts;
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", releaseDatePosts=" + releaseDatePosts +
                ", body='" + body + '\'' +
                '}';
    }
}
