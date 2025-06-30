package com.example.arrondissement;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

//import com.example.arrondissement.databinding.ActivityMenuBinding;

public class Menu extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    //private ActivityMenuBinding binding;

    BottomNavigationView menu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu=findViewById(R.id.barre_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new AccueilFragment()).commit();

        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frament=null;
                switch (item.getItemId()) {
                    case R.id.accueil:
                        frament=new AccueilFragment();
                        break;

                    case R.id.score:
                        frament=new ScoreFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,frament).commit();

                return true;
            }
        });


    }
}