package com.abarham97.codefellowship;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class UserSite implements UserDetails {

    @Id
    @GeneratedValue
    private long id;
    private String userName;

    private String password;

    private String firstname;
    private String lastname;


    private String dateOfBirth;

    private  String bio;
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<UserSite> following ;


    public UserSite(String userName, String password,String firstname,String lastname ,String dateOfBirth, String bio) {
        this.userName = userName;
        this.password = password;
        this.firstname=firstname;
        this.lastname=lastname;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public UserSite() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public long getId() {
        return id;
    }



    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName ;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<UserSite> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserSite> following) {
        this.following = following;
    }
}
