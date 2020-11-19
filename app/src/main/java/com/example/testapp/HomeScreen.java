package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Intent oudeIntent = getIntent();
        int successMessage = oudeIntent.getIntExtra("success", 1);
        int userid = oudeIntent.getIntExtra("userid", 0);
        TextView successMess = findViewById(R.id.successmessage);
        successMess.setText("Je gebruikersid is "+userid);
    }
}