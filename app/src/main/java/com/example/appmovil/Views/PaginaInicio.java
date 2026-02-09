package com.example.appmovil.Views;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appmovil.Fragments.Perfil;
import com.example.appmovil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class PaginaInicio extends AppCompatActivity {
    private BottomNavigationView bnv;
    private Inicio inicioFragment = new Inicio();
    private Perfil perfilFragment = new Perfil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bnv = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, inicioFragment).commit();

        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId(); //Sgun donde pulses aparece un fragmento
                if (id == R.id.inicio){
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, inicioFragment).commit();
                } else if (id == R.id.perfil){
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, perfilFragment).commit();
                }

                return false;
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_footer, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id=item.getItemId();
//        return super.onOptionsItemSelected(item);
//    }
}