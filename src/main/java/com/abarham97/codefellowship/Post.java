package com.abarham97.codefellowship;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Post {
    @Id
    @GeneratedValue
    private long id;


    private String body;


    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSite user;

    public Post(String body, Date createdAt) {
        this.body = body;
        this.createdAt = createdAt;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserSite getUser() {
        return user;
    }

    public void setUser(UserSite user) {
        this.user = user;
    }
}
