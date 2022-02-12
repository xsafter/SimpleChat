package com.example.simpletodo.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodo.MainActivity;
import com.example.simpletodo.R;
import com.example.simpletodo.services.AuthService;

public class LoginActivity extends AppCompatActivity {
    Button signupButton;
    Button loginButton;
    EditText emailField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signupButton.setOnClickListener(view -> {
            AuthService.signOut();
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
        });

        loginButton.setOnClickListener(view -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();


            if (email.isEmpty()) {
                emailField.setError("Email is empty");
//                toast("Email is empty");
                return;
            }
            if (!(email.contains("@") || email.contains(".") || email.length() > 3)) {
                emailField.setError("Email is not valid");
//                toast("Email is not valid");
                return;
            }

            if (password.isEmpty()) {
                passwordField.setError("Password is empty");
//                toast("Password is empty");
                return;
            }
            if (password.length() < 6) {
                passwordField.setError("Password is too weak");
//                toast("Password is too weak");
            }


            AuthService.login(email, password).addOnSuccessListener(authResult ->
            {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getApplicationContext(), "Can't create user", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    });
        });

    }

    void initView() {
        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginButton);
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
    }
}