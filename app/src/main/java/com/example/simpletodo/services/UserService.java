package com.example.simpletodo.services;

import com.example.simpletodo.services.models.User;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;

public class UserService {
    public static User createUser(AuthResult authResult) {
        return new User("", authResult.getUser().getEmail(),
                authResult.getUser().getMetadata().getCreationTimestamp());
//        authResult.getUser().getMetadata().getCreationTimestamp();
    }

    public static void storeUser(User user) {
        //TODO: Replace
        FirebaseDatabase.getInstance()
                .getReference("users")
                .setValue(user);
    }
}
