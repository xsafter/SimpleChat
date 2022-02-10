package com.example.simpletodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.*;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView textview_log; Button button;
    EditText requestHeader; EditText requestMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkViews();

        button.setOnClickListener(view -> dbRequest());
        dbLog("Debug", "App is ready");
        listenChanges("");
    }

    protected void dbLog(String tag, String message){
        StringBuilder builder = new StringBuilder();
        builder.append(textview_log.getText().toString());
        builder.append("<br>");
        builder.append(getColoredSpanned(new Date().toLocaleString(), "#5f2c3e") +" "+getColoredSpanned(tag, "#008080")+": <b>"+message+"</b>");
        builder.append("<br>");
        textview_log.setText(Html.fromHtml(builder.toString()));
    }

    private void listenChanges(String request){
        FirebaseDatabase listenDatabase = FirebaseDatabase.getInstance("https://chat-app-5b288-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference reference = listenDatabase.getReference(request);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbLog("Data Changed", snapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dbLog("DB Error!", getColoredSpanned(error.getMessage(), "#cc0000"));
            }
        });
    }
    @Deprecated
    private void dbRequest(String Reference, String request){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("debug/debug");
        myRef.setValue(request);}

    private void dbRequest(){
        if (requestMessage.getText().toString().isEmpty()){
            Toast.makeText(this, "Request message is empty!", Toast.LENGTH_LONG);return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(requestHeader.getText().toString());
        myRef.setValue(requestMessage.getText().toString());
        dbLog(
                "Request",
                "<font color=#8A2BE2> Request to: </font>" +
                        requestHeader.getText().toString() +
                        "<font color=#8A2BE2> with value: </font>" +
                        requestMessage.getText().toString()
        );
        myRef.setValue(requestMessage.getText().toString())
                .addOnSuccessListener(unused -> { dbLog("Request", "<font color=#32CD32> Sent successfully!  </font>");
                });
    }



    private void linkViews(){
        textview_log = findViewById(R.id.LogTextView);
        button = findViewById(R.id.button);
        requestHeader = findViewById(R.id.requestHeader);
        requestMessage = findViewById(R.id.requestMessage);
    }

    private String getColoredSpanned(String text, String color) { return "<font color=" + color + ">" + text + "</font>"; }
}