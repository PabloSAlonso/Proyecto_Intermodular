package com.example.appmovil.Models;

import android.media.Image;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

public class Publicacion implements Serializable {
    private int id_publicacion;
    private int id_usuario;
    private Date fecha_publicacion;

    private Blob imagen;

    private String descripcion;
    private int likes;
    private int comentarios;
    public Publicacion(int id_publicacion,
                       int id_usuario,
                       Date fecha_publicacion,
                       Blob imagen,
                       String descripcion,
                       int likes,
                       int comentarios) {
        this.id_publicacion = id_publicacion;
        this.id_usuario = id_usuario;
        this.fecha_publicacion = fecha_publicacion;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.likes = likes;
        this.comentarios = comentarios;
    }



    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public Blob getImagen() {
        return imagen;
    }

    public int getComentarios() {
        return comentarios;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public int getLikes() {
        return likes;
    }

    public String getDescripcion() {
        return descripcion;
    }


}
