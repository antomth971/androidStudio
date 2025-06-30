package com.example.classroomactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classroomactivity.R;

public class MainActivity extends AppCompatActivity {

    private EditText EditUser, EditPassword;
    private String password;
    public static String user;
    public Button connexion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditUser = findViewById(R.id.login_connexion);
        EditPassword = findViewById(R.id.passwd_connexion);


        connexion=findViewById(R.id.connexion);
        //System.out.println("acy  "+password+user);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=EditPassword.getText().toString();
                user=EditUser.getText().toString();
                System.out.println("je clique  "+password+user);

                if(password.equals("")||user.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Il manque une information", Toast.LENGTH_LONG);
                }
                else {
                    Intent i=new Intent(MainActivity.this,SecondActivity.class);
                    i.putExtra("login",user);
                    System.out.println("je lance ");
                    User user1=new User(user,password);
                    Bundle b = new Bundle();
                    b.putSerializable("user",user1);
                    i.putExtras(b);
                    startActivity(i);

                }

            }
        });


    }
}