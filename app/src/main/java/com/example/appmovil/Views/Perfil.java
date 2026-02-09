package com.example.appmovil.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmovil.Adapters.AdaptadorInicio;
import com.example.appmovil.Models.Publicacion;
import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;

import java.util.ArrayList;

public class Perfil extends Fragment {

    private ImageView imgFotoPerfil;
    private TextView txtNickname, txtDescripcion;
    private RecyclerView rvPublicaciones;

    private Usuario usuario;
    private ArrayList<Publicacion> publicacionesUsuario;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgFotoPerfil = view.findViewById(R.id.imgFotoPerfil);
        txtNickname = view.findViewById(R.id.txtNickname);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        rvPublicaciones = view.findViewById(R.id.rvPublicaciones);

        usuario = obtenerUsuario();
        publicacionesUsuario = obtenerPublicacionesUsuario(usuario.getId());

        txtNickname.setText(usuario.getNickname());
        txtDescripcion.setText("Descripción del usuario");

        imgFotoPerfil.setImageResource(R.drawable.user_icon);

        AdaptadorInicio adaptador =
                new AdaptadorInicio(publicacionesUsuario, getContext());

        rvPublicaciones.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPublicaciones.setAdapter(adaptador);
    }

    private Usuario obtenerUsuario() {
        // Esto luego vendrá de API.
        return new Usuario(
                1,
                "Nombre",
                "Apellidos",
                "nickname",
                "email@email.com",
                "1234",
                null,
                null,
                null
        );
    }

    private ArrayList<Publicacion> obtenerPublicacionesUsuario(int idUsuario) {

        ArrayList<Publicacion> lista = new ArrayList<>();

        // Aquí luego filtrar por id_usuario
        // lista.add(...)

        return lista;
    }
}
