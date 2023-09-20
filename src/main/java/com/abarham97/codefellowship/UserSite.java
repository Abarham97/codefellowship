package com.abarham97.codefellowship;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

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
}
