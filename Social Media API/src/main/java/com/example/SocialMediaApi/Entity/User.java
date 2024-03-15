package com.example.SocialMediaApi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name="id", length = 500)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="type")
    private String type;


    public User(int id, String username,  String email, String password, String type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }


    public User() {

    }

    public int getId() {
        return id;}

    public void setId(int id) {

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail( String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    public String getType(){

        return type;
    }
    public void setType(String type){

        this.type = type;
    }

    @Override
    public String toString(){
        return "User{"+
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
