package com.abarham97.codefellowship.Repositry;

import com.abarham97.codefellowship.Post;
import com.abarham97.codefellowship.UserSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepositry extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(UserSite userSite);
}
