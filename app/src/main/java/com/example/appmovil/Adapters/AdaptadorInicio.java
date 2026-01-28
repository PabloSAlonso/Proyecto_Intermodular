package com.example.appmovil.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
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
import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;

import java.util.ArrayList;

public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.InicioViewHolder>{
    Context contexto;
    ArrayList<Publicacion> publicaciones = new ArrayList<>();
    public AdaptadorInicio(ArrayList<Publicacion> publicaciones, Context contexto){
        this.publicaciones = publicaciones;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public InicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_publicacion,parent,false);
        InicioViewHolder ivh = new InicioViewHolder(elemento);
        return  ivh;
    }


    @Override
    public void onBindViewHolder(@NonNull InicioViewHolder holder, int position) {
        Publicacion p = this.publicaciones.get(position);
        if (selectedPos == position) {

        } else {

        }

    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    int selectedPos=RecyclerView.NO_POSITION;
    public int getSelectedPos () {
        return selectedPos;
    }
    public void setSelectedPos(int nuevaPos) {
        if (nuevaPos == this.selectedPos){

        }
    }

    public class InicioViewHolder extends RecyclerView.ViewHolder{
        private TextView tvLikes, tvComentarios, tvCompartidos, tvDescripcion, tvNombreUsuario;
        private ImageButton ibLikes, ibComentarios, ibCompartidos, ibPerfil, ibOpciones;
        private ImageView ivFotoPublicacion;
        private ScrollView sv;
        public InicioViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvLikes = itemView.findViewById(R.id.tvLikes);
            this.tvComentarios = itemView.findViewById(R.id.tvComents);
            this.tvCompartidos = itemView.findViewById(R.id.tvShares);
            this.tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            this.tvNombreUsuario = itemView.findViewById(R.id.tvNombreUser);
            this.ibLikes = itemView.findViewById(R.id.ibLikes);
            this.ibComentarios = itemView.findViewById(R.id.ibComents);
            this.ibCompartidos = itemView.findViewById(R.id.ibShares);
            this.sv = itemView.findViewById(R.id.scrollView2);
            this.ibPerfil = itemView.findViewById(R.id.ibPerfil);
            this.ibOpciones = itemView.findViewById(R.id.ibOpciones);
            this.ivFotoPublicacion = itemView.findViewById(R.id.ivImagen);
        }

        public ImageButton getIbComentarios() {
            return ibComentarios;
        }

        public ImageButton getIbCompartidos() {
            return ibCompartidos;
        }

        public ImageButton getIbLikes() {
            return ibLikes;
        }

        public ImageButton getIbOpciones() {
            return ibOpciones;
        }

        public ImageButton getIbPerfil() {
            return ibPerfil;
        }

        public ImageView getIvFotoPublicacion() {
            return ivFotoPublicacion;
        }

        public ScrollView getSv() {
            return sv;
        }

        public TextView getTvComentarios() {
            return tvComentarios;
        }

        public TextView getTvCompartidos() {
            return tvCompartidos;
        }

        public TextView getTvDescripcion() {
            return tvDescripcion;
        }

        public TextView getTvLikes() {
            return tvLikes;
        }

        public TextView getTvNombreUsuario() {
            return tvNombreUsuario;
        }

    }
}
