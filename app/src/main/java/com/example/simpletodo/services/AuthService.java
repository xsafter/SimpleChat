package com.example.simpletodo.services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthService {
    public static Task<AuthResult> login(String email, String password) {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password);
    }

    public static void logInSilently() {
        //TODO
    }

    public static Task<AuthResult> signUp(String email, String password) {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password);
    }

    public static void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}
