package com.example.appmovil.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Models.Publicacion;

import java.util.ArrayList;

public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.InicioViewHolder>{
    ArrayList<Publicacion> publicaciones = new ArrayList<>();

    @NonNull
    @Override
    public InicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull InicioViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class InicioViewHolder extends RecyclerView.ViewHolder{
        private TextView tvLikes, tvComentarios, tvCompartidos, tvDescripcion;
        private ImageButton ibLikes, ibComentarios, ibCompartidos, ibPerfil, ibOpciones;
        private ImageView ivFotoPublicacion;
        private Toolbar tb;
        private ScrollView sv;
        public InicioViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
