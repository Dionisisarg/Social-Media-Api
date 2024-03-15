package com.example.SocialMediaApi.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id", length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "release_date_comments")
    private Date releaseDateComments;
    @Column(name = "body")
    private String body;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name ="user_name")
    private User user;


    public Comment(){

    }

    public Comment(int id, Date releaseDateComments, String body, Post post, User user) {
        this.id = id;
        this.releaseDateComments = releaseDateComments;
        this.body = body;
        this.post = post;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReleaseDateComments() {
        return releaseDateComments;
    }

    public void setReleaseDateComments(Date releaseDateComments) {
        this.releaseDateComments = releaseDateComments;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", releaseDateComments=" + releaseDateComments +
                ", body='" + body + '\'' +
                ", post='" + post +
                ", user='" + user +
                '}';
    }
}
