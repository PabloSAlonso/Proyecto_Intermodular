package com.example.appmovil.Models;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class Historia implements Serializable {
    private int id_historia;
    private int id_usuario;
    private Date fecha_publicacion;
    private Blob imagen;
    private int likes;
}
