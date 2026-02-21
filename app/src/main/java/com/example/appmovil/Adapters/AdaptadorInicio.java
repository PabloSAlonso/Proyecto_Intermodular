package com.example.appmovil.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Models.Publicacion;
import com.example.appmovil.R;

import java.util.ArrayList;

public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.InicioViewHolder> {

    private Context contexto;
    private ArrayList<Publicacion> publicaciones;

    public AdaptadorInicio(ArrayList<Publicacion> publicaciones, Context contexto) {
        this.publicaciones = publicaciones;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public InicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celda_publicacion, parent, false);
        return new InicioViewHolder(elemento);
    }

    @Override
    public void onBindViewHolder(@NonNull InicioViewHolder holder, int position) {

        Publicacion p = publicaciones.get(position);

        holder.tvLikes.setText(String.valueOf(p.getLikes()));
        holder.tvComentarios.setText(String.valueOf(p.getComentarios()));
        holder.tvCompartidos.setText("0");

        // Descripción
        holder.tvDescripcion.setText(p.getDescripcion());

        // Imagen publicación (por ahora placeholder)
        holder.ivFotoPublicacion.setImageResource(R.drawable.ic_launcher_foreground);

        // Nombre usuario (si lo tienes)
        holder.tvNombreUsuario.setText("usuario");
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    public static class InicioViewHolder extends RecyclerView.ViewHolder {

        TextView tvLikes, tvComentarios, tvCompartidos, tvDescripcion, tvNombreUsuario;
        ImageButton ibLikes, ibComentarios, ibCompartidos, ibOpciones;
        ImageView ivFotoPublicacion, ivPerfilPost;

        public InicioViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvComentarios = itemView.findViewById(R.id.tvComents);
            tvCompartidos = itemView.findViewById(R.id.tvShares);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionPublicacion);
            tvNombreUsuario = itemView.findViewById(R.id.tvNombreUser);

            ibLikes = itemView.findViewById(R.id.ibLikes);
            ibComentarios = itemView.findViewById(R.id.ibComents);
            ibCompartidos = itemView.findViewById(R.id.ibShares);
            ibOpciones = itemView.findViewById(R.id.ibOpciones);

            ivFotoPublicacion = itemView.findViewById(R.id.ivImagen);
            ivPerfilPost = itemView.findViewById(R.id.ivPerfilPost);
        }
    }
}
