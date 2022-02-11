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

public class SignUp extends AppCompatActivity {
    Button signupButton;
    Button loginButton;
    EditText emailField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
        signupButton.setOnClickListener(view -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();


            if (email.isEmpty()) {
                emailField.setError("Email is empty");
                toast("Email is empty");
                return;
            }
            if (!(email.contains("@") || email.contains(".") || email.length() > 3)) {
                emailField.setError("Email is not valid");
                toast("Email is not valid");
                return;
            }

            if (password.isEmpty()) {
                toast("Password is empty");
                return;
            }
            if (password.length() < 6) {
                toast("Password is too weak");
            }


            AuthService.signUp(email, password).addOnSuccessListener(authResult ->
            {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            })
                    .addOnFailureListener(e -> {
                        toast("Can't create user");
                        e.printStackTrace();
                    });
        });
    }

    private void initView() {
        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginButton);
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
    }

    public void toast(String toast) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}