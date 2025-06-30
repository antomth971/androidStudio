package com.example.arrondissement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerAdapterButton extends ArrayAdapter {
    Context context;
    static ArrayList<Button> logos;
    static ArrayList<Button> logos2=new ArrayList<>();
    ArrayList<String> liste_reponse;
    String bonne_reponse;
    LayoutInflater inflter;

    int p =-1;
    int layout;
    static boolean click=false;


    public CustomerAdapterButton(Context applicationContext,int layout, ArrayList<Button> logos,ArrayList<String> liste_reponse,String bonne_reponse) {
        super(applicationContext,layout);
        this.context = applicationContext;
        this.logos = logos;
        this.layout=layout;
        this.liste_reponse=liste_reponse;
        this.bonne_reponse=bonne_reponse;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return logos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {



        view = inflter.inflate(layout, null); // inflate the layout
        Button icon = (Button) view.findViewById(R.id.question_button); // get the reference of ImageView
        icon.setText(liste_reponse.get(i));
        System.out.println("je suis dans get view");

        if(click) {
            if (i != p) {
                icon.setBackgroundColor(Color.RED);
            } else {
                icon.setBackgroundColor(Color.GREEN);
            }
        }

        if(icon.getText().toString().equals(bonne_reponse)){
            p=i;
        }

        // System.out.println("bonne réponse "+bonne_reponse+" texte cliqué "+icon.getText().toString());
        icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              if(!click) {
                  if (icon.getText().toString().equals(bonne_reponse)) {
                      System.out.println("Bonne réponse ! ");
                      Toast.makeText(context, "Bonne réponse", Toast.LENGTH_SHORT).show();
                      sendScore(Connexion.user);

                  } else {
                      Toast.makeText(context, "Mauvaise réponse. La bonne réponse était : "+bonne_reponse, Toast.LENGTH_SHORT).show();

                  }

                    notifyDataSetChanged();
                  //changeColor(p,);

                  click =true;
              }

            }
        });

        return view;
    }

    void sendScore(String login){
        String URL = "http://10.10.26.94:80/lpiot/index.php";





        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                   /* String activite="";

                    try {
                        JSONArray obj = new JSONArray(response);
                        for (int i =0;i< obj.length();i++){
                            activite = activite + obj.getString(i);
                        }
                        Toast.makeText(context, "Mauvaise réponse. La bonne réponse était :"+activite, Toast.LENGTH_SHORT).show();

                    System.out.println("envoie score "+activite);
                    } catch (JSONException e) {
                        System.out.println(e);
                    }*/
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
                data.put("verif","ajout_score");
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
