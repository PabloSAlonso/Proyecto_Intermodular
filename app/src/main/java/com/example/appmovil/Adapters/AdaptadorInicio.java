package com.example.appmovil.Adapters;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Models.Publicacion;

import java.util.ArrayList;

public class AdaptadorInicio {
    ArrayList<Publicacion> publicaciones = new ArrayList<>();
    public class InicioViewHolder extends RecyclerView.ViewHolder{

        public InicioViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
