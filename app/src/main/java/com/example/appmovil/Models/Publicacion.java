package com.example.appmovil.Models;

import android.media.Image;

import java.io.Serializable;
import java.sql.Date;

public class Publicacion implements Serializable {
    private int id_publicacion;
    private int id_usuario;
    private Date fecha_publicacion;

    private Image imagen;

    private String descripcion;
    private int likes;
    private int comentarios;

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public Image getImagen() {
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
