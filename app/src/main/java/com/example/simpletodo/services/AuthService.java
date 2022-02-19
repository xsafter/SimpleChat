package com.example.simpletodo.services;

import com.example.simpletodo.services.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthService {
    public static Task<AuthResult> login(String email, String password) {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password);
    }

    public static boolean isLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static Task<AuthResult> signUp(String email, String password) {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    User user = UserService.createUser(authResult);
                    System.out.println(user.toString());
                    UserService.storeUser(user);
                });
    }

    public static void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}
