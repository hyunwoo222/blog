package com.hyun.blog.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;

    private String email;
    private String password;
    private String username;
    private String bio;
    private String image;

    protected User(){
    }

    public User(String username,String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }

    public static User createNewUser(String username,String email, String password) {
        return new User(username,email,password);
    }
}
