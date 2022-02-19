package com.example.simpletodo;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView textview_log;
    Button requestButton;
    EditText requestHeader;
    EditText requestMessage;
    Button deleteButton;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkViews();

        dbLog("Debug", "App is ready");
    }

    protected void dbLog(String tag, String message) {
        StringBuilder builder = new StringBuilder();
        builder.append(textview_log.getText().toString());
        builder.append("<br>");
        builder.append(getColoredSpanned(new Date().toLocaleString(), "#5f2c3e") + " " + getColoredSpanned(tag, "#008080") + ": <b>" + message + "</b>");
        builder.append("<br>");
        textview_log.setText(Html.fromHtml(builder.toString()));
    }


    private void linkViews() {
        textview_log = findViewById(R.id.LogTextView);
        requestButton = findViewById(R.id.RequestButton);
        deleteButton = findViewById(R.id.deleteButton);
        requestHeader = findViewById(R.id.requestHeader);
        requestMessage = findViewById(R.id.requestMessage);
        logoutButton = findViewById(R.id.logoutButton);
    }

    private String getColoredSpanned(String text, String color) {
        return "<font color=" + color + ">" + text + "</font>";
    }
}