package com.example.arrondissement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Connexion extends AppCompatActivity {

    private EditText EditUser, EditPassword;
    private String password;
    public static String user="root";
    public Button connexion;
    int connect = -1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        EditUser = findViewById(R.id.login_connexion);
        EditPassword = findViewById(R.id.passwd_connexion);


        connexion = findViewById(R.id.connexion);
        //System.out.println("acy  "+password+user);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = EditPassword.getText().toString();
                user = EditUser.getText().toString();


                if (password.equals("") || user.equals("")) {
                    System.out.println("dans if  ");
                    Toast toast = Toast.makeText(Connexion.this, "Il manque une information", Toast.LENGTH_LONG);
                } else {
                    System.out.println("dans else  ");
                        login_(user,password);

                }

            }
        });

    }

    public void login_(String login, String password) {



            String URL = "http://10.10.26.94:80/lpiot/index.php";





            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (Integer.parseInt(response)>0) {



                        Intent i = new Intent(Connexion.this, Accueil.class);
                        i.putExtra("login", user);
                        System.out.println("je lance ");
                        User user1 = new User(user, password);
                        Bundle b = new Bundle();
                        b.putSerializable("user", user1);
                        i.putExtras(b);
                        startActivity(i);
                    }else{
                        Toast.makeText(Connexion.this, "Identifiant ou mot de passe incorrect ", Toast.LENGTH_SHORT).show();
                        connect=0;
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                        System.out.println("error "+error);
                }


            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("login", login);
                    data.put("mdp", password);
                    data.put("verif","connexion");
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
    }
}