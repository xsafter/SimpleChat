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

public class SignUpActivity extends AppCompatActivity {
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
            AuthService.signOut();
            startActivity(new Intent(this, SignUpActivity.class));
        });

        signupButton.setOnClickListener(view -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            ProgressDialog progressDialog = showProgressDialog();
            progressDialog.setTitle("Loading");
            progressDialog.show();

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


            AuthService.signUp(email, password).addOnSuccessListener(authResult ->
            {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            })
                    .addOnSuccessListener(authResult -> {
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                    })
                    .addOnFailureListener(e -> {
                        toast("Can't create user");
                        e.printStackTrace();
                    })
                    .addOnCompleteListener(task -> progressDialog.dismiss());
        });
    }

    ProgressDialog showProgressDialog() {
        return new ProgressDialog(this);
        //progressDialog.dismiss();
    }

    private void initView() {
        signupButton = findViewById(R.id.signupButton2);
        loginButton = findViewById(R.id.loginButton2);
        emailField = findViewById(R.id.emailField2);
        passwordField = findViewById(R.id.passwordField2);
    }

    public void toast(String toast) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}