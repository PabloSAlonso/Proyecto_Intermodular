package com.example.appmovil.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.InicioViewHolder> {

    private Context contexto;
    private ArrayList<Publicacion> publicaciones;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

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

        // Set likes and comments count
        holder.tvLikes.setText(String.valueOf(p.getLikes()));
        holder.tvComentarios.setText(String.valueOf(p.getComentarios()));
        holder.tvCompartidos.setText("0");

        // Description
        holder.tvDescripcion.setText(p.getDescripcion());
        
        // Date
        if (p.getFecha_publicacion() != null) {
            holder.tvFecha.setText(dateFormat.format(p.getFecha_publicacion()));
        } else {
            holder.tvFecha.setText("");
        }

        // Username (placeholder - you could fetch user info)
        holder.tvNombreUsuario.setText("Usuario #" + p.getId_usuario());

        // Post image - load from Base64 if available
        String imagenBase64 = p.getImagenBase64();
        if (imagenBase64 != null && !imagenBase64.isEmpty()) {
            try {
                byte[] imageBytes = Base64.decode(imagenBase64, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                holder.ivFotoPublicacion.setImageBitmap(bitmap);
            } catch (Exception e) {
                // If decoding fails, show placeholder
                holder.ivFotoPublicacion.setImageResource(R.drawable.ic_launcher_foreground);
            }
        } else {
            // Show placeholder if no image
            holder.ivFotoPublicacion.setImageResource(R.drawable.ic_launcher_foreground);
        }
        
        // Profile picture placeholder
        holder.ivPerfilPost.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }
    
    /**
     * Update the publications list
     */
    public void updateData(ArrayList<Publicacion> nuevasPublicaciones) {
        this.publicaciones.clear();
        this.publicaciones.addAll(nuevasPublicaciones);
        notifyDataSetChanged();
    }

    public static class InicioViewHolder extends RecyclerView.ViewHolder {

        TextView tvLikes, tvComentarios, tvCompartidos, tvDescripcion, tvNombreUsuario, tvFecha;
        ImageButton ibLikes, ibComentarios, ibCompartidos, ibOpciones;
        ImageView ivFotoPublicacion, ivPerfilPost;

        public InicioViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvComentarios = itemView.findViewById(R.id.tvComents);
            tvCompartidos = itemView.findViewById(R.id.tvShares);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionPublicacion);
            tvNombreUsuario = itemView.findViewById(R.id.tvNombreUser);
            tvFecha = itemView.findViewById(R.id.tvFecha);

            ibLikes = itemView.findViewById(R.id.ibLikes);
            ibComentarios = itemView.findViewById(R.id.ibComents);
            ibCompartidos = itemView.findViewById(R.id.ibShares);
            ibOpciones = itemView.findViewById(R.id.ibOpciones);

            ivFotoPublicacion = itemView.findViewById(R.id.ivImagen);
            ivPerfilPost = itemView.findViewById(R.id.ivPerfilPost);
        }
    }
}

