package com.example.simpletodo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodo.services.AuthService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthService.logInSilently(); //TODO


    }

}