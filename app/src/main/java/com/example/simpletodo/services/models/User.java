package com.example.simpletodo.services.models;

import androidx.annotation.NonNull;

import java.util.Date;


public class User {
    public String name;
    public String email;
    public Date creationDate;

    public User() {
    }

    public User(String name, String email, long creationDate) {
        this.name = name;
        this.email = email;
        this.creationDate = new Date(creationDate);
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
