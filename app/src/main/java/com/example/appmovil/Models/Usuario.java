package com.example.appmovil.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.sql.Date;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String apellidos;
    private String nickname;
    private String email;
    private String password;
    private Date fecha_nacimiento;
    private Date fecha_creacion_cuenta;

    public Usuario(int id, String nombre, String apellidos, String nickname, String email, String password, Date fecha_nacimiento, Date fecha_creacion_cuenta){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_creacion_cuenta = fecha_creacion_cuenta;
    }

}
