package com.example.classroomactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    String login;
    TextView t;
    User u;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (getIntent()!=null){
            Intent i=getIntent();
            u=(User)i.getSerializableExtra("user");
            login=i.getStringExtra("login");
        }
        Toast toast = Toast.makeText(getApplicationContext(), "Bonjour "+u.getUser(), Toast.LENGTH_LONG);
        toast.show();
    }
}