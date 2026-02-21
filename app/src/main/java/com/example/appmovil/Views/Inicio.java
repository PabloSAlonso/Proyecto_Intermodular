package com.example.appmovil.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.API.ApiRest;
import com.example.appmovil.Adapters.AdaptadorInicio;
import com.example.appmovil.Models.Publicacion;
import com.example.appmovil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class Inicio extends Fragment {
    private ArrayList<Publicacion> publicaciones = new ArrayList<>();
    private AdaptadorInicio adaptadorInicio;
    private RecyclerView rv;
    private BottomNavigationView bnv;
    private RecyclerView.LayoutManager layoutManagerPublicaciones;
    private ApiRest api;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        api = new ApiRest();
        bnv = view.findViewById(R.id.bottomNavigationView);
        rv = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        layoutManagerPublicaciones = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManagerPublicaciones);

        // Initialize adapter with empty list
        adaptadorInicio = new AdaptadorInicio(publicaciones, getContext());
        rv.setAdapter(adaptadorInicio);

        rv.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL)
        );

        // Load publications from API
        cargarPublicaciones();

        return view;
    }
    
    private void cargarPublicaciones() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        
        api.obtenerPublicaciones(new ApiRest.PublicationsCallback() {
            @Override
            public void onPublicationsResult(boolean success, ArrayList<Publicacion> nuevasPublicaciones, String errorMessage) {
                if (getActivity() == null) return;
                
                getActivity().runOnUiThread(() -> {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    
                    if (success && nuevasPublicaciones != null) {
                        publicaciones.clear();
                        publicaciones.addAll(nuevasPublicaciones);
                        adaptadorInicio.updateData(publicaciones);
                    } else {
                        // If API fails, load mock data for demo
                        Toast.makeText(getContext(), 
                                "Cargando publicaciones de ejemplo...", 
                                Toast.LENGTH_SHORT).show();
                        cargarPublicacionesMock();
                    }
                });
            }
        });
    }
    
    private void cargarPublicacionesMock() {
        publicaciones.clear();

        publicaciones.add(new Publicacion(
                1,
                10,
                new java.sql.Date(System.currentTimeMillis()),
                null,
                "Primera publicación de prueba",
                12,
                3
        ));

        publicaciones.add(new Publicacion(
                2,
                11,
                new java.sql.Date(System.currentTimeMillis()),
                null,
                "Día increíble por la playa",
                45,
                10
        ));

        publicaciones.add(new Publicacion(
                3,
                12,
                new java.sql.Date(System.currentTimeMillis()),
                null,
                "Nuevo setup terminado",
                102,
                22
        ));

        publicaciones.add(new Publicacion(
                4,
                13,
                new java.sql.Date(System.currentTimeMillis()),
                null,
                "Trabajando en nuevos proyectos",
                67,
                8
        ));
        
        adaptadorInicio.updateData(publicaciones);
    }
}

