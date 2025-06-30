package com.example.arrondissement;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;


public class Accueil extends AppCompatActivity {

    GridView simpleGrid;
    int logos[] = {R.drawable.img_district1, R.drawable.img_district2, R.drawable.img_district3, R.drawable.img_district4,
            R.drawable.img_district5, R.drawable.img_district6, R.drawable.img_district7, R.drawable.img_district8, R.drawable.img_district9,
            R.drawable.img_district10, R.drawable.img_district11, R.drawable.img_district12, R.drawable.img_district13,R.drawable.img_district14,R.drawable.img_district15,R.drawable.img_district16
    ,R.drawable.img_district17,R.drawable.img_district18,R.drawable.img_district19,R.drawable.img_district20};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleGrid = (GridView) findViewById(R.id.simpleGridView); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapterImg customAdapter = new CustomAdapterImg(getApplicationContext(), logos);
        simpleGrid.setAdapter(customAdapter);
        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(Accueil.this, QuestionReponse.class);
                intent.putExtra("image", logos[position]); // put image data in Intent
                intent.putExtra("arrondissement",position+1);
                startActivity(intent); // start Intent*/

            }
        });
    }
}
