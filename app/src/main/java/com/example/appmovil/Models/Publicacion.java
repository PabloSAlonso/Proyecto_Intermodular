package com.example.appmovil.Models;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class Publicacion implements Serializable {
    private int id_publicacion;
    private int id_usuario;
    private Date fecha_publicacion;
    private Blob imagen;
    private String imagenBase64; // Base64 string for image
    private String descripcion;
    private int likes;
    private int comentarios;

    public Publicacion() {
        // Empty constructor required for JSON parsing
    }

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

    // Getters
    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public Blob getImagen() {
        return imagen;
    }

    public String getImagenBase64() {
        return imagenBase64;
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

    // Setters
    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "id=" + id_publicacion +
                ", id_usuario=" + id_usuario +
                ", descripcion='" + descripcion + '\'' +
                ", likes=" + likes +
                ", comentarios=" + comentarios +
                '}';
    }
}

