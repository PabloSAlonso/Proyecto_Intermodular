package com.example.appmovil;

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


}
