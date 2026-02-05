package com.example.appmovil.Views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appmovil.API.ApiRest;
import com.example.appmovil.Adapters.AdaptadorInicio;
import com.example.appmovil.Models.Publicacion;
import com.example.appmovil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class Inicio extends Fragment {
    ArrayList<Publicacion> publicaciones = new ArrayList<>();
    AdaptadorInicio adaptadorInicio;
    RecyclerView rv;
    Toolbar tb;
    BottomNavigationView bnv;
    RecyclerView.LayoutManager layoutManagerPublicaciones;
    ApiRest api;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        api = new ApiRest();
        tb = view.findViewById(R.id.toolbar3);
        bnv = view.findViewById(R.id.bottomNavigationView);
        rv = view.findViewById(R.id.recyclerView);

        layoutManagerPublicaciones = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManagerPublicaciones);

        adaptadorInicio = new AdaptadorInicio(publicaciones, getContext());
        rv.setAdapter(adaptadorInicio);

        rv.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL)
        );

        return view;
    }
}