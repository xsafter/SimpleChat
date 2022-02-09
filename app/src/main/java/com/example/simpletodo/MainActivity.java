package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView textview_log; Button button; EditText requestHeader; EditText requestMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkViews();

        button.setOnClickListener(view -> dbRequest());
    }
    private void dbRequest(String Reference, String request){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue(request);}
    private void dbRequest(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(requestHeader.getText().toString());
        myRef.setValue(requestMessage.getText().toString());}


    private void linkViews(){
        textview_log = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        requestHeader = findViewById(R.id.requestHeader);
        requestMessage = findViewById(R.id.requestMessage);
    }
}