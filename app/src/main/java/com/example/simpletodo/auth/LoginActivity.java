package com.example.simpletodo.auth;

import android.app.ProgressDialog;
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
    Button signupButton2;
    Button loginButton2;
    EditText emailField2;
    EditText passwordField2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        signupButton2.setOnClickListener(view -> {
            AuthService.signOut();
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
        });
        ProgressDialog progressDialog = showProgressDialog();
        progressDialog.setTitle("Loading");
        progressDialog.show();
        loginButton2.setOnClickListener(view -> {
            String email = emailField2.getText().toString();
            String password = passwordField2.getText().toString();


            if (email.isEmpty()) {
                emailField2.setError("Email is empty");
//                toast("Email is empty");
                return;
            }
            if (!(email.contains("@") || email.contains(".") || email.length() > 3)) {
                emailField2.setError("Email is not valid");
//                toast("Email is not valid");
                return;
            }

            if (password.isEmpty()) {
                passwordField2.setError("Password is empty");
//                toast("Password is empty");
                return;
            }
            if (password.length() < 6) {
                passwordField2.setError("Password is too weak");
//                toast("Password is too weak");
            }


            AuthService.login(email, password).addOnSuccessListener(authResult -> {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }).addOnSuccessListener(authResult -> {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getApplicationContext(), "Can't create user", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    })
                    .addOnCompleteListener(task -> progressDialog.dismiss());
        });

    }

    ProgressDialog showProgressDialog() {
        return new ProgressDialog(this);
        //progressDialog.dismiss();
    }

    void initView() {
        signupButton2 = findViewById(R.id.signupButton2);
        loginButton2 = findViewById(R.id.loginButton2);
        emailField2 = findViewById(R.id.emailField2);
        passwordField2 = findViewById(R.id.passwordField2);
    }
}