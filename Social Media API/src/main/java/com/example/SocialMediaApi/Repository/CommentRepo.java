package com.example.SocialMediaApi.Repository;

import com.example.SocialMediaApi.Entity.Comment;
import com.example.SocialMediaApi.Entity.Post;
import com.example.SocialMediaApi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findTop10ByPostOrderByReleaseDateCommentsDesc(Post post);

    List<Comment> findTop100ByPostIdOrderByReleaseDateCommentsDesc(int id);


    List<Comment> findByUserInOrderByReleaseDateCommentsDesc(List<User> followedUsers);
}
