package com.example.appmovil.Views;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Adapters.AdaptadorInicio;
import com.example.appmovil.Models.Publicacion;
import com.example.appmovil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class PaginaInicio extends AppCompatActivity {
    ArrayList<Publicacion> publicaciones = new ArrayList<>();
    AdaptadorInicio adaptadorInicio;
    RecyclerView rv;
    Toolbar tb;
    BottomNavigationView bnv;
    RecyclerView.LayoutManager layoutManagerPublicaciones;

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
        // Recoger Publicaciones de la API
        tb = findViewById(R.id.toolbar3);
        bnv = findViewById(R.id.bottomNavigationView);
        ActionBar ab = getSupportActionBar();
        setSupportActionBar(tb);
        rv = findViewById(R.id.recyclerView);
        rv.setAdapter(adaptadorInicio);
        layoutManagerPublicaciones = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        rv.setLayoutManager(layoutManagerPublicaciones);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL
        ));

    }
}