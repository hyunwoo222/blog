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

    public User(String email,String username, String password) {
        this(email,username,null,null);
        this.password = password;
    }

    public User(String email,String username,String bio, String image) {
        this.email = email;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public long getId() {
        return id;
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

    public static User SignUp(String username, String email, String password) {
        return new User(username,email,password);
    }
}
